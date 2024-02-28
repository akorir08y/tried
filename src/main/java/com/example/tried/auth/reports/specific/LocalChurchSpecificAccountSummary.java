package com.example.tried.auth.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class LocalChurchSpecificAccountSummary{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}