package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestChurchDetailsWithCode{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Churchpayload churchpayload;

}