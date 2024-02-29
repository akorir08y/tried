package com.example.tried.auth.personnel.reports.offering;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchOfferingSummary{

	@JsonProperty("payload")
	private LocalChurchPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}