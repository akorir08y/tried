package com.example.tried.auth.reports.statements;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchOfferingStatementResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("payload")
	private Lspayload lspayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}