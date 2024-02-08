package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthMemberResetResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private int status;
}