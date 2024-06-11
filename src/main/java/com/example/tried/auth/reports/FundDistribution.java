package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FundDistribution{

	@JsonProperty("campMeeting")
	private Integer campMeeting;

	@JsonProperty("openingTithe")
	private Integer openingTithe;

	@JsonProperty("tithe")
	private Integer tithe;

	@JsonProperty("openingCampMeeting")
	private Integer openingCampMeeting;

	@JsonProperty("thirteenthSabbath")
	private Integer thirteenthSabbath;

	@JsonProperty("combinedOfferings")
	private Integer combinedOfferings;

	@JsonProperty("openingThirteenthSabbath")
	private Integer openingThirteenthSabbath;

	@JsonProperty("openingCombinedOfferings")
	private Integer openingCombinedOfferings;

	public FundDistribution() {

	}

	public FundDistribution(Integer campMeeting, Integer openingTithe, Integer tithe, Integer openingCampMeeting, Integer thirteenthSabbath, Integer combinedOfferings, Integer openingThirteenthSabbath, Integer openingCombinedOfferings) {
		this.campMeeting = campMeeting;
		this.openingTithe = openingTithe;
		this.tithe = tithe;
		this.openingCampMeeting = openingCampMeeting;
		this.thirteenthSabbath = thirteenthSabbath;
		this.combinedOfferings = combinedOfferings;
		this.openingThirteenthSabbath = openingThirteenthSabbath;
		this.openingCombinedOfferings = openingCombinedOfferings;
	}

	public Integer getCampMeeting() {
		return campMeeting;
	}

	public void setCampMeeting(Integer campMeeting) {
		this.campMeeting = campMeeting;
	}

	public Integer getOpeningTithe() {
		return openingTithe;
	}

	public void setOpeningTithe(Integer openingTithe) {
		this.openingTithe = openingTithe;
	}

	public Integer getTithe() {
		return tithe;
	}

	public void setTithe(Integer tithe) {
		this.tithe = tithe;
	}

	public Integer getOpeningCampMeeting() {
		return openingCampMeeting;
	}

	public void setOpeningCampMeeting(Integer openingCampMeeting) {
		this.openingCampMeeting = openingCampMeeting;
	}

	public Integer getThirteenthSabbath() {
		return thirteenthSabbath;
	}

	public void setThirteenthSabbath(Integer thirteenthSabbath) {
		this.thirteenthSabbath = thirteenthSabbath;
	}

	public Integer getCombinedOfferings() {
		return combinedOfferings;
	}

	public void setCombinedOfferings(Integer combinedOfferings) {
		this.combinedOfferings = combinedOfferings;
	}

	public Integer getOpeningThirteenthSabbath() {
		return openingThirteenthSabbath;
	}

	public void setOpeningThirteenthSabbath(Integer openingThirteenthSabbath) {
		this.openingThirteenthSabbath = openingThirteenthSabbath;
	}

	public Integer getOpeningCombinedOfferings() {
		return openingCombinedOfferings;
	}

	public void setOpeningCombinedOfferings(Integer openingCombinedOfferings) {
		this.openingCombinedOfferings = openingCombinedOfferings;
	}
}