package tableModel;

import security.Cryptography;

public class Customer {

	private int userId;
	private String customer_id;
	private String customer_name;
	private String last_name;
	
	public Customer(){}
	
	public Customer(
			int userId,
			int customer_id, 
			String customer_name, 
			String last_name) {
		
		this.userId = userId;
		
		try {
			this.customer_id = Cryptography.encrypt(customer_id + "");
		} catch (Exception e) {
			//
		}

		this.customer_name = customer_name;
		this.last_name = last_name;
	}
	
	public int getUserId() {
		return userId;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public String getLast_name() {
		return last_name;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCustomer_idDecripted() {
		
		if (customer_id.equalsIgnoreCase("-1")) {
			return -1;
		}
		
		Integer value = null;
		try {
			value = Integer.parseInt(Cryptography.decrypt(customer_id));
		} catch (Exception e) {
			//
		}		
		return value;
	}
}
