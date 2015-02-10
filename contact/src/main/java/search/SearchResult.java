package search;

import java.util.ArrayList;
import java.util.HashMap;

import tableModel.Customer;

public class SearchResult {

	private Integer customersFound = null;
	private ArrayList<Customer> customers = null;

	public SearchResult(){}
	
	public SearchResult(
			Integer customersFound, ArrayList<Customer> customers){
		this.customersFound = customersFound;
		this.customers = customers;
	}

	public Integer getCustomersFound() {
		return customersFound;
	}

	public void setCustomersFound(Integer customersFound) {
		this.customersFound = customersFound;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}			

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = new ArrayList<Customer>(customers.values());
	}
}
