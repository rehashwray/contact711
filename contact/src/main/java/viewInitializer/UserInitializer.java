package viewInitializer;

import logger.ErrorHandler;
import logger.Log;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import tableModel.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import db.DbAccount;

public class UserInitializer extends ErrorHandler{
	
	public UserInitializer(){}
	
	public static String initialize(){
		
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if (!(auth instanceof AnonymousAuthenticationToken)) {//should be outside if
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}
		
		String json = null;
	    try {
			User user = new DbAccount().getUser(username);	

			json = new ObjectMapper().writeValueAsString(user);
		} catch (Exception e) {
			Log.logError(e);
		} 
		return json;
	}
}
