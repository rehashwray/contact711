package controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tableModel.User;
import accountManager.AddAccount;
import accountManager.DeleteAccount;
import accountManager.PasswordRecover;
import accountManager.UpdateAccount;

import com.fasterxml.jackson.databind.ObjectMapper;

import error.Log;

@Controller
public class Account extends ErrorHandler {
	
	@RequestMapping(value = {"/viewRegister"}, method = RequestMethod.GET)
	public String viewRegister(){

		return "Register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(
			@RequestParam("user") String userJson){

		User user = null;
		try {
			user = new ObjectMapper().readValue(userJson, User.class);
			
			AddAccount userAddManager = new AddAccount();
			userAddManager.addAccount(user);
		} catch (Exception e) {
			Log.logError(e);
		}		
        return "redirect:login";
	}	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){

		return "Login";
	}
	
	@RequestMapping(value = {"/forgotPassword"}, method = RequestMethod.GET)
	public String forgotPassword(){

		return "ForgotPassword";
	}
	
	@RequestMapping(value = { "/recoverPassword" }, method = RequestMethod.GET)
	public String recoverPassword(
			@RequestParam("username") String username) {
		
		PasswordRecover.recoverPassword(username);
		
        return "redirect:login";
	}
	
	@RequestMapping(value = { "/EditAccount" }, method = RequestMethod.GET)
	public String editAccount() {
		
		return "EditAccount";
	}
	
	@RequestMapping(value = { "/UpdateAccount" }, method = RequestMethod.GET)
	public String editAccount(
			@RequestParam("user") 
			String userJson) {
		
		User user = null;
		try {
			user = new ObjectMapper().readValue(userJson, User.class);
			
			UpdateAccount userUpdateManager = new UpdateAccount();
			userUpdateManager.updateUser(user);
		} catch (Exception e) {
			Log.logError(e);
		}
        return "redirect:logout";
	}
	
	@RequestMapping(value = { "/DeleteAccount" }, method = RequestMethod.GET)
	public String deleteAccount() {
		
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}
		
		try {			
			new DeleteAccount().deleteAccount(username);
		} catch (Exception e) {
			Log.logError(e);
		}
        return "redirect:logout";
	}	
}
