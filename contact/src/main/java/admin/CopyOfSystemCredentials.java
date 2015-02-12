package admin;

import com.google.common.collect.ImmutableMap;

public class CopyOfSystemCredentials {

	private static String dbUrl;
	private static String user;
	private static String password;

	private static ImmutableMap<String, String> emailCredentials;

	private static String securityKey;

	static {
		initialize();
	}

	private static void initialize() {

		dbUrl = "";
		user = "";
		password = "";

		emailCredentials = ImmutableMap.<String, String> builder()
				.put("", "").build();
		
		securityKey = "";
	}

	public static String getDbUrl() {
		return dbUrl;
	}

	public static String getUser() {
		return user;
	}

	public static String getPassword() {
		return password;
	}

	public static ImmutableMap<String, String> getEmailCredentials() {
		return emailCredentials;
	}

	public static String getSecurityKey() {
		return securityKey;
	}
}
