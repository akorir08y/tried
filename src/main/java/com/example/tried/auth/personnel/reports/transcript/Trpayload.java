package com.example.tried.auth.personnel.reports.transcript;

import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Trpayload{

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("transactions")
	private List<TransactionItem> transactions;

	public Trpayload() {
	}

	public Trpayload(String conferenceName, String conferenceNumber, List<TransactionItem> transactions) {
		this.conferenceName = conferenceName;
		this.conferenceNumber = conferenceNumber;
		this.transactions = transactions;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	public List<TransactionItem> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionItem> transactions) {
		this.transactions = transactions;
	}
}