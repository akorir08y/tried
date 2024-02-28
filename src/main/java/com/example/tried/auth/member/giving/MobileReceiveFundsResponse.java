package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileReceiveFundsResponse{

	@JsonProperty("cfmsTransactionId")
	private String cfmsTransactionId;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("extraData")
	private ExtraData extraData;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("status")
	private Integer status;
}