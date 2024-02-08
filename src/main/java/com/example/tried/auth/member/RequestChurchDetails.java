package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestChurchDetails{

	@JsonProperty("accessNumber")
	private String accessNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("mobileServiceProvider")
	private String mobileServiceProvider;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("connectionPurpose")
	private String connectionPurpose;

	@JsonProperty("accessPoint")
	private String accessPoint;
}