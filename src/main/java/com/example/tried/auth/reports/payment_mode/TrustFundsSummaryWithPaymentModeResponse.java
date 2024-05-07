package com.example.tried.auth.reports.payment_mode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrustFundsSummaryWithPaymentModeResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private RPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}