package com.rest.spring.Api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.spring.Api.Entity.Account;
import com.rest.spring.Api.Entity.Customer;
import com.rest.spring.Api.Entity.Transaction;
import com.rest.spring.Api.Exceptions.CustomerBadIndexException;
import com.rest.spring.Api.Exceptions.CustomerNotFoundException;
import com.rest.spring.Api.Service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class mainRestController {

	
	
	@Autowired
	private CustomerService customerService;
	
		@ApiOperation(value = "Returns List of Customers with their values")
		@GetMapping("/customers")
		public List<Customer> getCustomers()
		{
			
			List<Customer> customers=customerService.getCustomers();
			
			if (customers.isEmpty()) {
				throw new CustomerNotFoundException("Customer Not Found..");
			}
			return customers;
			
		}
	
		@ApiOperation(value = "Adds a new Customer")
		@PostMapping("/customers")
		public Customer addCustomer(@RequestBody Customer customer) 
		{
			// also just in case they pass an id in JSON ... set id to 0
			
			customer.setId(0);
			
			if (isItValid(customer.getTcNo())==false) {
				throw new CustomerBadIndexException("Customer TcNo must be 11 digit number");
			}
			
			customerService.saveCustomers(customer);
			
			return customer;
		}
		
		@ApiOperation(value = "Update the Customer (tcNo is not updatable)")
		@PutMapping("/customers")
		public Customer updateCustomer(@RequestBody Customer customer)
		{
			
			
			 customerService.saveCustomers(customer);
			
			return customer;
			
		}
		
		@ApiOperation(value = "Create an account for a user")
		@PostMapping("/customers/{customerId}/accounts")
		public Account addAccount(@PathVariable int customerId ,@RequestBody Account account)
		{
			Customer customer=customerService.getCustomersById(customerId);
			if (customer==null) {
				throw new CustomerNotFoundException("Customer id not found!! "+ customerId);
			}
			
			customerService.addAccount(customerId,account);
			
			return account;
			
		}
		
		@ApiOperation(value = "Brings the accounts by the customerId")
		@GetMapping("/customers/{customerId}/accounts")
		public List<Account> getAccounts(@PathVariable int customerId)
		{
			List<Account> accounts= customerService.getAccounts(customerId);
			if (accounts.isEmpty()) {
				throw new CustomerNotFoundException("No Account Found by this id.. "+ customerId);
			}
			
			return accounts;
			
		}
		
		@ApiOperation(value = "Brings all accounts (Only Admin can reach this method)")
		@GetMapping("/customers/all/accounts")
		public List<Account> getAccounts()
		{
			return customerService.getAllAccounts();
			
			
		}
		
		@ApiOperation(value = "Delete an account by AccountId which belongs to CustomerId")
		@DeleteMapping("/customers/{customerId}/accounts/{accountId}")
		public void deleteAccount(@PathVariable int customerId,@PathVariable int accountId)
		{
			
			
			 customerService.deleteAccount(customerId,accountId);
					
		}
		
		@ApiOperation(value = "Money is transferred to the destination Account from Source Account")
		@PostMapping("/customers/{customerId}/accounts/{accountId}/transfer")
		public ResponseEntity<String> transferBetweenAccounts(@PathVariable int accountId,@RequestBody Transaction transaction)
		{
			String value=customerService.transferBetweenAccounts(accountId,transaction);
			
			return ResponseEntity.status(HttpStatus.OK).body(value);
		}
		
		@ApiOperation(value = "Brings all transfers between accounts (Only Admin can reach this method)")
		@GetMapping("/customers/all/transfers")
		public List<Transaction> getAllTransfers()
		{
			return customerService.getAllTransfers();
			
		}
		
		
		private  boolean isItValid(long tcNo) {
			int sayac=0;
			
			while (tcNo!=0) 
			{
				tcNo /=10;
				++sayac;
				
			}
			
			if (sayac==11) {
				return true;
			}
			else {
				return false;
			}
			
		}
		
		
}
