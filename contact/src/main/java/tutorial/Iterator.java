package tutorial;

import java.util.HashMap;

public class Iterator {

	public static void main(String[] args) {
		
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		hm.put(1, 10);
		hm.put(2, 20);
		
		for(Integer i : hm.keySet()){
			System.out.println(i + " : " + hm.get(i));
		}
	}
}
