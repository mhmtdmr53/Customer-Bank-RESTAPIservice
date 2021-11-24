package com.rest.spring.Api.DAO;

import java.util.List;

import com.rest.spring.Api.Entity.Transaction;

public interface TransactionDAO {

	String transferBetweenAccounts(int accountId,Transaction transaction);

	List<Transaction> getAllTransfers();

}
