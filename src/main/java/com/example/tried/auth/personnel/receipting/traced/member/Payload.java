package com.example.tried.auth.personnel.receipting.traced.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("startDate")
	private String startDate;
}