package com.example.tried.auth.dashboard.trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("month")
	private Integer month;

	@JsonProperty("year")
	private Integer year;

	@JsonProperty("localChurchNumber")
	private String localChurchNumber;
}