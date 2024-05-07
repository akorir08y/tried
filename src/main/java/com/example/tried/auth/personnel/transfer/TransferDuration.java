package com.example.tried.auth.personnel.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferDuration{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("startDate")
	private String startDate;
}