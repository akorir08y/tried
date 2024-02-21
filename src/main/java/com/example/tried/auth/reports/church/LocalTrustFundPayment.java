package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalTrustFundPayment{

	@JsonProperty("payload")
	private Papayload papayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}