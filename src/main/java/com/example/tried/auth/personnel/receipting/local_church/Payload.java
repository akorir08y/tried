package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload{

	@JsonProperty("receiverName")
	private String receiverName;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("thirdPartyReference")
	private String thirdPartyReference;

	@JsonProperty("collectingParty")
	private String collectingParty;

	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("contributorContactType")
	private String contributorContactType;

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("contributingAs")
	private String contributingAs;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("contributorType")
	private String contributorType;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("contributingFor")
	private String contributingFor;

	public Payload() {
	}

	public Payload(String receiverName, String contributorName, String thirdPartyReference, String collectingParty, String totalAmount, String contributorContactType, String receiverId, String sessionNumber, String contributingAs, String churchCode, String contributorContact, String contributorType, String meansOfPayment, FundDistribution fundDistribution, String contributingFor) {
		this.receiverName = receiverName;
		this.contributorName = contributorName;
		this.thirdPartyReference = thirdPartyReference;
		this.collectingParty = collectingParty;
		this.totalAmount = totalAmount;
		this.contributorContactType = contributorContactType;
		this.receiverId = receiverId;
		this.sessionNumber = sessionNumber;
		this.contributingAs = contributingAs;
		this.churchCode = churchCode;
		this.contributorContact = contributorContact;
		this.contributorType = contributorType;
		this.meansOfPayment = meansOfPayment;
		this.fundDistribution = fundDistribution;
		this.contributingFor = contributingFor;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getContributorName() {
		return contributorName;
	}

	public void setContributorName(String contributorName) {
		this.contributorName = contributorName;
	}

	public String getThirdPartyReference() {
		return thirdPartyReference;
	}

	public void setThirdPartyReference(String thirdPartyReference) {
		this.thirdPartyReference = thirdPartyReference;
	}

	public String getCollectingParty() {
		return collectingParty;
	}

	public void setCollectingParty(String collectingParty) {
		this.collectingParty = collectingParty;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getContributorContactType() {
		return contributorContactType;
	}

	public void setContributorContactType(String contributorContactType) {
		this.contributorContactType = contributorContactType;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getContributingAs() {
		return contributingAs;
	}

	public void setContributingAs(String contributingAs) {
		this.contributingAs = contributingAs;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getContributorContact() {
		return contributorContact;
	}

	public void setContributorContact(String contributorContact) {
		this.contributorContact = contributorContact;
	}

	public String getContributorType() {
		return contributorType;
	}

	public void setContributorType(String contributorType) {
		this.contributorType = contributorType;
	}

	public String getMeansOfPayment() {
		return meansOfPayment;
	}

	public void setMeansOfPayment(String meansOfPayment) {
		this.meansOfPayment = meansOfPayment;
	}

	public FundDistribution getFundDistribution() {
		return fundDistribution;
	}

	public void setFundDistribution(FundDistribution fundDistribution) {
		this.fundDistribution = fundDistribution;
	}

	public String getContributingFor() {
		return contributingFor;
	}

	public void setContributingFor(String contributingFor) {
		this.contributingFor = contributingFor;
	}
}