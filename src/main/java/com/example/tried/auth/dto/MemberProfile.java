package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberProfile{

	@JsonProperty("payload")
	private Profilepayload profilepayload;

	@JsonProperty("function")
	private String function;
}