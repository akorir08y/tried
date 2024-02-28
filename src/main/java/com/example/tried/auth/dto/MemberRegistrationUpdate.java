package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberRegistrationUpdate{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private AuthMemberRegister updatepayload;
}