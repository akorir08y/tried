package com.example.tried.auth.personnel.transfer.non_trust_fund;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class Payload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("startDate")
	private String startDate;
}