package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalTrustFundSummary{

	@JsonProperty("function")
	private String function;

	@JsonProperty("cppayload")
	private Cppayload cppayload;

	@JsonProperty("authentication")
	private Authentication authentication;
}