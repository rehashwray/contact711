package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import search.SearchQueryParsed;
import search.SearchResult;
import tableModel.Customer;
import tableModel.CustomerAddress;
import tableModel.CustomerEmail;
import tableModel.CustomerPhoneNumber;
import tableModel.CustomerProfile;

public class DbCustomers {

	public PreparedStatement prepareFindCustomersQuery(
			String username, SearchQueryParsed sqp, Connection connection) throws SQLException{

		String baseQuery = "select table_distinct.customer_id, table_distinct.customer_name, "
				+ "table_distinct.last_name, count(*) OVER() AS customersFound "
				+ "from (select distinct customers.customer_id customer_id, "
				+ "		customers.customer_name customer_name, customers.last_name last_name "				
				+ "from users inner join customers " 
				+ "on users.user_id = customers.user_id "
				+ "inner join customers_emails "
				+ "on customers.customer_id = customers_emails.customer_id "
				+ "inner join customers_phone_numbers "
				+ "on customers.customer_id = customers_phone_numbers.customer_id "
				+ "inner join customers_addresses "
				+ "on customers.customer_id = customers_addresses.customer_id "
				+ "where username = '" + username + "' and ( ";
				
		StringBuilder query = new StringBuilder(baseQuery);

		class ColumnWord{
			private String columnName;
			private ArrayList<String> wordList;
			
			public ColumnWord(String columnName, ArrayList<String> wordList){
				this.columnName = columnName;
				this.wordList = wordList;
			}
			
			public String getColumnName() {
				return columnName;
			}
			public ArrayList<String> getWordList() {
				return wordList;
			}
		}
		
		ArrayList<ColumnWord> columnWords = 
				new ArrayList<ColumnWord>();
						
		columnWords.add(
				new ColumnWord(
						"customers.customer_name", sqp.getNameWords()));
		columnWords.add(
				new ColumnWord(
						"customers.last_name", sqp.getLastNameWords()));
		columnWords.add(
				new ColumnWord(
						"customers_emails.email", sqp.getEmailWords()));
		columnWords.add(
				new ColumnWord(
						"customers_phone_numbers.phone_number", sqp.getPhoneWords()));
		columnWords.add(
				new ColumnWord(
						"customers_addresses.street", sqp.getStreetWords()));
		columnWords.add(
				new ColumnWord(
						"customers_addresses.city", sqp.getCityWords()));
		columnWords.add(
				new ColumnWord(
						"customers_addresses.state", sqp.getStateWords()));
		columnWords.add(
				new ColumnWord(
						"customers_addresses.zip_code", sqp.getZipWords()));
		
		ArrayList<String> searchWordsList = new ArrayList<String>();
		
		for (ColumnWord columnWord : columnWords) {
			if (!columnWord.getWordList().isEmpty()) {
				
				query.append(columnWord.getColumnName() + " similar to ");
				query.append("?");
				query.append(" or ");
				
				StringBuilder searchWords = new StringBuilder("%(");					
				for (String word : columnWord.getWordList()) {
					searchWords.append(word + "|");
				}
				searchWords.delete(searchWords.length() - 1, searchWords.length());
				searchWords.append(")%");
				
				searchWordsList.add(searchWords.toString());
			}
		}
		if(query.charAt(query.length() - 2) != '('){
			query.delete(query.length() - 4, query.length());
		}
		else{
			query.append(" 1 = 2 "); //force to be empty result
		}
		query.append(" ) ) table_distinct ");
		query.append("ORDER BY table_distinct.customer_id ");
		query.append("LIMIT "+ sqp.getDisplayLimit() + " " 
				+"OFFSET "+ sqp.getFrom());
		
		PreparedStatement statement = null;
		statement = connection.prepareStatement(query.toString());
			
		int index = 1;
		for (String searchWord : searchWordsList) {
			statement.setString(index, searchWord);
			index++;
		}			
		
		//DbUtilities.print(statement.toString());
				
		return statement;
	}	
	
