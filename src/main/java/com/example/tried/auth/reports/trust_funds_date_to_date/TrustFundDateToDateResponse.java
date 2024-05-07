package com.example.tried.auth.reports.trust_funds_date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrustFundDateToDateResponse{

	@JsonProperty("payload")
	private Dtpayload dtpayload;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}