package com.levent.webservice.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.levent.webservice.logging.LogBase;
import com.levent.webservice.model.Customer;
import com.levent.webservice.model.response.CustomerData;
import com.levent.webservice.model.response.CustomerPayloadResponse;
import com.levent.webservice.model.response.CustomerResponse;
import com.levent.webservice.model.response.ErrorData;
import com.levent.webservice.model.response.ErrorType;
import com.levent.webservice.model.response.Meta;
import com.levent.webservice.repository.CustomerRepository;
import com.levent.webservice.repository.impl.CustomerRepositoryCacheImpl;

@Path("/customer-api")
public class CustomerController {

	private static Logger log = LogBase.getLogger(CustomerController.class);
	private CustomerRepository customerRepository;
	
	public CustomerController() {
		this.customerRepository = new CustomerRepositoryCacheImpl();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/test")
	public String testAPI() {
		log.info("testAPI is called");

		return "customer-api is working.";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers/{id}")
	public CustomerResponse getCustomerById(@PathParam("id") long id) {
		log.info("Received id: " + id);
		
		Customer customer;
		try {
			customer = customerRepository.find(id);
		} catch (Exception e) {
			log.info("Customer not found");
			customer = null;
		}
		
		CustomerData data = new CustomerData(customer);
		Meta meta;
		
		if(customer != null) {
			meta = new Meta()
					.setErrorData(null)
					.setSuccessful(true);
			
			data = new CustomerData(customer);
		} else {
			ErrorData errorData = new ErrorData()
					.setErrorType(ErrorType.NOT_FOUND)
					.setErrorMessage("Resource not found.");
			
			meta = new Meta()
					.setErrorData(errorData)
					.setSuccessful(false);
		}
				
		
		CustomerResponse customerResponse = new CustomerResponse()
			.setData(data)
			.setMeta(meta);
		
		
		return customerResponse;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers")
	public CustomerResponse getCustomers() {
		List<Customer> customers = null;
		try {
			customers = customerRepository.findAll();
		} catch (Exception e) {
			log.info("Customers not found");
		}
		
		CustomerData data = new CustomerData(customers);
		Meta meta;
		
		if(customers != null) {
			if(!customers.isEmpty()) {
				meta = new Meta()
						.setErrorData(null)
						.setSuccessful(true);
				
				data = new CustomerData(customers);
			} else {
				ErrorData errorData = new ErrorData()
						.setErrorType(ErrorType.NOT_FOUND)
						.setErrorMessage("Resource not found.");
				
				meta = new Meta()
						.setErrorData(errorData)
						.setSuccessful(false);
			}
		} else {
			ErrorData errorData = new ErrorData()
					.setErrorType(ErrorType.NOT_FOUND)
					.setErrorMessage("Resource not found.");
			
			meta = new Meta()
					.setErrorData(errorData)
					.setSuccessful(false);
		}
				
		
		CustomerResponse customerResponse = new CustomerResponse()
			.setData(data)
			.setMeta(meta);
		
		log.info("returning: " + customerResponse);
		
		return customerResponse;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers")
	public CustomerPayloadResponse createCustomer(Customer customer) {
		log.info("received: " + customer);

		Customer customerPersisted = null;
		ErrorData errorData = null;
		try {
			customerPersisted = customerRepository.create(customer);
		} catch (Exception e) {
			log.error("Customer not persisted");
			
			errorData = new ErrorData()
					.setErrorType(ErrorType.NOT_PERSISTED)
					.setErrorMessage(e.getMessage());
		}
		
		CustomerData data = new CustomerData(customer);
		Meta meta;
		
		if(customerPersisted != null) {
			meta = new Meta()
					.setErrorData(null)
					.setSuccessful(true);
			
			data = new CustomerData(customer);
		} else {
			meta = new Meta()
					.setErrorData(errorData)
					.setSuccessful(false);
		}
				
		
		CustomerPayloadResponse customerPostResponse = new CustomerPayloadResponse()
			.setData(data)
			.setMeta(meta);
		
		log.info("returning: " + customerPersisted);
		
		return customerPostResponse;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers/{id}")
	public CustomerPayloadResponse updateCustomer(@PathParam("id") long id, Customer customer) {
		log.info("received id: " + id + ", customer: "+ customer);
		
		customer.setId(id);
		boolean isSuccessful;
		
		CustomerData data = null;
		Meta meta = null;
		ErrorData errorData = null;
		
		try {
			isSuccessful = customerRepository.update(customer);
		} catch (Exception e) {
			isSuccessful = false;
			log.info("Cannot update id: " + id + " ,customer: " + customer);
			
			errorData = new ErrorData()
					.setErrorType(ErrorType.NOT_UPDATED)
					.setErrorMessage(e.getMessage());
		}
		
		data = new CustomerData(customer);
		
		if(isSuccessful) {
			meta = new Meta()
					.setErrorData(null)
					.setSuccessful(true);
			
			data = new CustomerData(customer);
		} else {
			meta = new Meta()
					.setErrorData(errorData)
					.setSuccessful(false);
		}
				
		
		CustomerPayloadResponse customerPostResponse = new CustomerPayloadResponse()
			.setData(data)
			.setMeta(meta);
		
		log.info("returning: " + customer);
		
		return customerPostResponse;
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers/{id}")
	public CustomerPayloadResponse deleteCustomer(@PathParam("id") long id) {
		log.info("received id: " + id);
		
		boolean isSuccessful;
		Meta meta = null;
		ErrorData errorData = null;
		
		try {
			isSuccessful = customerRepository.delete(id);
		} catch (Exception e) {
			isSuccessful = false;
			log.error("Cannot delete id: " + id);
			
			errorData = new ErrorData()
					.setErrorType(ErrorType.NOT_DELETED)
					.setErrorMessage(e.getMessage());
		}
		
		CustomerData data = null;
		
		if(isSuccessful) {
			meta = new Meta()
					.setErrorData(null)
					.setSuccessful(true);
			
		} else {
			meta = new Meta()
					.setErrorData(errorData)
					.setSuccessful(false);
		}
				
		CustomerPayloadResponse customerPostResponse = new CustomerPayloadResponse()
			.setData(data)
			.setMeta(meta);
		
		log.info("returning: " + customerPostResponse);
		
		return customerPostResponse;
	}
	
}
