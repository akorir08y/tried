package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionTracingMemberReceiptResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Rpayload rpayload;

	@JsonProperty("status")
	private Integer status;
}