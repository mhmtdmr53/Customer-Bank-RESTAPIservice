package com.rest.spring.Api.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "sourceAccountId")
	private int sourceAccountId;
	
	@Column(name = "destAccountId")
	private int destinationAccountId;

	public Transaction() {
	
	}
	
	public Transaction(BigDecimal amount, int sourceAccountId, int destinationAccountId) {
		
		this.amount = amount;
		this.sourceAccountId = sourceAccountId;
		this.destinationAccountId = destinationAccountId;
	}
	
	

	public int getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(int sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(int destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	
	
	
	
}
