package com.example.tried.auth.personnel.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchSpecificAccountResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("specpayload")
	private Specpayload specpayload;

	@JsonProperty("status")
	private Integer status;
}