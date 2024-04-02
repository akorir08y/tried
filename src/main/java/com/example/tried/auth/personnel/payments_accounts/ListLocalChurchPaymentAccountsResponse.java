package com.example.tried.auth.personnel.payments_accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListLocalChurchPaymentAccountsResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;
}