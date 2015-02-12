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

	public Delete() {}

	public void deleteCustomerProfile(CustomerProfile customerProfileDelete) {

		if (customerProfileDelete.getCustomerEmails() != null)
			deleteCustomerEmails(customerProfileDelete.getCustomerEmails());

		if (customerProfileDelete.getCustomerPhoneNumbers() != null)
			deleteCustomerPhoneNumbers(customerProfileDelete
					.getCustomerPhoneNumbers());

		if (customerProfileDelete.getCustomerAddresses() != null)
			deleteCustomerAddresses(customerProfileDelete
					.getCustomerAddresses());
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
