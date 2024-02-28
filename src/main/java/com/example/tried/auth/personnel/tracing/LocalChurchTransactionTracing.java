package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchTransactionTracing{

	@JsonProperty("payload")
	private TracingPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}