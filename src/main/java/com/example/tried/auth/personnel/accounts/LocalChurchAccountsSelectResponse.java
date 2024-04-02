package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchAccountsSelectResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private Integer status;
}