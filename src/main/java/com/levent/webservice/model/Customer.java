package com.levent.webservice.model;

public class Customer {
	
	private long id;
	private String firstName;
	private String lastName;
	private int age;
	private boolean isRegular;
	
	public Customer() {}
	
	public Customer(final Customer c) {
		this.id = c.id;
		this.firstName = c.firstName;
		this.lastName = c.lastName;
		this.age = c.age;
		this.isRegular = c.isRegular;
	}

	public long getId() {
		return id;
	}

	public Customer setId(final long id) {
		this.id = id;
		
		return new Customer(this);
	}

	public String getFirstName() {
		return firstName;
	}

	public Customer setFirstName(final String firstName) {
		this.firstName = firstName;
		
		return new Customer(this);
	}

	public String getLastName() {
		return lastName;
	}

	public Customer setLastName(final String lastName) {
		this.lastName = lastName;
		
		return new Customer(this);
	}

	public int getAge() {
		return age;
	}

	public Customer setAge(final int age) {
		this.age = age;
		
		return new Customer(this);
	}

	public boolean isRegular() {
		return isRegular;
	}

	public Customer setRegular(final boolean isRegular) {
		this.isRegular = isRegular;
		
		return new Customer(this);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", isRegular=" + isRegular + "]";
	}

}
