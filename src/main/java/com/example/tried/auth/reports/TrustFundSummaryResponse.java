package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrustFundSummaryResponse{

	@JsonProperty("trpayload")
	private Trpayload trpayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}