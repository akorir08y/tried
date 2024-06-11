package com.example.tried.auth.reports.church;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Cppayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("month")
	private Integer month;

	@JsonProperty("year")
	private Integer year;

	@JsonProperty("localChurchNumber")
	private String localChurchNumber;

	public Cppayload() {

	}

	public Cppayload(String churchName, Integer month, Integer year, String localChurchNumber) {
		this.churchName = churchName;
		this.month = month;
		this.year = year;
		this.localChurchNumber = localChurchNumber;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLocalChurchNumber() {
		return localChurchNumber;
	}

	public void setLocalChurchNumber(String localChurchNumber) {
		this.localChurchNumber = localChurchNumber;
	}
}