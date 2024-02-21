package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Trpayload{

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("startDate")
	private String startDate;
}