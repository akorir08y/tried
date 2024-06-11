package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountabilityStatus{

	@JsonProperty("status")
	private String status;

	public AccountabilityStatus() {
	}

	public AccountabilityStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}