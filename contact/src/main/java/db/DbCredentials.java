package db;

import java.io.File;
import java.io.IOException;
import java.util.List;

import logger.Log;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class DbCredentials {

	private static String url;
	private static String user;
	private static String password;
	
	static {
		initialize();
	}
	
	private static void initialize() {
		
		File file = new File("DbCredentialsFile.txt");
		List<String> lines = null;
		try {
			lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			Log.logError(e);
			return;
		}

		url = lines.get(0);
		user = lines.get(1);
		password = lines.get(2);
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
