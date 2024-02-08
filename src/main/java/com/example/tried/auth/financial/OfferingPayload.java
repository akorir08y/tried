package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OfferingPayload{

	@JsonProperty("memberNumber")
	private String memberNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("numberOfTries")
	private Integer numberOfTries;

	@JsonProperty("startDate")
	private String startDate;
}