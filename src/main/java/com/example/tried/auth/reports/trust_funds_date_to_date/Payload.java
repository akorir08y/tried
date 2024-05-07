package com.example.tried.auth.reports.trust_funds_date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("startDate")
	private String startDate;
}