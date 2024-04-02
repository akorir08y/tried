package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("memberNumber")
	private String memberNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("numberOfTries")
	private Integer numberOfTries;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("startDate")
	private String startDate;
}