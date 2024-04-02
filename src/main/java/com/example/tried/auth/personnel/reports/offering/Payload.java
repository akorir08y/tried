package com.example.tried.auth.personnel.reports.offering;

import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("localChurchFunds")
	private HashMap<String, Integer> localChurchFunds;

	@JsonProperty("members")
	private List<HashMap<String, Object>> members;

	@JsonProperty("meansOfPayment")
	private List<String> meansOfPayment;

	@JsonProperty("trustFunds")
	private HashMap<String,String> trustFunds;
}