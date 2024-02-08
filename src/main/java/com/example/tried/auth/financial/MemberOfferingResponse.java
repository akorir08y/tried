package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberOfferingResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Offpayload offpayload;

	@JsonProperty("status")
	private Integer status;
}