package com.example.tried.auth.funds.non;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.HashMap;

public class Nonpayload{

	@JsonProperty("amount")
	private Object amount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("funds")
	private HashMap<String, Object> funds;

	@JsonProperty("startDate")
	private String startDate;

	public Nonpayload() {
	}

	public Nonpayload(Object amount, Integer sessionNumber, String endDate, HashMap<String, Object> funds, String startDate) {
		this.amount = amount;
		this.sessionNumber = sessionNumber;
		this.endDate = endDate;
		this.funds = funds;
		this.startDate = startDate;
	}

	public Object getAmount() {
		return amount;
	}

	public void setAmount(Object amount) {
		this.amount = amount;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public HashMap<String, Object> getFunds() {
		return funds;
	}

	public void setFunds(HashMap<String, Object> funds) {
		this.funds = funds;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}