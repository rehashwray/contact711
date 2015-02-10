package db;

import org.junit.Test;

import tableModel.CustomerProfile;

public class SearchCustomersTest {

	@SuppressWarnings("unused")
	@Test
	public void testGetCustomer() {
		DbCustomers searchCustomers = new DbCustomers();
		try {
			CustomerProfile customerProfile = searchCustomers.getCustomerProfile("mm", 2);
			int stop = 0;
			
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	//@Test
	@SuppressWarnings("unused")
	public void printQuery() {
		
		DbCustomers c = new DbCustomers();
		
		String query = "select * from users inner join customers "
				+ "on users.user_id = customers.user_id "
				+ "inner join customers_emails "
				+ "on customers.customer_id = customers_emails.customer_id "
				+ "inner join customers_phone_numbers "
				+ "on customers.customer_id = customers_phone_numbers.customer_id "
				+ "inner join customers_addresses "
				+ "on customers.customer_id = customers_addresses.customer_id "
				+ "where p = j and p = n"; 
		
		int[] expected = {0, 19, 40, 77, 105, 161, 196, 259, 290, 349};
		
		DbUtilities.print(query);
		
		//assertArrayEquals(expected, actual);
		
		int stop = 0;
		//assertArrayEquals
	}
	
	//@Test
	public void testAddCustomer() {
	}

}
