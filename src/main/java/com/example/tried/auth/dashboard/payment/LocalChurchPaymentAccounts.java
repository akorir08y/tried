package com.example.tried.auth.dashboard.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchPaymentAccounts{

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