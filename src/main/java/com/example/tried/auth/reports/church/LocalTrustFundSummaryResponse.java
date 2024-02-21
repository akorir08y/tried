package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalTrustFundSummaryResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}