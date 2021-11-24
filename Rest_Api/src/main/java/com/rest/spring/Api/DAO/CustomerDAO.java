package com.rest.spring.Api.DAO;

import java.util.List;

import com.rest.spring.Api.Entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();
	
	public void saveCustomers(Customer customer);

	public Customer getCustomersById(int customerId);
}
