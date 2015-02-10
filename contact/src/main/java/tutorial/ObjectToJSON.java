package tutorial;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJSON {
    
	public static void main(String[] args) {
    	 
	ObjectMapper mapper = new ObjectMapper();
 
	HashMap<String, String> hashMap = new HashMap<String, String>();
	
	hashMap.put("ba", "ban");
	hashMap.put("noche", "fria");
	
	try { 
		// display to console
		String json = mapper.writeValueAsString(hashMap);
		System.out.println("json: " + json);
 
	} catch (JsonGenerationException e) {
 
		e.printStackTrace();
 
	} catch (JsonMappingException e) {
 
		e.printStackTrace();
 
	} catch (IOException e) {
 
		e.printStackTrace();
 
	}
 
  }
}
