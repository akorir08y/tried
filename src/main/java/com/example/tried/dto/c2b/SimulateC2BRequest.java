package com.example.tried.dto.c2b;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimulateC2BRequest{

	@JsonProperty("ShortCode")
	private int shortCode;

	@JsonProperty("Msisdn")
	private String msisdn;

	@JsonProperty("BillRefNumber")
	private String billRefNumber;

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("CommandID")
	private String commandID;
}