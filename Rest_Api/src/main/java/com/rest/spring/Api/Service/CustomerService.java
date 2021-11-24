package com.rest.spring.Api.Service;

import java.util.List;

import com.rest.spring.Api.Entity.Account;
import com.rest.spring.Api.Entity.Customer;
import com.rest.spring.Api.Entity.Transaction;

public interface CustomerService {

	public List<Customer> getCustomers();
	
	public void saveCustomers(Customer customer);
	
	public void addAccount(int customerId,Account account);

	public List<Account> getAccounts(int customerId);


	public List<Account> getAllAccounts();

	public void deleteAccount(int customerId,int accountId);

	public String transferBetweenAccounts(int accountId,Transaction transaction);

	public Customer getCustomersById(int customerId);

	public List<Transaction> getAllTransfers();
}
