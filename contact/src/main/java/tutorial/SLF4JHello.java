package tutorial;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SLF4JHello {
	
	public static void main(String[] args) {
		try {
			String s = null;
			s.length();
		} catch (Throwable t) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			
			t.printStackTrace(pw);
			sw.toString();
			
			System.out.println(sw);
			//System.out.println(ps.toString());
		}
	}
}
