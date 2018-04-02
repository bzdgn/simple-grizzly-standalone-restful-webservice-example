package com.levent.webservice.model.response;

import java.util.ArrayList;
import java.util.List;

import com.levent.webservice.model.Customer;

public class CustomerData {
	
	private List<Customer> customers;
	
	public CustomerData() {}
	
	public CustomerData(Customer customer) {
		this.customers = new ArrayList<>();
		this.customers.add(customer);
	}
	
	public CustomerData(final List<Customer> customers) {
		this.customers = customers;
	}

	public List<Customer> getCustomers() {
		final List<Customer> newCustomers = new ArrayList<>(this.customers);
		
		return newCustomers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "CustomerData [customers=" + customers + "]";
	}
	
}
