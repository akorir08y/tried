package com.example.tried.auth.personnel.accounts.update_account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateLocalChurchAccount{

	@JsonProperty("editValues")
	private EditValues editValues;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}