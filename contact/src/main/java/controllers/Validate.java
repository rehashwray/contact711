package controllers;

import logger.ErrorHandler;
import logger.Log;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import validation.DataMember;
import validation.Unique;
import validation.ValidateDataMember;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller//change to rest controller
public class Validate extends ErrorHandler {

	@RequestMapping(value = { "/validate" }, method = RequestMethod.GET)
	@ResponseBody
	public Unique validate(
			@RequestParam("dataMember") String dataMemberJson) {
		
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}	
		
		DataMember dataMember = null;
		try {
			dataMember = new ObjectMapper().readValue(dataMemberJson, DataMember.class);
			
			ValidateDataMember validateDataMember = new ValidateDataMember();
			Unique u = validateDataMember.exist(dataMember, username); 
			return u;
		} catch (Exception e) {
			Log.logError(e);
		}
        return null;		
	}
}
