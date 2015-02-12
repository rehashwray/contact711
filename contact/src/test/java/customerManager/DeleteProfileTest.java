package customerManager;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import tableModel.Customer;
import tableModel.CustomerAddress;
import tableModel.CustomerEmail;
import tableModel.CustomerPhoneNumber;

import com.google.common.collect.Lists;

import customerProfileManager.Add;
import customerProfileManager.DeleteProfile;
import db.DbException;
import db.DbExistance;

public class DeleteProfileTest {

	private Add add = null;
	private DeleteProfile deleteProfile = null;

	private DbExistance existance = null;

	private Integer customerId = 16;

	@Before
	public void setUp() throws Exception {
		
		add = new Add(customerId);
		deleteProfile = new DeleteProfile();

		existance = new DbExistance();
	}

	@Test
	public void testDeleteCustomer() throws DbException {

		String query = "select count(*) from customers where customer_id = ?";

		Customer customer = new Customer(-1, -1, "marvin", "cas");
		Integer customerId = add.addCustomer(customer);

		deleteProfile.deleteCustomerProfiles(Lists.newArrayList(customerId));

		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(customerId);

		assertFalse(existance.exist(query, parameters));
	}

	@Test
	public void testDeleteCustomerEmails() throws DbException {
		
		String query = "select count(*) from customers_emails where customer_id = ?";

		HashMap<Integer, CustomerEmail> customerEmails = new HashMap<Integer, CustomerEmail>();

		customerEmails.put(1, new CustomerEmail(customerId, 1, "bon"));
		customerEmails.put(2, new CustomerEmail(customerId, 2, "nfl"));
		customerEmails.put(3, new CustomerEmail(customerId, 3, "qu"));

		add.addCustomerEmails(customerEmails);

		deleteProfile.deleteCustomerEmails(Lists.newArrayList(customerId));

		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(customerId);

		assertFalse(existance.exist(query, parameters));
	}

	@Test
	public void testDeleteCustomerPhoneNumbers() throws DbException {

		String query = "select count(*) from customers_phone_numbers where customer_id = ?";

		HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers = new HashMap<Integer, CustomerPhoneNumber>();

		customerPhoneNumbers.put(1, new CustomerPhoneNumber(customerId, 1, "783"));
		customerPhoneNumbers.put(2, new CustomerPhoneNumber(customerId, 2, "232183"));
		customerPhoneNumbers.put(3, new CustomerPhoneNumber(customerId, 3, "1231783"));
		
		add.addCustomerPhoneNumbers(customerPhoneNumbers);

		deleteProfile.deleteCustomerPhoneNumbers(Lists.newArrayList(customerId));

		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(customerId);

		assertFalse(existance.exist(query, parameters));
	}

	@Test
	public void testDeleteCustomerAddresses() throws DbException {

		String query = "select count(*) from customers_addresses where customer_id = ?";

		HashMap<Integer, CustomerAddress> customerAddresses = new HashMap<Integer, CustomerAddress>();

		customerAddresses.put(1, new CustomerAddress(
				customerId, 1, "sta", "dca", "ade", "003"));
		customerAddresses.put(2, new CustomerAddress(
				customerId, 2, "xxxxxsta234", "dca", "ade", "004"));
		customerAddresses.put(3, new CustomerAddress(
				customerId, 3, "st232a", "dca", "ade", "006"));

		add.addCustomerAddresses(customerAddresses);

		deleteProfile.deleteCustomerAddresses(Lists.newArrayList(customerId));

		ArrayList<Object> parameters = new ArrayList<Object>();

		parameters.add(customerId);

		assertFalse(existance.exist(query, parameters));
	}

}
