package com.example.tried.auth.personnel.accounts.update_account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateLocalChurchAccountResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private Integer status;
}