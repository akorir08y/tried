package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class TransactionTracingMember{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}