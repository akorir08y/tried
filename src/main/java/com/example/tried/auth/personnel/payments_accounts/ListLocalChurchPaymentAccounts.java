package com.example.tried.auth.personnel.payments_accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.Data;

@Data
public class ListLocalChurchPaymentAccounts{

	@JsonProperty("password")
	private String password;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("sessionNumber")
	private Long sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("user")
	private String user;
}