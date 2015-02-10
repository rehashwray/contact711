package tableModel;

public class User {

	private int userId;
	private String username;
	private String userPassword;
	
	public User(){}
	
	public User(
			int userId, 
			String username, 
			String userPassword) {

		this.userId = userId;
		this.username = username;
		this.userPassword = userPassword;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public boolean equalsByValue(Object object){
		User clone = (User) object;
		
		return this.userId == clone.userId 
				&& this.username.equalsIgnoreCase(clone.username)
				&& this.userPassword.equalsIgnoreCase(clone.userPassword);
	}
}
