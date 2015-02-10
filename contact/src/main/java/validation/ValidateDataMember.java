package validation;

import java.util.ArrayList;

import db.DbException;
import db.DbExistance;

public class ValidateDataMember {

	public ValidateDataMember() {}

	public Unique exist(DataMember dataMember, String username) {

		if (dataMember.getType().equalsIgnoreCase("username")) {//change register
			return new Unique(!this.existUsername(dataMember, username));
		} 
		
		if (dataMember.getType().equalsIgnoreCase("email")) {
			return new Unique(!this.existEmail(dataMember, username));
		}
		
		return new Unique(!this.existPhoneNumber(dataMember, username));
	}

	public Boolean existUsername(DataMember dataMember, String username) {

		if(username != null && username.equals(dataMember.getValue()))
			return false;
		
		String query = "select count(*) from users where username = ? ";

		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(dataMember.getValue());

		Boolean exist = null;
		try {
			exist = new DbExistance().exist(query, parameters);
		} catch (DbException e) {

		}
		return exist;
	}
	
	public Boolean existEmail(DataMember dataMember, String username) {

		String query = "select count(*) from users inner join customers " 
						+"on users.user_id = customers.user_id "
						+"inner join customers_emails " 
						+"on customers.customer_id = customers_emails.customer_id "						
						+"where users.username = ? "
						+"and customers.customer_id != ? "
						+"and customers_emails.email = ?";

		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(username);
		parameters.add(dataMember.getCustomer_idDecripted());
		parameters.add(dataMember.getValue());

		Boolean exist = null;
		try {
			exist = new DbExistance().exist(query, parameters);
		} catch (DbException e) {

		}
		return exist;
	}
	
	public Boolean existPhoneNumber(DataMember dataMember, String username) {

		String query = "select count(*) from users inner join customers " 
				+"on users.user_id = customers.user_id "
				+"inner join customers_phone_numbers " 
				+"on customers.customer_id = customers_phone_numbers.customer_id "						
				+"where users.username = ? "
				+"and customers.customer_id != ? "
				+"and customers_phone_numbers.phone_number = ?";		

		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(username);
		parameters.add(dataMember.getCustomer_idDecripted());
		parameters.add(dataMember.getValue());

		Boolean exist = null;
		try {
			exist = new DbExistance().exist(query, parameters);
		} catch (DbException e) {

		}
		return exist;
	}
}
