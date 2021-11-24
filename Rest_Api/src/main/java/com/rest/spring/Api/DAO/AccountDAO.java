package com.rest.spring.Api.DAO;

import java.util.List;

import com.rest.spring.Api.Entity.Account;

public interface AccountDAO {

	public List<Account> getAccounts(int customerId);
	
	public List<Account> getAllAccounts();
	
	public void saveAccounts(int customerId,  Account account);

	public void deleteAccount(int customerId,int accountId);
}
