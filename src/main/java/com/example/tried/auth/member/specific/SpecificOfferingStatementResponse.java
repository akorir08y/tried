package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpecificOfferingStatementResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Specpayload specpayload;

	@JsonProperty("status")
	private Integer status;
}