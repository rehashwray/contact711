package customerProfileManager;

import java.util.HashMap;

public class PrimaryKeys {

	private HashMap<Integer, Integer> customerEmailsId = null;
	private HashMap<Integer, Integer> customerPhoneNumbersId = null;
	private HashMap<Integer, Integer> customerAddressesId = null;
	
	public PrimaryKeys(){}
	
	public HashMap<Integer, Integer> getCustomerEmailsId() {
		return customerEmailsId;
	}
	public void setCustomerEmailsId(HashMap<Integer, Integer> customerEmailsId) {
		this.customerEmailsId = customerEmailsId;
	}
	
	public HashMap<Integer, Integer> getCustomerPhoneNumbersId() {
		return customerPhoneNumbersId;
	}
	public void setCustomerPhoneNumbersId(
			HashMap<Integer, Integer> customerPhoneNumbersId) {
		this.customerPhoneNumbersId = customerPhoneNumbersId;
	}
	
	public HashMap<Integer, Integer> getCustomerAddressesId() {
		return customerAddressesId;
	}
	public void setCustomerAddressesId(HashMap<Integer, Integer> customerAddressesId) {
		this.customerAddressesId = customerAddressesId;
	}	
}
