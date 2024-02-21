package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class TrustFundSummary{

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	@JsonProperty("trustpayload")
	private Trustpayload trustpayload;
}