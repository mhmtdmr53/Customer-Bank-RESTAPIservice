package com.rest.spring.Api.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.spring.Api.Entity.Account;
import com.rest.spring.Api.Entity.Customer;
import com.rest.spring.Api.Exceptions.CustomerNotFoundException;


@Repository
public class AccountDAOImpl implements AccountDAO {

	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<Account> getAccounts(int customerId) {
		
		Session session=entityManager.unwrap(Session.class);
		
		
		Query<Account> query= session.createQuery("from Account where customer_customer_id = :customerId",Account.class);
		
		query.setParameter("customerId", customerId);
		
		List<Account> accounts= query.getResultList();
		
		return accounts;
		
		
	}

	@Override
	public void saveAccounts(int customerId, Account account) {
		
		Session session= entityManager.unwrap(Session.class);
		
		Customer customer=session.get(Customer.class, customerId);
		
		
		account.setCustomer(customer);
		
		session.save(account);

	}

	@Override
	public List<Account> getAllAccounts() {
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Account> query= session.createQuery("from Account",Account.class);
		
		
		
		List<Account> accounts= query.getResultList();
		
		return accounts;
		
		
	}

	@Override
	public void deleteAccount(int customerId,int accountId) {
		
		Session session=entityManager.unwrap(Session.class);
		
		Account account=session.get(Account.class,accountId);
		
		if (account==null || account.getCustomer().getId()!=customerId ) {
			throw new CustomerNotFoundException("There is no Account By this id: "+ customerId);
		}
		
		session.delete(account);
		
	}

}
