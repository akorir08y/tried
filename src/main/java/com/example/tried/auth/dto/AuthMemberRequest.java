package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthMemberRequest {

	@JsonProperty("payload")
	private AuthPayload payload;

	@JsonProperty("function")
	private String function;
}