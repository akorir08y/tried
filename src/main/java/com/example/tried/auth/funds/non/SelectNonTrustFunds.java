package com.example.tried.auth.funds.non;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectNonTrustFunds{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}