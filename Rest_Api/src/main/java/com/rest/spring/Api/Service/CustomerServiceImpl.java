package com.rest.spring.Api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.spring.Api.DAO.AccountDAO;
import com.rest.spring.Api.DAO.CustomerDAO;
import com.rest.spring.Api.DAO.TransactionDAO;
import com.rest.spring.Api.Entity.Account;
import com.rest.spring.Api.Entity.Customer;
import com.rest.spring.Api.Entity.Transaction;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private TransactionDAO transactionDAO;


	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void addAccount(int customerId,Account account) {
		
		accountDAO.saveAccounts(customerId,account);
	}
	
	@Override
	@Transactional
	public void saveCustomers(Customer customer) {
		customerDAO.saveCustomers(customer);

	}

	@Override
	@Transactional
	public List<Account> getAccounts(int customerId) {
		return accountDAO.getAccounts(customerId);
		
	}

	@Override
	@Transactional
	public List<Account> getAllAccounts() {
		
		return accountDAO.getAllAccounts();
	}

	@Override
	@Transactional
	public void deleteAccount(int customerId, int accountId) {
		
		 accountDAO.deleteAccount(customerId,accountId);
	}

	@Override
	@Transactional
	public String transferBetweenAccounts(int accountId,Transaction transaction) {
		
		return transactionDAO.transferBetweenAccounts(accountId,transaction);
	}

	@Override
	@Transactional
	public Customer getCustomersById(int customerId) {
		
		return customerDAO.getCustomersById(customerId);
	}

	@Override
	@Transactional
	public List<Transaction> getAllTransfers() {
		
		return transactionDAO.getAllTransfers();
	}

}
