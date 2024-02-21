package com.example.tried.auth.dashboard.trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchTrustFundSummaryResponse{

	@JsonProperty("payload")
	private Trupayload trupayload;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}