	public SearchResult searchCustomers(String username, SearchQueryParsed sqp) 
			throws DbException {
		
		Connection connection = null;
		try {
			connection = DbUtilities.openDatabase();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setOpenConnection();
			throw dbe;
		}

		PreparedStatement statement = null;
		SearchResult searchResult = new SearchResult();
		try {
			statement = this.prepareFindCustomersQuery(username, sqp, connection);
			
			ResultSet rs = statement.executeQuery();
			
			HashMap<String, Customer> customers = new HashMap<String, Customer>();
			
			while(rs.next()) {
				if(searchResult.getCustomersFound() == null){
					searchResult.setCustomersFound(rs.getInt("customersFound"));
				}
				
				customers.put(
						rs.getString(1), 
						new Customer(-1, rs.getInt(1), rs.getString(2), rs.getString(3)));
			} 
			searchResult.setCustomers(customers);
			
			rs.close();
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setExecutingStatement();
			throw dbe;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingStatement();
				throw dbe;
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingConnection();				
				throw dbe;
			}
		}
		
		return searchResult;
	}
	
	public ArrayList<Integer> getCustomerIds(String username) throws DbException{
		
		Connection connection = null;
		try {
			connection = DbUtilities.openDatabase();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setOpenConnection();
			throw dbe;
		}

		PreparedStatement statement = null;
		ArrayList<Integer> customerIds = new ArrayList<Integer>();
		try {		
			statement = connection.prepareStatement(
					"select distinct customers.customer_id "
					+ "from users inner join customers "
					+ "on users.user_id = customers.user_id "
					+ "where users.username = ? ");
			
			statement.setString(1, username);
			
			ResultSet rs = statement.executeQuery();								

			while(rs.next()) {
				customerIds.add(rs.getInt("customer_id"));
			} 
			
			rs.close();				
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setExecutingStatement();
			throw dbe;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingStatement();
				throw dbe;
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingConnection();				
				throw dbe;
			}
		}	
		
		return customerIds;
	}
	
	public CustomerProfile getCustomerProfile(String username, int customer_id) 
			throws DbException {
		
		Connection connection = null;
		try {
			connection = DbUtilities.openDatabase();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setOpenConnection();
			throw dbe;
		}

		PreparedStatement statement = null;
		CustomerProfile customerProfile = new CustomerProfile();
		try {		
			statement = connection.prepareStatement(
					"select customers.*, "
					+ "customers_emails.*, "
					+ "customers_phone_numbers.*, "
					+ "customers_addresses.* "
					+ "from users inner join customers "
					+ "on users.user_id = customers.user_id "
					+ "inner join customers_emails "
					+ "on customers.customer_id = customers_emails.customer_id "
					+ "inner join customers_phone_numbers "
					+ "on customers.customer_id = customers_phone_numbers.customer_id "
					+ "inner join customers_addresses "
					+ "on customers.customer_id = customers_addresses.customer_id "
					+ "where username = '" + username + "' and " 
					+ "customers.customer_id = " + customer_id);
			
			ResultSet rs = statement.executeQuery();				
			
			Customer customer = null;
			HashMap<Integer, CustomerEmail> customerEmails = new HashMap<Integer, CustomerEmail>();
			HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers = new HashMap<Integer, CustomerPhoneNumber>();
			HashMap<Integer, CustomerAddress> customerAddresses = new HashMap<Integer, CustomerAddress>();

			while(rs.next()) {
				customer = new Customer(
						rs.getInt("user_id"), 
						rs.getInt("customer_id"), 
						rs.getString("customer_name"), 
						rs.getString("last_name"));
			 			
				customerEmails.put(
						rs.getInt("email_id"),
						new CustomerEmail(
								rs.getInt("customer_id"), 
								rs.getInt("email_id"), 
								rs.getString("email")));
			
				customerPhoneNumbers.put(
						rs.getInt("phone_number_id"),
						new CustomerPhoneNumber(
								rs.getInt("customer_id"),
								rs.getInt("phone_number_id"), 
								rs.getString("phone_number")));

				customerAddresses.put(
						rs.getInt("address_id"),
						new CustomerAddress(
								rs.getInt("customer_id"),
								rs.getInt("address_id"), 
								rs.getString("city"),
								rs.getString("street"),
								rs.getString("state"),
								rs.getString("zip_code")));
			} 
			rs.close();
			
			customerProfile = new CustomerProfile();
			customerProfile.setCustomer(customer);
			customerProfile.setCustomerEmails(customerEmails);
			customerProfile.setCustomerPhoneNumbers(customerPhoneNumbers);
			customerProfile.setCustomerAddresses(customerAddresses);
			
		} catch (SQLException e) {
			DbException dbe = new DbException(e.getMessage());
			dbe.setExecutingStatement();
			throw dbe;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingStatement();
				throw dbe;
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				DbException dbe = new DbException(e.getMessage());
				dbe.setClosingConnection();				
				throw dbe;
			}
		}		
		return customerProfile;
	}
}
