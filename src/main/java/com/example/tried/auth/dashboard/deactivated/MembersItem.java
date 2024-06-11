package com.example.tried.auth.dashboard.deactivated;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MembersItem{

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("districtName")
	private String districtName;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("districtNumber")
	private String districtNumber;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("churchNumber")
	private String churchNumber;

	public MembersItem(String membershipNumber, String phoneNumber, String churchName, String conferenceName, Integer sessionNumber, String districtName, String conferenceNumber, String memberName, String districtNumber, Integer status, String churchNumber) {
		this.membershipNumber = membershipNumber;
		this.phoneNumber = phoneNumber;
		this.churchName = churchName;
		this.conferenceName = conferenceName;
		this.sessionNumber = sessionNumber;
		this.districtName = districtName;
		this.conferenceNumber = conferenceNumber;
		this.memberName = memberName;
		this.districtNumber = districtNumber;
		this.status = status;
		this.churchNumber = churchNumber;
	}

	public MembersItem() {
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getDistrictNumber() {
		return districtNumber;
	}

	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getChurchNumber() {
		return churchNumber;
	}

	public void setChurchNumber(String churchNumber) {
		this.churchNumber = churchNumber;
	}
}