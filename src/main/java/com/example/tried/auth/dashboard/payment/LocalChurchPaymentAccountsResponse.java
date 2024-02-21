package com.example.tried.auth.dashboard.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchPaymentAccountsResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Pypayload pypayload;
}