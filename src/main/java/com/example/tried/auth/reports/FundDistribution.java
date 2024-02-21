package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FundDistribution{

	@JsonProperty("campMeeting")
	private Integer campMeeting;

	@JsonProperty("openingTithe")
	private Integer openingTithe;

	@JsonProperty("tithe")
	private Integer tithe;

	@JsonProperty("openingCampMeeting")
	private Integer openingCampMeeting;

	@JsonProperty("thirteenthSabbath")
	private Integer thirteenthSabbath;

	@JsonProperty("combinedOfferings")
	private Integer combinedOfferings;

	@JsonProperty("openingThirteenthSabbath")
	private Integer openingThirteenthSabbath;

	@JsonProperty("openingCombinedOfferings")
	private Integer openingCombinedOfferings;
}