package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchNonTrustSummaryResponse{

	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private NonTrpayload nonTrpayload;

	@JsonProperty("status")
	private Integer status;
}