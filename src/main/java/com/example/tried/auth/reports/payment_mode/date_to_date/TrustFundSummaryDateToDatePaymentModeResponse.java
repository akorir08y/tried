package com.example.tried.auth.reports.payment_mode.date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrustFundSummaryDateToDatePaymentModeResponse{

	@JsonProperty("payload")
	private Mdpayload mdpayload;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}