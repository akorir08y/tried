package com.example.tried.auth.personnel.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class FundDistribution{

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;
}