package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trustpayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("startDate")
	private String startDate;
}