package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalChurchFunds{

	@JsonProperty("local_combined_offerings")
	private Integer localCombinedOfferings;

	public LocalChurchFunds() {
	}

	public LocalChurchFunds(Integer localCombinedOfferings) {
		this.localCombinedOfferings = localCombinedOfferings;
	}

	public Integer getLocalCombinedOfferings() {
		return localCombinedOfferings;
	}

	public void setLocalCombinedOfferings(Integer localCombinedOfferings) {
		this.localCombinedOfferings = localCombinedOfferings;
	}
}