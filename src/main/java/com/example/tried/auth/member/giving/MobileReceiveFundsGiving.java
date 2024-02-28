package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MobileReceiveFundsGiving{

	@JsonProperty("payload")
	private RmPayload payload;

	@JsonProperty("function")
	private String function;
}