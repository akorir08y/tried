package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberRegistration{

	@JsonProperty("payload")
	private AuthMemberRegister payload;

	@JsonProperty("function")
	private String function;
}