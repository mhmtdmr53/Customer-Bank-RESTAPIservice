package com.rest.spring.Api.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.spring.Api.Entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	private EntityManager entityManager;
	
	
	@Autowired	
	public CustomerDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Customer> getCustomers() {
		
		Session session=entityManager.unwrap(Session.class);
		
		
		Query<Customer> query= session.createQuery("from Customer",Customer.class);
		
		
		List<Customer> customers= query.getResultList();
		
		return customers;
		
	}

	

	@Override
	public void saveCustomers(Customer customer) {
		
		Session session= entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomersById(int customerId) {
		
		Session session= entityManager.unwrap(Session.class);
		
		Customer customer=session.get(Customer.class, customerId);
		
		
		return customer;
	}

}
