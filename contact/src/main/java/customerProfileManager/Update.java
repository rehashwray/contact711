package customerProfileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import logger.Log;
import tableModel.Customer;
import tableModel.CustomerAddress;
import tableModel.CustomerEmail;
import tableModel.CustomerPhoneNumber;
import tableModel.CustomerProfile;
import db.DbException;

public class Update 
	extends Add {
	
	public Update(int customer_id) {
		super(customer_id);
	}

	public PrimaryKeys updateCustomerProfile(
			CustomerProfile customerProfileUpdate,
			CustomerProfile customerProfileAdd){

		if(customerProfileUpdate.getCustomer() != null)
			updateCustomer(
					customerProfileUpdate.getCustomer());
		
		if(customerProfileUpdate.getCustomerEmails() != null)
			updateCustomerEmails(
					customerProfileUpdate.getCustomerEmails());
		
		if(customerProfileUpdate.getCustomerPhoneNumbers() != null)
			updateCustomerPhoneNumbers(
					customerProfileUpdate.getCustomerPhoneNumbers());
		
		if(customerProfileUpdate.getCustomerAddresses() != null)
			updateCustomerAddresses(
					customerProfileUpdate.getCustomerAddresses());
		
		return addCustomerProfile(customerProfileAdd);		
	}
	
	public void updateCustomer(Customer customer){		
		String query = "update customers "
				+ "set customer_name = ?, "
				+ "last_name = ? "
				+ "where customer_id = ?";
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		
		parameters.add(customer.getCustomer_name());
		parameters.add(customer.getLast_name());
		parameters.add(customer.getCustomer_idDecripted());
		
		try {
			dbUpdate.updateDb(query, parameters, "customer_id");
		} catch (DbException e) {
			Log.logError(e);
		}
	}
	
	public void updateCustomerEmails(
			HashMap<Integer, CustomerEmail> customerEmails) {

		Set<Integer> customerEmailsKeys = customerEmails.keySet();

		for (Integer customerEmailKey : customerEmailsKeys) {

			CustomerEmail customerEmail = customerEmails.get(customerEmailKey);

			String query = "update customers_emails " 
					+ "set email = ? "
					+ "where email_id = ?";

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(customerEmail.getEmail());
			parameters.add(customerEmail.getEmail_id());

			try {
				dbUpdate.updateDb(query, parameters, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}
	
	public void updateCustomerPhoneNumbers(
			HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers) {
		
		Set<Integer> customerPhoneNumbersKeys = customerPhoneNumbers.keySet();

		for (Integer customerPhoneNumbersKey : customerPhoneNumbersKeys) {
			
			CustomerPhoneNumber customerPhoneNumber = customerPhoneNumbers
					.get(customerPhoneNumbersKey);
			
			String query = "update customers_phone_numbers "
					+ "set phone_number = ? " 
					+ "where phone_number_id = ?";

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(customerPhoneNumber.getPhone_number());
			parameters.add(customerPhoneNumber.getPhone_number_id());

			try {
				dbUpdate.updateDb(query, parameters, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}
	
	public void updateCustomerAddresses(
			HashMap<Integer, CustomerAddress> customerAddresses) {

		Set<Integer> customerAddressesKeys = customerAddresses.keySet();

		for (Integer customerAddressKey : customerAddressesKeys) {

			CustomerAddress customerAddress = customerAddresses
					.get(customerAddressKey);

			String query = "update customers_addresses "
					+ "set street = ?, " 
					+ "city = ?, " 
					+ "state = ?, "
					+ "zip_code = ? " 
					+ "where address_id = ?";

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(customerAddress.getStreet());
			parameters.add(customerAddress.getCity());
			parameters.add(customerAddress.getState());
			parameters.add(customerAddress.getZip_code());
			parameters.add(customerAddress.getAddress_id());

			try {
				dbUpdate.updateDb(query, parameters, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}
	

}
