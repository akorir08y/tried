package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchTransactionTracingResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}