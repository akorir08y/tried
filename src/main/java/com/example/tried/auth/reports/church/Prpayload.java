package com.example.tried.auth.reports.church;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Prpayload{

	@JsonProperty("localChurchNumber")
	private String localChurchNumber;

	@JsonProperty("localChurchName")
	private String localChurchName;

	@JsonProperty("transactions")
	private List<TransactionsItem> transactions;

	public Prpayload() {

	}

	public Prpayload(String localChurchNumber, String localChurchName, List<TransactionsItem> transactions) {
		this.localChurchNumber = localChurchNumber;
		this.localChurchName = localChurchName;
		this.transactions = transactions;
	}

	public String getLocalChurchNumber() {
		return localChurchNumber;
	}

	public void setLocalChurchNumber(String localChurchNumber) {
		this.localChurchNumber = localChurchNumber;
	}

	public String getLocalChurchName() {
		return localChurchName;
	}

	public void setLocalChurchName(String localChurchName) {
		this.localChurchName = localChurchName;
	}

	public List<TransactionsItem> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionsItem> transactions) {
		this.transactions = transactions;
	}
}