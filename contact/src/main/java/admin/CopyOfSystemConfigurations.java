package admin;

import com.google.common.collect.ImmutableMap;

public class CopyOfSystemConfigurations {

	private static String dbUrl;
	private static String dbUser;
	private static String dbPassword;

	private static ImmutableMap<String, String> emailCredentials;

	private static String securityKey;
	
	private static String errorFileName;

	static {
		initialize();
	}

	private static void initialize() {

		dbUrl = "";
		dbUser = "";
		dbPassword = "";

		emailCredentials = ImmutableMap.<String, String> builder()
				.put("emailAddress", "")
				.put("emailPassword", "")
				.build();
		
		securityKey = "";
		
		errorFileName = "";
	}

	public static String getDbUrl() {
		return dbUrl;
	}

	public static String getDbUser() {
		return dbUser;
	}

	public static String getDbPassword() {
		return dbPassword;
	}

	public static ImmutableMap<String, String> getEmailCredentials() {
		return emailCredentials;
	}

	public static String getSecurityKey() {
		return securityKey;
	}
	
	public static String getErrorFileName() {
		return errorFileName;
	}
}
