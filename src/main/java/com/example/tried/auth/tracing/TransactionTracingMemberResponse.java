package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionTracingMemberResponse{

	@JsonProperty("payload")
	private Tropayload tropayload;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}