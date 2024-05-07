package com.example.tried.auth.personnel.transfer.non_trust_fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetNonTrustFunds{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}