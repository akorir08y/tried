package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDetails{

	@JsonProperty("sourceAccount")
	private String sourceAccount;

	@JsonProperty("destinationAccountNumber")
	private String destinationAccountNumber;

	@JsonProperty("sourceAccountNumber")
	private String sourceAccountNumber;

	@JsonProperty("destinationAccount")
	private String destinationAccount;
}