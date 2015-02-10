package tableModel;

import java.util.HashMap;

public class CustomerProfile {
	
	private Customer customer;
	private HashMap<Integer, CustomerEmail> customerEmails;
	private HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers;
	private HashMap<Integer, CustomerAddress>  customerAddresses;
	
	public CustomerProfile() {}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public HashMap<Integer, CustomerEmail> getCustomerEmails() {
		return customerEmails;
	}
	public void setCustomerEmails(HashMap<Integer, CustomerEmail> customerEmails) {
		this.customerEmails = customerEmails;
	}
	
	public HashMap<Integer, CustomerPhoneNumber> getCustomerPhoneNumbers() {
		return customerPhoneNumbers;
	}
	public void setCustomerPhoneNumbers(
			HashMap<Integer, CustomerPhoneNumber> customerPhoneNumbers) {
		this.customerPhoneNumbers = customerPhoneNumbers;
	}
	
	public HashMap<Integer, CustomerAddress> getCustomerAddresses() {
		return customerAddresses;
	}
	public void setCustomerAddresses(
			HashMap<Integer, CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}	
}
