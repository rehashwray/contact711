package customerProfileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import tableModel.CustomerAddress;
import tableModel.CustomerEmail;
import tableModel.CustomerPhoneNumber;
import tableModel.CustomerProfile;
import db.DbException;
import db.DbUpdate;
import error.Log;

public class Delete {

	protected DbUpdate dbUpdate = new DbUpdate();

	public Delete(){}
	
	public void deleteCustomerProfile(
			CustomerProfile customerProfileDelete){
		
		if(customerProfileDelete.getCustomerEmails() != null)
			deleteCustomerEmails(
					customerProfileDelete.getCustomerEmails());
		
		if(customerProfileDelete.getCustomerPhoneNumbers() != null)
			deleteCustomerPhoneNumbers(
					customerProfileDelete.getCustomerPhoneNumbers());
		
		if(customerProfileDelete.getCustomerAddresses() != null)
			deleteCustomerAddresses(
					customerProfileDelete.getCustomerAddresses());
	}
	
	public String deleteCustomers(ArrayList<Integer> customerIds){		

		String result = "ok";

		for (Integer id : customerIds) {

			String queryCustomer = "delete from customers "
					+ "where customer_id = ?";

			ArrayList<Object> customerParameters = new ArrayList<Object>();

			customerParameters.add(id);

			try {
				dbUpdate.updateDb(queryCustomer, customerParameters,
						"customer_id");
			} catch (DbException e) {
				Log.logError(e);
				result = "failed";
			}

			String query = "delete from customers_emails "
					+ "where customer_id = ?";

			ArrayList<Object> parametersCustomerEmails = new ArrayList<Object>();

			parametersCustomerEmails.add(id);

			try {
				dbUpdate.updateDb(query, parametersCustomerEmails,
						"customer_id");
			} catch (DbException e) {
				Log.logError(e);
				result = "failed";
			}

			String queryCustomerPhoneNumbers = "delete from customers_phone_numbers "
					+ "where customer_id = ?";

			ArrayList<Object> parametersCustomerPhoneNumbers = new ArrayList<Object>();

			parametersCustomerPhoneNumbers.add(id);

			try {
				dbUpdate.updateDb(queryCustomerPhoneNumbers,
						parametersCustomerPhoneNumbers, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
				result = "failed";
			}

			String queryCustomerAddresses = "delete from customers_addresses "
					+ "where customer_id = ?";

			ArrayList<Object> parametersCustomerAddresses = new ArrayList<Object>();

			parametersCustomerAddresses.add(id);

			try {
				dbUpdate.updateDb(queryCustomerAddresses,
						parametersCustomerAddresses, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
				result = "failed";
			}
		}
		
		return result;
	}
	
	public void deleteCustomerEmails(
		HashMap<Integer, CustomerEmail> customerEmails) {

		Set<Integer> customerEmailsKeys = customerEmails.keySet();
	
		for (Integer customerEmailKey : customerEmailsKeys) {
						
			String query = "delete from customers_emails " 
					+ "where email_id = ?";
	
			ArrayList<Object> parameters = new ArrayList<Object>();
	
			parameters.add(customerEmailKey);
	
			try {
				dbUpdate.updateDb(query, parameters, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}
	
	public void deleteCustomerPhoneNumbers(
			HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers) {
		
		Set<Integer> customerPhoneNumbersKeys = customerPhoneNumbers.keySet();

		for (Integer customerPhoneNumbersKey : customerPhoneNumbersKeys) {
			
			String query = "delete from customers_phone_numbers "
					+ "where phone_number_id = ?";
	
			ArrayList<Object> parameters = new ArrayList<Object>();
	
			parameters.add(customerPhoneNumbersKey);
	
			try {
				dbUpdate.updateDb(query, parameters, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}
	
	public void deleteCustomerAddresses(
			HashMap<Integer, CustomerAddress> customerAddresses) {

		Set<Integer> customerAddressesKeys = customerAddresses.keySet();

		for (Integer customerAddressKey : customerAddressesKeys) {
				
			String query = "delete from customers_addresses "
					+ "where address_id = ?";
	
			ArrayList<Object> parameters = new ArrayList<Object>();
			
			parameters.add(customerAddressKey);
	
			try {
				dbUpdate.updateDb(query, parameters, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}
}
