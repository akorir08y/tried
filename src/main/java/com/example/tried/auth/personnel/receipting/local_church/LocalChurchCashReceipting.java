package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalChurchCashReceipting{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}