package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class RmPayload{

	@JsonProperty("receiverName")
	private String receiverName;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("collectingParty")
	private String collectingParty;

	@JsonProperty("totalAmount")
	private Integer totalAmount;

	@JsonProperty("contributorContactType")
	private String contributorContactType;

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("contributingAs")
	private String contributingAs;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("receiverContact")
	private String receiverContact;

	@JsonProperty("contributorType")
	private String contributorType;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("contributingFor")
	private String contributingFor;

	public RmPayload() {

	}

	public RmPayload(String receiverName, String contributorName, String collectingParty, Integer totalAmount, String contributorContactType, String receiverId, Integer sessionNumber, String contributingAs, String churchCode, String contributorContact, String receiverContact, String contributorType, String meansOfPayment, FundDistribution fundDistribution, String contributingFor) {
		this.receiverName = receiverName;
		this.contributorName = contributorName;
		this.collectingParty = collectingParty;
		this.totalAmount = totalAmount;
		this.contributorContactType = contributorContactType;
		this.receiverId = receiverId;
		this.sessionNumber = sessionNumber;
		this.contributingAs = contributingAs;
		this.churchCode = churchCode;
		this.contributorContact = contributorContact;
		this.receiverContact = receiverContact;
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

	public String getCollectingParty() {
		return collectingParty;
	}

	public void setCollectingParty(String collectingParty) {
		this.collectingParty = collectingParty;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
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

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
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

	public String getReceiverContact() {
		return receiverContact;
	}

	public void setReceiverContact(String receiverContact) {
		this.receiverContact = receiverContact;
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

	@Override
 	public String toString(){
		return 
			"RmPayload{" + 
			"receiverName = '" + receiverName + '\'' + 
			",contributorName = '" + contributorName + '\'' + 
			",collectingParty = '" + collectingParty + '\'' + 
			",totalAmount = '" + totalAmount + '\'' + 
			",contributorContactType = '" + contributorContactType + '\'' + 
			",receiverId = '" + receiverId + '\'' + 
			",sessionNumber = '" + sessionNumber + '\'' + 
			",contributingAs = '" + contributingAs + '\'' + 
			",churchCode = '" + churchCode + '\'' + 
			",contributorContact = '" + contributorContact + '\'' + 
			",contributorType = '" + contributorType + '\'' + 
			",meansOfPayment = '" + meansOfPayment + '\'' + 
			",fundDistribution = '" + fundDistribution + '\'' + 
			",contributingFor = '" + contributingFor + '\'' + 
			"}";
		}
}