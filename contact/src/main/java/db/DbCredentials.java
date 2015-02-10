package db;

public class DbCredentials {

	private static String url;
	private static String user;
	private static String password;
	
	static {
		initialize();
	}
	
	private static void initialize() {
		
		url = "";
		user = "";
		password = "";
	}

	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPassword() {
		return password;
	}
}
