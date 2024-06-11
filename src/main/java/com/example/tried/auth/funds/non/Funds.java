package com.example.tried.auth.funds.non;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Funds{

	@JsonProperty("Combined Offerings")
	private Object combinedOfferings;

	@JsonProperty("openingCombinedOfferings")
	private Object openingCombinedOfferings;

	public Funds() {
	}

	public Funds(Object combinedOfferings, Object openingCombinedOfferings) {
		this.combinedOfferings = combinedOfferings;
		this.openingCombinedOfferings = openingCombinedOfferings;
	}

	public Object getCombinedOfferings() {
		return combinedOfferings;
	}

	public void setCombinedOfferings(Object combinedOfferings) {
		this.combinedOfferings = combinedOfferings;
	}

	public Object getOpeningCombinedOfferings() {
		return openingCombinedOfferings;
	}

	public void setOpeningCombinedOfferings(Object openingCombinedOfferings) {
		this.openingCombinedOfferings = openingCombinedOfferings;
	}
}