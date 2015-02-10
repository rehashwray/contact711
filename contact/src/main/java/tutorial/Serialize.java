package tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;

public class Serialize {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String... args) throws IOException,
			ClassNotFoundException {
		HashMap<String, Object> fileObj = new HashMap<String, Object>();

		ArrayList<String> cols = new ArrayList<String>();
		cols.add("a");
		cols.add("b");
		cols.add("c");
		fileObj.put("mylist", cols);
		{
			File file = new File("temp");
			FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(fileObj);
			s.close();
		}
		File file = new File("temp");
		FileInputStream f = new FileInputStream(file);
		ObjectInputStream s = new ObjectInputStream(f);
		HashMap<String, Object> fileObj2 = (HashMap<String, Object>) s
				.readObject();
		s.close();
		
		ArrayList<String> al = (ArrayList<String>) fileObj2.get("mylist");
		

	}
}
