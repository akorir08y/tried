package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class Memberpayload{

	@JsonProperty("church_name")
	private String churchName;

	@JsonProperty("membership_number")
	private String membershipNumber;

	@JsonProperty("conference_name")
	private String conferenceName;

	@JsonProperty("church_code")
	private String churchCode;

	@JsonProperty("member_name")
	private String memberName;

	@JsonProperty("preferred_language")
	private String preferredLanguage;

	@JsonProperty("conference_number")
	private String conferenceNumber;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("departmental_accounts")
	private List<DepartmentalAccountsItem> departmentalAccounts;

	@JsonProperty("contributes_as")
	private String contributesAs;

	public Memberpayload() {
	}

	public Memberpayload(String churchName, String membershipNumber, String conferenceName, String churchCode, String memberName, String preferredLanguage, String conferenceNumber, Integer status, List<DepartmentalAccountsItem> departmentalAccounts, String contributesAs) {
		this.churchName = churchName;
		this.membershipNumber = membershipNumber;
		this.conferenceName = conferenceName;
		this.churchCode = churchCode;
		this.memberName = memberName;
		this.preferredLanguage = preferredLanguage;
		this.conferenceNumber = conferenceNumber;
		this.status = status;
		this.departmentalAccounts = departmentalAccounts;
		this.contributesAs = contributesAs;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<DepartmentalAccountsItem> getDepartmentalAccounts() {
		return departmentalAccounts;
	}

	public void setDepartmentalAccounts(List<DepartmentalAccountsItem> departmentalAccounts) {
		this.departmentalAccounts = departmentalAccounts;
	}

	public String getContributesAs() {
		return contributesAs;
	}

	public void setContributesAs(String contributesAs) {
		this.contributesAs = contributesAs;
	}
}