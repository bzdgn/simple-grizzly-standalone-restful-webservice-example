package com.levent.webservice.model.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.levent.webservice.model.Customer;
import com.levent.webservice.model.repository.CustomerRepository;

public class CustomerRepositoryCacheImpl implements CustomerRepository {
	
	private static List<Customer> customers = new ArrayList<Customer>();
	private static int idIndex = 1;
	
	static {
		generateInitialCustomers();
	}
	
	@Override
	public Customer find(long id) throws Exception {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getId() == id) {
				return customers.get(i);
			}
		}
		
		return null;
	}

	@Override
	public List<Customer> findAll() throws Exception {
		return customers;
	}

	@Override
	public Customer create(Customer customer) throws Exception {
		Customer customerToBePersisted = new Customer(customer).setId(idIndex++);
		customers.add(customerToBePersisted);
		
		return customerToBePersisted;
	}

	@Override
	public boolean update(Customer customer) throws Exception {
		long id = customer.getId();
		
		for(final ListIterator<Customer> it = customers.listIterator(); it.hasNext();) {
			if(it.next().getId() == id) {
				it.set(customer);
				
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean delete(long id) throws Exception {
		Customer customerToBeDeleted = null;
		
		for(Customer customer: customers) {
			if(customer.getId() == id) {
				customerToBeDeleted = customer;
				break;
			}
		}
		customers.remove(customerToBeDeleted);
		
		idIndex--;
		
		return true;
	}
	
	private static void generateInitialCustomers() {
		Customer customer1 = new Customer()
				.setId(1)
				.setFirstName("Levent")
				.setLastName("Divilioglu")
				.setAge(31)
				.setRegular(true);
		
		Customer customer2 = new Customer()
				.setId(2)
				.setFirstName("John")
				.setLastName("Doe")
				.setAge(44)
				.setRegular(false);
		
		Customer customer3 = new Customer()
				.setId(3)
				.setFirstName("Jane")
				.setLastName("Doe")
				.setAge(24)
				.setRegular(true);
		
		Customer customer4 = new Customer()
				.setId(4)
				.setFirstName("Fawn")
				.setLastName("Smith")
				.setAge(66)
				.setRegular(false);
		
		customers.add(customer1);
		idIndex++;
		customers.add(customer2);
		idIndex++;
		customers.add(customer3);
		idIndex++;
		customers.add(customer4);
		idIndex++;
	}

}
