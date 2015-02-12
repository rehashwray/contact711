package customerManager;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

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
import db.DbExistance;
import db.DbUpdate;

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
		}
    }
	
    @Ignore
	@Test
	public void testAddCustomer() throws DbException {
				
		String query = "select count(*) from customers where customer_id = ? ";
				
		Customer customer = new Customer(customerId, -1, "marvin", "cas");	
		
		Integer customerId = add.addCustomer(customer);
		
		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(customerId);
		
		assertTrue(existance.exist(query, parameters));
		
		rtd.add(new Record("customers", "customer_id", customerId));
	}

    @Ignore
	@Test
	public void testAddCustomerEmails() throws DbException {
		
		String query = "select count(*) from customers_emails where email_id = ?";
				
		HashMap<Integer, CustomerEmail> 
			customerEmails = new HashMap<Integer, CustomerEmail>();	
		
		customerEmails.put(1, new CustomerEmail(customerId, 1, "bon"));
		customerEmails.put(2, new CustomerEmail(customerId, 2, "nfl"));
		customerEmails.put(3, new CustomerEmail(customerId, 3, "qu"));
		
		HashMap<Integer, Integer> emailIds = add.addCustomerEmails(customerEmails);
		
		for(Integer customerEmailKey : customerEmails.keySet()){
					
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(emailIds.get(customerEmailKey));

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_emails", 
					"email_id", emailIds.get(customerEmailKey)));
		}
	}

    @Ignore
	@Test
	public void testAddCustomerPhoneNumbers() throws DbException {
		
		String query = "select count(*) from customers_phone_numbers where phone_number_id = ?";
				
		HashMap<Integer, CustomerPhoneNumber> 
			customerPhoneNumbers = new HashMap<Integer, CustomerPhoneNumber>();	
		
		customerPhoneNumbers.put(1, new CustomerPhoneNumber(customerId, 1, "783"));
		customerPhoneNumbers.put(2, new CustomerPhoneNumber(customerId, 2, "3-3"));
		customerPhoneNumbers.put(3, new CustomerPhoneNumber(customerId, 3, "33-4-3-3"));
		
		HashMap<Integer, Integer> phoneNumberIds = add
				.addCustomerPhoneNumbers(customerPhoneNumbers);
				
		for(Integer customerPhoneNumberKey : customerPhoneNumbers.keySet()){
					
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(phoneNumberIds.get(customerPhoneNumberKey));

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_phone_numbers", 
					"phone_number_id", phoneNumberIds.get(customerPhoneNumberKey)));
		}
	}

    //@Ignore
	@Test
	public void testAddCustomerAddresses() throws DbException {

		String query = "select count(*) from customers_addresses where address_id = ?";
				
		HashMap<Integer, CustomerAddress> 
			customerAddresses = new HashMap<Integer, CustomerAddress>();	
		
		customerAddresses.put(1, new CustomerAddress(customerId, 1, "sta", "dca", "ade", "003"));
		customerAddresses.put(2, new CustomerAddress(customerId, 2, "xxxxxsta234", "dca", "ade", "004"));
		customerAddresses.put(3, new CustomerAddress(customerId, 3, "st232a", "dca", "ade", "006"));
		
		HashMap<Integer, Integer> addressIds = add.addCustomerAddresses(customerAddresses);
		
		for(Integer customerAddressesKey : customerAddresses.keySet()){
					
			ArrayList<Object> parameters = new ArrayList<Object>();

			parameters.add(addressIds.get(customerAddressesKey));

			assertTrue(existance.exist(query, parameters));

			rtd.add(new Record("customers_addresses", 
					"address_id", addressIds.get(customerAddressesKey)));
		}
	}
	
    @Ignore
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
