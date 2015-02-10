package tutorial;

import java.util.ArrayList;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;


public class Array {
	
	public static void main(String[] args) {

		int i0 = 4;
		Integer i1 = i0;
		int i2 = i1;
		
		ArrayList<Object> l = new ArrayList<Object>();
		l.add("stringdd");
		l.add(3);
		
		
		System.out.println(l.get(0).getClass().toString().contains("Integer"));
		System.out.println(l.get(1).getClass().toString().contains("String"));
		
		System.out.println(i0);
		System.out.println(i1);
		System.out.println(i2);
	}

}
