package com.example.tried.auth.funds.non;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectNonTrustFundsResponse{

	@JsonProperty("payload")
	private Nonpayload nonpayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}