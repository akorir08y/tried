package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrustFundTranscriptResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("payload")
	private Trpayload trpayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}