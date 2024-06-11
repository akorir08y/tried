package com.example.tried.auth.personnel.tracing;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("transactions")
	private List<TransactionsItem> transactions;

	@JsonProperty("churchNumber")
	private String churchNumber;

	public Payload() {

	}

	public Payload(String churchName, List<TransactionsItem> transactions, String churchNumber) {
		this.churchName = churchName;
		this.transactions = transactions;
		this.churchNumber = churchNumber;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public List<TransactionsItem> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionsItem> transactions) {
		this.transactions = transactions;
	}

	public String getChurchNumber() {
		return churchNumber;
	}

	public void setChurchNumber(String churchNumber) {
		this.churchNumber = churchNumber;
	}
}