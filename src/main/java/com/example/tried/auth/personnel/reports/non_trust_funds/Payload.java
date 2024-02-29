package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("group")
	private String group;
}