package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchAccounts{

	@JsonProperty("editValues")
	private EditValues editValues;

	@JsonProperty("payload")
	private SelectLocalChurchPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}