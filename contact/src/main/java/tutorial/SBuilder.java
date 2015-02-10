package tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SBuilder {

	public static void main(String[] args){
		
		StringBuilder s = new StringBuilder("san antonio");
		
		System.out.println(s.delete(5, s.length()));
		
	}
}
