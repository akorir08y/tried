package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthMemberReset{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;
}