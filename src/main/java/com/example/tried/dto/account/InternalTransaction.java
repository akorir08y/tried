package com.example.tried.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;


public class InternalTransaction{

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("Occassion")
	private String occassion;

	public InternalTransaction() {

	}

	public InternalTransaction(String transactionID, int amount, String occassion) {
		this.transactionID = transactionID;
		this.amount = amount;
		this.occassion = occassion;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getOccassion() {
		return occassion;
	}

	public void setOccassion(String occassion) {
		this.occassion = occassion;
	}
}