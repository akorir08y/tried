package com.example.tried.auth.personnel.reports.offering;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class LocalChurchPayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("group")
	private String group;
}