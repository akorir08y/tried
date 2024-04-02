package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferDuration{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("timeOfSettlement")
	private String timeOfSettlement;

	@JsonProperty("startDate")
	private String startDate;
}