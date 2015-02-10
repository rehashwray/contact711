package customerManager;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import tableModel.Customer;
import tableModel.CustomerAddress;
import tableModel.CustomerEmail;
import tableModel.CustomerPhoneNumber;
import tableModel.CustomerProfile;
import customerProfileManager.Add;
import db.DbException;
import db.DbUpdate;
import db.DbExistance;

public class AddTest {
	
	private Add add = null;

	private DbUpdate dbUpdate = null;
	private DbExistance existance = null;

	private ArrayList<Record> rtd = null;
	
	private Integer customerId = null;		
	
	@Before
	public void initialize() {
		add = new Add();
		
		dbUpdate = new DbUpdate();
		existance = new DbExistance();

		rtd = new ArrayList<Record>();
					
		customerId = -1;
	}

    @After
    public void clean() throws DbException{    	

		for (Record record : rtd) {

			String query = "delete from " + record.getTable() + " where "
					+ record.getIdName() + " = ?";

			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(record.getIdValue());

			dbUpdate.updateDb(query, parameters, "customer_id");			
			//assertFalse(existance.exist(query, parameters));
		}
    }
	
    @Ignore
	@Test
	public void testAddCustomer() throws DbException {
				
		String query = "select count(*) from customers "
				+ "where customer_name = ? and last_name = ? "
				+ "and customer_id = ? ";
				
		Customer customer = new Customer(customerId, -1, "marvin", "cas");	
		
		Integer customerId = add.addCustomer(customer);
		
		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(customer.getCustomer_name());
		parameters.add(customer.getLast_name());
		parameters.add(customerId);
		
		assertTrue(existance.exist(query, parameters));
		
		rtd.add(new Record("customers", "customer_id", customerId));
	}

    @Ignore
	@Test
	public void testAddCustomerEmails() throws DbException {
		
		String query = "select count(*) from customers_emails "
				+ "where email_id = ? and email = ? ";
				
		HashMap<Integer, CustomerEmail> 
			customerEmails = new HashMap<Integer, CustomerEmail>();	
		
		customerEmails.put(1, new CustomerEmail(customerId, 1, "bon"));
		customerEmails.put(2, new CustomerEmail(customerId, 2, "nfl"));
		customerEmails.put(3, new CustomerEmail(customerId, 3, "qu"));
		
		HashMap<Integer, Integer> emailIds = add.addCustomerEmails(customerEmails);
		
		Set<Integer> customerEmailsKeys = customerEmails.keySet();		
		for(Integer customerEmailKey : customerEmailsKeys){
		
			CustomerEmail customerEmail = customerEmails.get(customerEmailKey);
			
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(emailIds.get(customerEmailKey));
			parameters.add(customerEmail.getEmail());

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_emails", 
					"email_id", emailIds.get(customerEmailKey)));
		}
	}

    @Ignore
	@Test
	public void testAddCustomerPhoneNumbers() throws DbException {
		
		String query = "select count(*) from customers_phone_numbers "
				+ "where phone_number_id = ? and phone_number = ? ";
				
		HashMap<Integer, CustomerPhoneNumber> 
			customerPhoneNumbers = new HashMap<Integer, CustomerPhoneNumber>();	
		
		customerPhoneNumbers.put(1, new CustomerPhoneNumber(customerId, 1, "783"));
		customerPhoneNumbers.put(2, new CustomerPhoneNumber(customerId, 2, "3-3"));
		customerPhoneNumbers.put(3, new CustomerPhoneNumber(customerId, 3, "33-4-3-3"));
		
		HashMap<Integer, Integer> phoneNumberIds = add.addCustomerPhoneNumbers(customerPhoneNumbers);
		
		Set<Integer> customerPhoneNumbersKeys = customerPhoneNumbers.keySet();
		
		for(Integer customerPhoneNumberKey : customerPhoneNumbersKeys){
		
			CustomerPhoneNumber customerPhoneNumber = customerPhoneNumbers.get(customerPhoneNumberKey);
			
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(phoneNumberIds.get(customerPhoneNumberKey));
			parameters.add(customerPhoneNumber.getPhone_number());

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_phone_numbers", 
					"phone_number_id", phoneNumberIds.get(customerPhoneNumberKey)));
		}
	}

    @Ignore
	@Test
	public void testAddCustomerAddresses() throws DbException {

		String query = "select count(*) from customers_addresses "
				+ "where address_id = ? and street = ? and city = ? "
				+ "and state = ? and zip_code = ? ";
				
		HashMap<Integer, CustomerAddress> 
			customerAddresses = new HashMap<Integer, CustomerAddress>();	
		
		customerAddresses.put(1, new CustomerAddress(customerId, 1, "sta", "dca", "ade", "003"));
		customerAddresses.put(2, new CustomerAddress(customerId, 2, "xxxxxsta234", "dca", "ade", "004"));
		customerAddresses.put(3, new CustomerAddress(customerId, 3, "st232a", "dca", "ade", "006"));
		
		HashMap<Integer, Integer> addressIds = add.addCustomerAddresses(customerAddresses);
		
		Set<Integer> customerAddressesKeys = customerAddresses.keySet();
		
		for(Integer customerAddressesKey : customerAddressesKeys){
		
			CustomerAddress customerAddress = customerAddresses.get(customerAddressesKey);
			
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(addressIds.get(customerAddressesKey));
			parameters.add(customerAddress.getStreet());
			parameters.add(customerAddress.getCity());
			parameters.add(customerAddress.getState());
			parameters.add(customerAddress.getZip_code());

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_addresses", 
					"address_id", addressIds.get(customerAddressesKey)));
		}
	}
	
	@Test
	public void testAddCustomerProfile() {;

		add = mock(Add.class);
		
		Customer customer = new Customer(1, 1, "john", "bach");
		
		HashMap<Integer, CustomerEmail> customerEmails = new HashMap<Integer, CustomerEmail>();
		CustomerEmail customerEmail = new CustomerEmail(1, 1, "m@d.c");
		customerEmails.put(1, customerEmail);

		HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers = new HashMap<Integer, CustomerPhoneNumber>();
		CustomerPhoneNumber customerPhoneNumber = new CustomerPhoneNumber(1, 1, "324-33");
		customerPhoneNumbers.put(1, customerPhoneNumber);		
		
		HashMap<Integer, CustomerAddress> customerAddresses = new HashMap<Integer, CustomerAddress>();
		CustomerAddress customerAddress = new CustomerAddress(1, 1, null, null, null, null);
		customerAddresses.put(1, customerAddress);
		
		CustomerProfile customerProfile = new CustomerProfile();
		customerProfile.setCustomer(customer);
		customerProfile.setCustomerEmails(customerEmails);
		customerProfile.setCustomerPhoneNumbers(customerPhoneNumbers);
		customerProfile.setCustomerAddresses(customerAddresses);
		
		when(add.addCustomer(any(Customer.class))).thenReturn(1);								
		when(add.addCustomerEmails(customerEmails)).thenReturn(null);										
		
		assertTrue(false);
	}
}
