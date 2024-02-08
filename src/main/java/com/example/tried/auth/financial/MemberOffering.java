package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberOffering{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private OfferingPayload payload;

	@JsonProperty("authentication")
	private OfferingAuthentication authentication;
}