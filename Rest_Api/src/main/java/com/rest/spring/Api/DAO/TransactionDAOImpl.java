package com.rest.spring.Api.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.rest.spring.Api.Entity.Account;
import com.rest.spring.Api.Entity.Transaction;

import com.rest.spring.Api.Exceptions.TransferNotAllowedException;


@Repository
public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	private EntityManager entityManager;
	

	
	
	@Override
	public String transferBetweenAccounts(int accountId,Transaction transaction) {
		
		Session session=entityManager.unwrap(Session.class);
		
		Account accountSource=session.get(Account.class, accountId);
	
		if (accountSource.getBalance().compareTo(transaction.getAmount())==-1) {
			throw new TransferNotAllowedException("This exchange is not allowed because these amount of money is bigger than your balance!!"); 
		}
		
		
		
		
		Account accountDestination=session.get(Account.class, transaction.getDestinationAccountId());
		
		if (accountSource.getCurrency().equals(accountDestination.getCurrency().trim())) 
		{
			accountSource.setBalance(accountSource.getBalance().subtract(transaction.getAmount()));
			
			accountDestination.setBalance(accountDestination.getBalance().add(transaction.getAmount()));
			
			
			
		} else {
			throw new TransferNotAllowedException("This exchange is not allowed between different currency!!: "+accountSource.getCurrency()+" to "+accountDestination.getCurrency());
		}
		
		
		
		transaction.setSourceAccountId(accountId);
		session.save(transaction);
		
		if (accountSource.getCustomer().getId()!=accountDestination.getCustomer().getId()) {
		
		return "Money is Transferred "+accountSource.getCustomer().getFirstName()+" to "+accountDestination.getCustomer().getFirstName();
		}
		else {
			return "Transfer is Succesfully Done!!";
		}
		
		
	}

	@Override
	public List<Transaction> getAllTransfers() {
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Transaction> query=session.createQuery("from Transaction",Transaction.class);
		
		List<Transaction> transactions=query.getResultList();
		
		return transactions;
	}

}
