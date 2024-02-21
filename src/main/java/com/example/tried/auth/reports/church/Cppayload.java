package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cppayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("month")
	private Integer month;

	@JsonProperty("year")
	private Integer year;

	@JsonProperty("localChurchNumber")
	private String localChurchNumber;
}