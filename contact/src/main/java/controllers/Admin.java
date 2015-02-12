package controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import error.ErrorHandler;

@Controller//change to rest controller
public class Admin extends ErrorHandler {

	@RequestMapping(value = { "/SystemLogs" }, method = RequestMethod.GET)
	public String viewErrors() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserDetails userDetail = (UserDetails) auth.getPrincipal();		
		String role = userDetail.getAuthorities().toArray()[0].toString();	
				
		if(role.equalsIgnoreCase("ROLE_ADMIN")){
	        return "SystemLogs";		
		}		
		return "redirect:/";		
	}
	
}
