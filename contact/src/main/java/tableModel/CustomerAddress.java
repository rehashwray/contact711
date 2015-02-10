package tableModel;

public class CustomerAddress {

	private int customer_id;
	private int address_id;
	private String street;
	private String city;
	private String state;
	private String zip_code;
	
	public CustomerAddress(){}
	
	public CustomerAddress(int customer_id, int address_id, String street, 
			String city, String state, String zip_code) {
		this.customer_id = customer_id;
		this.address_id = address_id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
	
}
