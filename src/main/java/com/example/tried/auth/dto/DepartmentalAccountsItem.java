package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepartmentalAccountsItem{

	@JsonProperty("account_1_number")
	private String account1Number;

	@JsonProperty("account_2_number")
	private String account2Number;

	@JsonProperty("account_1_name")
	private String account1Name;

	@JsonProperty("account_2_name")
	private String account2Name;
}