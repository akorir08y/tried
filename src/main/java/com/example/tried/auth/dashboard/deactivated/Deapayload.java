package com.example.tried.auth.dashboard.deactivated;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Deapayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("churchCode")
	private String churchCode;

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}


	public Deapayload(String churchName, String churchCode) {
		this.churchName = churchName;
		this.churchCode = churchCode;
	}

	public Deapayload() {
	}
}