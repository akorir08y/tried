package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthMemberRegistrationResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private int status;

	@JsonProperty("state")
	private String state;

	@JsonProperty("error")
	private String error;

	@JsonProperty("membershipNumber")
	private String membershipNumber;
}