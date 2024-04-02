package com.example.tried.auth.personnel.receipting.traced.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionTracingMemberReceiptResponse{

	@JsonProperty("payload")
	private Rtpayload rtpayload;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}