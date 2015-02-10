package customerProfileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import tableModel.Customer;
import tableModel.CustomerAddress;
import tableModel.CustomerEmail;
import tableModel.CustomerPhoneNumber;
import tableModel.CustomerProfile;
import db.DbException;
import db.DbUpdate;
import error.Log;

public class Add {

	protected DbUpdate dbUpdate = new DbUpdate();
	private int customer_id;
			
	public Add(){
		this.customer_id = -1;
	}
	
	public Add(int customer_id) {
		this.customer_id = customer_id;
	}	
	
	public PrimaryKeys addCustomerProfile(
			CustomerProfile customerProfileAdd){

		PrimaryKeys primaryKeys = new PrimaryKeys();
		
		if(customerProfileAdd.getCustomerEmails() != null)
			primaryKeys.setCustomerEmailsId(
				addCustomerEmails(customerProfileAdd.getCustomerEmails()));					
		
		if(customerProfileAdd.getCustomerPhoneNumbers() != null)
			primaryKeys.setCustomerPhoneNumbersId(
				addCustomerPhoneNumbers(customerProfileAdd.getCustomerPhoneNumbers()));
		
		if(customerProfileAdd.getCustomerAddresses() != null)
			primaryKeys.setCustomerAddressesId(
				addCustomerAddresses(customerProfileAdd.getCustomerAddresses()));
		
		return primaryKeys;
	}
	
	public Integer addCustomer(Customer customer){		
		String query = "insert into customers "
				+ "(customer_name, last_name, user_id) "
				+ "values(?, ?, ?)";
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		
		parameters.add(customer.getCustomer_name());
		parameters.add(customer.getLast_name());
		parameters.add(customer.getUserId());
		
		try {
			Integer result = dbUpdate.updateDb(
					query, parameters, "customer_id");		
			
			if(result != null){
				this.customer_id = result;
			}
		} catch (DbException e) {
			Log.logError(e);
		}
		return this.customer_id;
	}	
	
	public HashMap<Integer, Integer> addCustomerEmails(
			HashMap<Integer, CustomerEmail> customerEmails) {

		HashMap<Integer, Integer> customerEmailsId = new HashMap<Integer, Integer>();
		
		Set<Integer> customerEmailsKeys = customerEmails.keySet();

		for (Integer customerEmailKey : customerEmailsKeys) {

			CustomerEmail customerEmail = customerEmails.get(customerEmailKey);

			String query = "insert into customers_emails " 
					+ "(customer_id, email) values("+ customer_id +", ?)";

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(customerEmail.getEmail());

			try {
				Integer emailId = dbUpdate.updateDb(query, parameters, "email_id");
				
				customerEmailsId.put(
						customerEmail.getEmail_id(), emailId);
			} catch (DbException e) {
				Log.logError(e);
			}
		}
		
		return customerEmailsId;
	}
	
	public HashMap<Integer, Integer> addCustomerPhoneNumbers(
			HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers) {

		HashMap<Integer, Integer> customerPhoneNumbersIds = new HashMap<Integer, Integer>();
		
		Set<Integer> customerPhoneNumbersKeys = customerPhoneNumbers.keySet();
		
		for (Integer customerPhoneNumbersKey : customerPhoneNumbersKeys) {
			
			CustomerPhoneNumber customerPhoneNumber = customerPhoneNumbers
					.get(customerPhoneNumbersKey);
			
			String query = "insert into customers_phone_numbers "
					+ "(customer_id, phone_number) values("+ customer_id +", ?)"; 

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(customerPhoneNumber.getPhone_number());

			try {
				Integer phoneNumberId = dbUpdate.updateDb(
						query, parameters, "phone_number_id");
				
				customerPhoneNumbersIds.put(
						customerPhoneNumber.getPhone_number_id(), phoneNumberId);
			} catch (DbException e) {
				Log.logError(e);
			}
		}		
		return customerPhoneNumbersIds;
	}
	
	public HashMap<Integer, Integer> addCustomerAddresses(
			HashMap<Integer, CustomerAddress> customerAddresses) {

		HashMap<Integer, Integer> customerAddressesIds = new HashMap<Integer, Integer>();
		
		Set<Integer> customerAddressesKeys = customerAddresses.keySet();

		for (Integer customerAddressKey : customerAddressesKeys) {

			CustomerAddress customerAddress = customerAddresses
					.get(customerAddressKey);

			String query = "insert into customers_addresses "
					+ "(customer_id, street, city, state, zip_code) " 
					+ "values("+ customer_id +", ?, ?, ?, ?)";

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(customerAddress.getStreet());
			parameters.add(customerAddress.getCity());
			parameters.add(customerAddress.getState());
			parameters.add(customerAddress.getZip_code());

			try {
				Integer addressId = dbUpdate.updateDb(query, parameters, "address_id");

				if(addressId != null){
					customerAddressesIds.put(customerAddress.getAddress_id(), addressId);
				}
			} catch (DbException e) {
				Log.logError(e);
			}
		}		
		return customerAddressesIds;
	}
}
