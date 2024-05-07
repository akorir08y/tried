package com.example.tried.auth.personnel.reports.non_trust_funds;

import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class NonTrpayload{

	@JsonProperty("localChurchFunds")
	private HashMap<String, Integer> localChurchFunds;

	@JsonProperty("members")
	private List<HashMap<String, Object>> members;

	@JsonProperty("meansOfPayment")
	private List<String> meansOfPayment;
}