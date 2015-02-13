package error;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

import admin.SystemConfigurations;

public class Log {

	public static void logError(Throwable error) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		error.printStackTrace(pw);
		
		String data = sw.toString();				
		String time = (new Timestamp(System.currentTimeMillis())).toString();

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(
					new FileWriter(SystemConfigurations.getErrorFileName(), true));

			writer.write(time + ": " + data + "\n");
			writer.write("\n");			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {}
		}
	}
}
