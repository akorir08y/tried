package com.example.tried.auth.personnel.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("startDate")
	private String startDate;
}