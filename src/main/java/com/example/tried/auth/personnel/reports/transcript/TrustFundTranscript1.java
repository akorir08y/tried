package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrustFundTranscript1 {

	@JsonProperty("payload")
	private Payload1 payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}