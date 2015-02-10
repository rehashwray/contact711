package tableModel;

public class CustomerEmail {

	private int customer_id;
	private int email_id;
	private String email;
	
	public CustomerEmail(){}
	
	public CustomerEmail(
			int customer_id, 
			int email_id, 
			String email){
		this.customer_id = customer_id;
		this.email_id = email_id;
		this.email = email;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getEmail_id() {
		return email_id;
	}
	public void setEmail_id(int email_id) {
		this.email_id = email_id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
