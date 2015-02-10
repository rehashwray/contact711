package controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import search.SearchQueryParsed;
import search.SearchResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import db.DbCustomers;
import error.ErrorHandler;
import error.Log;

@Controller
public class Search extends ErrorHandler {
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home() {
		
		return "Search";
	}
	
	@RequestMapping(value = { "/Search" }, method = RequestMethod.GET)
	@ResponseBody
	public SearchResult search(
			@RequestParam("sqpJson") String sqpJson) {
		
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}		
		
		SearchResult searchResult = new SearchResult(0, null);
		try {
			SearchQueryParsed sqp = new ObjectMapper().readValue(sqpJson, SearchQueryParsed.class);			
			
			searchResult = (new DbCustomers()).searchCustomers(username, sqp);						
		} catch (Exception e) {
			Log.logError(e);			
		};				
		
		return searchResult;
	}
}
