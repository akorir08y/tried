package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileReceiveFunds{

	@JsonProperty("receivepayload")
	private Receivepayload receivepayload;

	@JsonProperty("function")
	private String function;
}