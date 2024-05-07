package com.example.tried.auth.personnel.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MobileReceiveFundsTransferResponse{

	@JsonProperty("cfmsTransactionId")
	private String cfmsTransactionId;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private Integer status;
}