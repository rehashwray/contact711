package validation;

import java.io.UnsupportedEncodingException;

import security.Cryptography;
import security.CryptographyException;

public class DataMember {

	private String type;
	private String value;	
	private String customerId;

	public DataMember(){}
	
	public DataMember(String type, String value, Integer customerId){		
		this.type = type;
		this.value = value;

		try {
			this.customerId = Cryptography.encrypt(customerId + "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (CryptographyException e) {
			e.printStackTrace();
		}
	}
	
	public String getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	public String getCustomerId() {
		return customerId;
	}		
	
	public int getCustomer_idDecripted() {
		
		try {
			return Integer.parseInt(Cryptography.decrypt(this.customerId));
		} catch (Exception e) {
			//handle not number case
		}
		
		return -1;
	}
}
