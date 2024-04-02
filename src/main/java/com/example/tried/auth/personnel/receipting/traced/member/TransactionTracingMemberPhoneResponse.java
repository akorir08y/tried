package com.example.tried.auth.personnel.receipting.traced.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionTracingMemberPhoneResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Trppayload trppayload;

	@JsonProperty("status")
	private Integer status;
}