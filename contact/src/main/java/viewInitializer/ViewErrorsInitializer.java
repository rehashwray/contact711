package viewInitializer;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import error.Log;

public class ViewErrorsInitializer {

	public static String initialize(){

		String errors = null;
		try {
			ArrayList<String> lines = (ArrayList<String>) Files.readLines(
					new File("ErrorsLog.txt"), Charset.forName("utf-8"));
			
			errors = new ObjectMapper().writeValueAsString(lines);
		} catch (IOException e) {
			Log.logError(e);
		}		
		return errors;
	}
}
