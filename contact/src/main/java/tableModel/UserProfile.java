package tableModel;

public class UserProfile {

	private User user;
	private String role;
	
	public UserProfile(){}

	public UserProfile(User user, String role){
		this.user = user;
		this.role = role;
	}
	
	public User getUser() {
		return user;
	}

	public String getRole() {
		return role;
	}		
}
