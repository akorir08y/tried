package com.example.tried.auth.personnel.transfer;

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

	public AccountDetails() {

	}

	public AccountDetails(String sourceAccount, String destinationAccountNumber, String sourceAccountNumber, String destinationAccount) {
		this.sourceAccount = sourceAccount;
		this.destinationAccountNumber = destinationAccountNumber;
		this.sourceAccountNumber = sourceAccountNumber;
		this.destinationAccount = destinationAccount;
	}

	public String getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
}