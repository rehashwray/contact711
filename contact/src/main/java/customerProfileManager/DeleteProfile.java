package customerProfileManager;

import java.util.ArrayList;

import db.DbException;
import db.DbUpdate;
import error.Log;

public class DeleteProfile {

	protected DbUpdate dbUpdate = new DbUpdate();

	public DeleteProfile(){}
	
	public void deleteCustomerProfiles(ArrayList<Integer> customerIds) {
		
		deleteCustomer(customerIds);
		deleteCustomerEmails(customerIds);
		deleteCustomerPhoneNumbers(customerIds);
		deleteCustomerAddresses(customerIds);		
	}

	public void deleteCustomer(ArrayList<Integer> customerIds) {

		for (Integer id : customerIds) {

			String queryCustomer = "delete from customers where customer_id = ?";

			ArrayList<Object> customerParameters = new ArrayList<Object>();

			customerParameters.add(id);

			try {
				dbUpdate.updateDb(queryCustomer, customerParameters, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}

	public void deleteCustomerEmails(ArrayList<Integer> customerIds) {

		for (Integer id : customerIds) {

			String query = "delete from customers_emails where customer_id = ?";

			ArrayList<Object> parametersCustomerEmails = new ArrayList<Object>();

			parametersCustomerEmails.add(id);

			try {
				dbUpdate.updateDb(
						query, parametersCustomerEmails, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}

	public void deleteCustomerPhoneNumbers(ArrayList<Integer> customerIds) {

		for (Integer id : customerIds) {

			String queryCustomerPhoneNumbers = "delete from customers_phone_numbers "
					+ "where customer_id = ?";

			ArrayList<Object> parametersCustomerPhoneNumbers = new ArrayList<Object>();

			parametersCustomerPhoneNumbers.add(id);

			try {
				dbUpdate.updateDb(
						queryCustomerPhoneNumbers, parametersCustomerPhoneNumbers, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}

	public void deleteCustomerAddresses(ArrayList<Integer> customerIds) {

		for (Integer id : customerIds) {

			String queryCustomerAddresses = "delete from customers_addresses "
					+ "where customer_id = ?";

			ArrayList<Object> parametersCustomerAddresses = new ArrayList<Object>();

			parametersCustomerAddresses.add(id);

			try {
				dbUpdate.updateDb(
						queryCustomerAddresses, parametersCustomerAddresses, "customer_id");
			} catch (DbException e) {
				Log.logError(e);
			}
		}
	}
}
