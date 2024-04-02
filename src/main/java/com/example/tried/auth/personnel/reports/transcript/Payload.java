package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("startDate")
	private String startDate;
}