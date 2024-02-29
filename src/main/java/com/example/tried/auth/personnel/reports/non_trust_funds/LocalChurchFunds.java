package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalChurchFunds{

	@JsonProperty("local_combined_offerings")
	private Integer localCombinedOfferings;
}