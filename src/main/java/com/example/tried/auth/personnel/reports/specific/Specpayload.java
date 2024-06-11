package com.example.tried.auth.personnel.reports.specific;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Specpayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("transactions")
	private List<TransactionsItem> transactions;

	@JsonProperty("churchNumber")
	private String churchNumber;

	public Specpayload() {
	}

	public Specpayload(String churchName, List<TransactionsItem> transactions, String churchNumber) {
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