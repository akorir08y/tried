package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TracingPayload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("startDate")
	private String startDate;
}