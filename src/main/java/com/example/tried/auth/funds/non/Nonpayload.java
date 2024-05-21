package com.example.tried.auth.funds.non;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class Nonpayload{

	@JsonProperty("amount")
	private Object amount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("funds")
	private HashMap<String, Object> funds;

	@JsonProperty("startDate")
	private String startDate;
}