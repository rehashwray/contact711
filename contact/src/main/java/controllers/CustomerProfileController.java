package controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tableModel.Customer;
import tableModel.CustomerProfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import customerProfileManager.Add;
import customerProfileManager.Delete;
import customerProfileManager.PrimaryKeys;
import customerProfileManager.Update;
import db.DbAccount;
import db.DbException;
import error.ErrorHandler;
import error.Log;

@Controller
public class CustomerProfileController extends ErrorHandler  {

	@RequestMapping(value = { "/AddCustomer" }, method = RequestMethod.GET)//should change other to post
	public String addCustomerProfile() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		String role = userDetail.getAuthorities().toArray()[0].toString();	
		if(role.equalsIgnoreCase("ROLE_ADMIN")){
			return "redirect:/SystemLogs";
		}
		
		return "AddCustomer";
	}
	
	@RequestMapping(value = { "/AddCustomerProfile" }, method = RequestMethod.POST)
	@ResponseBody
	public String addCustomerProfile(
			@RequestParam("customerProfileAdd") 
			String customerProfileAddJson) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		String role = userDetail.getAuthorities().toArray()[0].toString();	
		if(role.equalsIgnoreCase("ROLE_ADMIN")){
			return "redirect:/SystemLogs";
		}
		
		String username = userDetail.getUsername();		
		
		Integer user_id = null;
		try {
			user_id = new DbAccount().getUserId(username);
		} catch (DbException e) {
			Log.logError(e);
		}
		
		String result = "ok";		
		try {
			CustomerProfile customerProfileAdd = new ObjectMapper().readValue(
					customerProfileAddJson, CustomerProfile.class);
			
			Add customerProfileAddManager =  new Add();
			
			customerProfileAdd.getCustomer().setUserId(user_id);
			if(customerProfileAddManager.addCustomer(
					customerProfileAdd.getCustomer()) > -1){
				
				customerProfileAddManager.addCustomerProfile(customerProfileAdd);				
			}
		}catch (Exception e) {
			result = "failed";
			Log.logError(e);
		}		
        return result;
	}
	
	@RequestMapping(value = { "/ViewCustomer" }, method = RequestMethod.GET)
	public String viewCustomer() {		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		String role = userDetail.getAuthorities().toArray()[0].toString();	
		if(role.equalsIgnoreCase("ROLE_ADMIN")){
			return "redirect:/SystemLogs";
		}
		
		return "ViewCustomer";
	}
	
	@RequestMapping(value = { "/updateCustomerProfile" }, method = RequestMethod.POST)
	@ResponseBody
	public PrimaryKeys updateCustomerProfile(
			@RequestParam("customerProfileAdd") 
			String customerProfileAddJson, 
			@RequestParam("customerProfileUpdate") 
			String customerProfileUpdateJson,
			@RequestParam("customerProfileDelete") 
			String customerProfileDeleteJson) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		String role = userDetail.getAuthorities().toArray()[0].toString();	
		if(role.equalsIgnoreCase("ROLE_ADMIN")){
			return null;
		}
		
		CustomerProfile customerProfileAdd = null;
		CustomerProfile customerProfileUpdate = null;
		CustomerProfile customerProfileDelete = null;

		PrimaryKeys primaryKeys = null;
		try {
			customerProfileAdd = new ObjectMapper().readValue(
					customerProfileAddJson, CustomerProfile.class);
			
			customerProfileUpdate = new ObjectMapper().readValue(
					customerProfileUpdateJson, CustomerProfile.class);
			
			customerProfileDelete = new ObjectMapper().readValue(
					customerProfileDeleteJson, CustomerProfile.class);
			
			//customer might be null
			primaryKeys = new Update(
					customerProfileUpdate.getCustomer().getCustomer_idDecripted())
					.updateCustomerProfile(
							customerProfileUpdate, 
							customerProfileAdd);
			
			new Delete()
				.deleteCustomerProfile(customerProfileDelete);
		} 
		catch (Exception e) {
			Log.logError(e);
		}
		
        return primaryKeys;
	}
	
	@RequestMapping(value = { "/deleteCustomerProfile" }, method = RequestMethod.POST)
	@ResponseBody
	public String deleteCustomerProfile(
			@RequestParam("customer") String customerJson) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		String role = userDetail.getAuthorities().toArray()[0].toString();	
		if(role.equalsIgnoreCase("ROLE_ADMIN")){
			return "redirect:/SystemLogs";
		}
		
		Customer customer = null;

		String result = "ok";
		try {			
			customer = new ObjectMapper().readValue(
					customerJson, Customer.class);
			
			result = new Delete().deleteCustomers(Lists.newArrayList(customer.getCustomer_idDecripted()));
		} catch (Exception e) {
			Log.logError(e);
			result = "failed";
		}		
        
		return result;
	}
}
