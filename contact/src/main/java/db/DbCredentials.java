package db;

public class DbCredentials {

	private static String url;
	private static String user;
	private static String password;
	
	static {
		initialize();
	}
	
	private static void initialize() {
		

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
