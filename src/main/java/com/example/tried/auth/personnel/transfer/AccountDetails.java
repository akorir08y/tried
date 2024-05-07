package com.example.tried.auth.personnel.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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