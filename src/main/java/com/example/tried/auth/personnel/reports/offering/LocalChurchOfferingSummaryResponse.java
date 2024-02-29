package com.example.tried.auth.personnel.reports.offering;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class LocalChurchOfferingSummaryResponse{

	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;
}