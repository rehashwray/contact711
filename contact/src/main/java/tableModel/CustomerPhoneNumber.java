package tableModel;

public class CustomerPhoneNumber {

	private int customer_id;
	private int phone_number_id;
	private String phone_number;

	public CustomerPhoneNumber(){}
	
	public CustomerPhoneNumber(
			int customer_id, 
			int phone_number_id, 
			String phone_number) {
		
		this.customer_id = customer_id;
		this.phone_number_id = phone_number_id;
		this.phone_number = phone_number;
	}

	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getPhone_number_id() {
		return phone_number_id;
	}
	public void setPhone_number_id(int phone_number_id) {
		this.phone_number_id = phone_number_id;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}
