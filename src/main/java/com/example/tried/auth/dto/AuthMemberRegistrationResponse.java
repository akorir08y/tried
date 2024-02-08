package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthMemberRegistrationResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private int status;
}