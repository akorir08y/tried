package com.example.tried.dto.kra;

import com.fasterxml.jackson.annotation.JsonProperty;



public class InternalTaxRequest{

	@JsonProperty("Amount")
	private String amount;

	public InternalTaxRequest() {

	}

	public InternalTaxRequest(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}