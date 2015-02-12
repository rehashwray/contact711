package error;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

public class Log {

	private static File logFile = new File("ErrorsLog.txt");

	public static void logError(Throwable error) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		error.printStackTrace(pw);
		
		String data = sw.toString();				
		String time = (new Timestamp(System.currentTimeMillis())).toString();

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(logFile, true));

			writer.write(time + ": " + data + "\n");
			writer.write("\n");			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}
}
