package customerManager;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tableModel.Customer;
import tableModel.CustomerAddress;
import tableModel.CustomerEmail;
import tableModel.CustomerPhoneNumber;
import customerProfileManager.Add;
import customerProfileManager.Update;
import db.DbException;
import db.DbExistance;
import db.DbUpdate;

public class UpdateTest {

	private Add add = null;
	private Update update = null;	
	
	private DbUpdate dbUpdate = null;
	private DbExistance existance = null;

	private ArrayList<Record> rtd = null;
	
	private Integer customerId = 16;		
	
	@Before
	public void initialize() {
		add = new Add();
		update = new Update(customerId);
		
		dbUpdate = new DbUpdate();
		existance = new DbExistance();

		rtd = new ArrayList<Record>();					
	}
	
    @After
    public void clean() throws DbException{    	
    	
    	for (Record record : rtd) {

			String query = "delete from " + record.getTable() + " where "
					+ record.getIdName() + " = ?";

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(record.getIdValue());

			dbUpdate.updateDb(query, parameters, "customer_id");			
		}
    }
	
    @Test
	public void testUpdateCustomer() throws DbException {
				
		String query = "select count(*) from customers "
				+ "where customer_name = ? and last_name = ? "
				+ "and customer_id = ? ";
				
		Customer customer = new Customer(-1, -1, "marvin", "cas");			
		Integer customerId = add.addCustomer(customer);
		
		customer = new Customer(-1, customerId, "mar", "c");
		update = new Update(customerId);
		update.updateCustomer(customer);
				
		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(customer.getCustomer_name());
		parameters.add(customer.getLast_name());
		parameters.add(customer.getCustomer_idDecripted());
		
		assertTrue(existance.exist(query, parameters));
		
		rtd.add(new Record("customers", "customer_id", customerId));
	}

	@Test
	public void testUpdateCustomerEmails() throws DbException {

		String query = "select count(*) from customers_emails "
				+ "where email_id = ? and email = ? ";
				
		HashMap<Integer, CustomerEmail> 
			customerEmails = new HashMap<Integer, CustomerEmail>();	
		
		customerEmails.put(1, new CustomerEmail(customerId, 1, "bon"));
		customerEmails.put(2, new CustomerEmail(customerId, 2, "nfl"));
		customerEmails.put(3, new CustomerEmail(customerId, 3, "qu"));
		
		HashMap<Integer, Integer> emailIds = add.addCustomerEmails(customerEmails);
		
		customerEmails.put(1, new CustomerEmail(customerId, emailIds.get(1), "bon222"));
		customerEmails.put(2, new CustomerEmail(customerId, emailIds.get(2), "nfl222"));
		customerEmails.put(3, new CustomerEmail(customerId, emailIds.get(3), "qu222"));		
		
		update.updateCustomerEmails(customerEmails);
		
		for(Integer customerEmailKey : customerEmails.keySet()){
		
			CustomerEmail customerEmail = customerEmails.get(customerEmailKey);
			
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(emailIds.get(customerEmailKey));
			parameters.add(customerEmail.getEmail());

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_emails", 
					"email_id", emailIds.get(customerEmailKey)));
		}
	}

	@Test
	public void testUpdateCustomerPhoneNumbers() throws DbException {

		String query = "select count(*) from customers_phone_numbers "
				+ "where phone_number_id = ? and phone_number = ? ";
				
		HashMap<Integer, CustomerPhoneNumber> 
			customerPhoneNumbers = new HashMap<Integer, CustomerPhoneNumber>();	
		
		customerPhoneNumbers.put(1, new CustomerPhoneNumber(customerId, 1, "783"));
		customerPhoneNumbers.put(2, new CustomerPhoneNumber(customerId, 2, "3-3"));
		customerPhoneNumbers.put(3, new CustomerPhoneNumber(customerId, 3, "33-4-3-3"));
		
		HashMap<Integer, Integer> phoneNumberIds = add.addCustomerPhoneNumbers(customerPhoneNumbers);
		
		customerPhoneNumbers.put(1, new CustomerPhoneNumber(customerId, phoneNumberIds.get(1), "939-783"));
		customerPhoneNumbers.put(2, new CustomerPhoneNumber(customerId, phoneNumberIds.get(2), "939-3-3"));
		customerPhoneNumbers.put(3, new CustomerPhoneNumber(customerId, phoneNumberIds.get(3), "939-33-4-3-3"));		
		
		update.updateCustomerPhoneNumbers(customerPhoneNumbers);
		
		for(Integer customerPhoneNumberKey : customerPhoneNumbers.keySet()){
		
			CustomerPhoneNumber customerPhoneNumber = customerPhoneNumbers.get(customerPhoneNumberKey);
			
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(phoneNumberIds.get(customerPhoneNumberKey));
			parameters.add(customerPhoneNumber.getPhone_number());

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_phone_numbers", 
					"phone_number_id", 
					phoneNumberIds.get(customerPhoneNumberKey)));
		}
	}

	@Test
	public void testUpdateCustomerAddresses() throws DbException {

		String query = "select count(*) from customers_addresses "
				+ "where address_id = ? and street = ? and city = ? "
				+ "and state = ? and zip_code = ? ";
				
		HashMap<Integer, CustomerAddress> 
			customerAddresses = new HashMap<Integer, CustomerAddress>();	
		
		customerAddresses.put(1, new CustomerAddress(customerId, 1, "sta", "dca", "ade", "003"));
		customerAddresses.put(2, new CustomerAddress(customerId, 2, "xxxxxsta234", "dca", "ade", "004"));
		customerAddresses.put(3, new CustomerAddress(customerId, 3, "st232a", "dca", "ade", "006"));
		
		HashMap<Integer, Integer> addressIds = add.addCustomerAddresses(customerAddresses);

		customerAddresses.put(1, new CustomerAddress(customerId, addressIds.get(1), "a", "a", "a", "00333"));
		customerAddresses.put(2, new CustomerAddress(customerId, addressIds.get(2), "b", "b", "b", "00433"));
		customerAddresses.put(3, new CustomerAddress(customerId, addressIds.get(3), "c", "c", "c", "00633"));

		update.updateCustomerAddresses(customerAddresses);
		
		for(Integer customerAddressesKey : customerAddresses.keySet()){
		
			CustomerAddress customerAddress = customerAddresses.get(customerAddressesKey);
			
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(addressIds.get(customerAddressesKey));
			parameters.add(customerAddress.getStreet());
			parameters.add(customerAddress.getCity());
			parameters.add(customerAddress.getState());
			parameters.add(customerAddress.getZip_code());

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_addresses", "address_id", addressIds.get(customerAddressesKey)));
		}
	}
}
