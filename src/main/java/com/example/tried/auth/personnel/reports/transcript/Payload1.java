package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload1 {

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("churchName")
	private String churchName;

}