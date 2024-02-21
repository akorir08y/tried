package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalTrustFundPaymentResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("prpayload")
	private Prpayload prpayload;

	@JsonProperty("status")
	private Integer status;
}