package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthMemberResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("numberOfTries")
	private int numberOfTries;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private int status;

	@JsonProperty("notice")
	private String notice;

	@JsonProperty("error")
	private String error;
}