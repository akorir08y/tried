package com.example.tried.auth.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Member{

	@JsonProperty("receiverContactType")
	private String receiverContactType;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("cfmsTransactionId")
	private String cfmsTransactionId;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("receiverContact")
	private String receiverContact;

	@JsonProperty("receiverName")
	private String receiverName;

	@JsonProperty("accountNumber")
	private String accountNumber;

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

	@JsonProperty("contributingAs")
	private String contributingAs;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("contributorType")
	private String contributorType;

	@JsonProperty("contributingFor")
	private String contributingFor;

	public Member() {

	}

	public Member(String receiverContactType, String churchName, String cfmsTransactionId, String accountName, String receiverContact, String receiverName, String accountNumber, String contributorName, String collectingParty, Integer totalAmount, String contributorContactType, String receiverId, String contributingAs, String churchCode, String contributorContact, String contributorType, String contributingFor) {
		this.receiverContactType = receiverContactType;
		this.churchName = churchName;
		this.cfmsTransactionId = cfmsTransactionId;
		this.accountName = accountName;
		this.receiverContact = receiverContact;
		this.receiverName = receiverName;
		this.accountNumber = accountNumber;
		this.contributorName = contributorName;
		this.collectingParty = collectingParty;
		this.totalAmount = totalAmount;
		this.contributorContactType = contributorContactType;
		this.receiverId = receiverId;
		this.contributingAs = contributingAs;
		this.churchCode = churchCode;
		this.contributorContact = contributorContact;
		this.contributorType = contributorType;
		this.contributingFor = contributingFor;
	}

	public String getReceiverContactType() {
		return receiverContactType;
	}

	public void setReceiverContactType(String receiverContactType) {
		this.receiverContactType = receiverContactType;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getCfmsTransactionId() {
		return cfmsTransactionId;
	}

	public void setCfmsTransactionId(String cfmsTransactionId) {
		this.cfmsTransactionId = cfmsTransactionId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getReceiverContact() {
		return receiverContact;
	}

	public void setReceiverContact(String receiverContact) {
		this.receiverContact = receiverContact;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getContributingFor() {
		return contributingFor;
	}

	public void setContributingFor(String contributingFor) {
		this.contributingFor = contributingFor;
	}
}