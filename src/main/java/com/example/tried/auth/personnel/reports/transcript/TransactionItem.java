package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionItem{

	@JsonProperty("meansOfGiving")
	private String meansOfGiving;

	@JsonProperty("trustFundsLessSpecialTrustFund")
	private String trustFundsLessSpecialTrustFund;

	@JsonProperty("conferenceSettlementComment")
	private String conferenceSettlementComment;

	@JsonProperty("conferenceSettlementReference")
	private String conferenceSettlementReference;

	@JsonProperty("specialTrustFunds")
	private String specialTrustFunds;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("tithe")
	private String tithe;

	@JsonProperty("combinedOfferings")
	private String combinedOfferings;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("conferenceSettlementConfirmation")
	private String conferenceSettlementConfirmation;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("eMoneyReference")
	private String eMoneyReference;

	@JsonProperty("transactionInitiationTime")
	private String transactionInitiationTime;

	@JsonProperty("transactionReceiptNumber")
	private String transactionReceiptNumber;

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	@JsonProperty("memberId")
	private String memberId;

	@JsonProperty("confirmedInBankRecords")
	private String confirmedInBankRecords;

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("receiverName")
	private String receiverName;

	@JsonProperty("telcoTransactionTime")
	private String telcoTransactionTime;

	@JsonProperty("transactionCompletionTime")
	private String transactionCompletionTime;

	@JsonProperty("campMeeting")
	private String campMeeting;

	@JsonProperty("transactionTotalAmount")
	private String transactionTotalAmount;

	@JsonProperty("settlementAtClearenceAccount")
	private String settlementAtClearenceAccount;

	@JsonProperty("eMoneyAgent")
	private String eMoneyAgent;

	@JsonProperty("methodOfCollection")
	private String methodOfCollection;

	@JsonProperty("trustFund")
	private String trustFund;

	@JsonProperty("thirteenthSabbath")
	private String thirteenthSabbath;

	@JsonProperty("conferenceDevelopment")
	private String conferenceDevelopment;

	@JsonProperty("eMoneyNotificationTime")
	private String eMoneyNotificationTime;

	public TransactionItem() {

	}

	public TransactionItem(String meansOfGiving, String trustFundsLessSpecialTrustFund, String conferenceSettlementComment, String conferenceSettlementReference, String specialTrustFunds, String conferenceNumber, String tithe, String combinedOfferings, String contributorName, String conferenceSettlementConfirmation, String contributorContact, String churchCode, String eMoneyReference, String transactionInitiationTime, String transactionReceiptNumber, String receiptNumber, String memberId, String confirmedInBankRecords, String conferenceName, String receiverName, String telcoTransactionTime, String transactionCompletionTime, String campMeeting, String transactionTotalAmount, String settlementAtClearenceAccount, String eMoneyAgent, String methodOfCollection, String trustFund, String thirteenthSabbath, String conferenceDevelopment, String eMoneyNotificationTime) {
		this.meansOfGiving = meansOfGiving;
		this.trustFundsLessSpecialTrustFund = trustFundsLessSpecialTrustFund;
		this.conferenceSettlementComment = conferenceSettlementComment;
		this.conferenceSettlementReference = conferenceSettlementReference;
		this.specialTrustFunds = specialTrustFunds;
		this.conferenceNumber = conferenceNumber;
		this.tithe = tithe;
		this.combinedOfferings = combinedOfferings;
		this.contributorName = contributorName;
		this.conferenceSettlementConfirmation = conferenceSettlementConfirmation;
		this.contributorContact = contributorContact;
		this.churchCode = churchCode;
		this.eMoneyReference = eMoneyReference;
		this.transactionInitiationTime = transactionInitiationTime;
		this.transactionReceiptNumber = transactionReceiptNumber;
		this.receiptNumber = receiptNumber;
		this.memberId = memberId;
		this.confirmedInBankRecords = confirmedInBankRecords;
		this.conferenceName = conferenceName;
		this.receiverName = receiverName;
		this.telcoTransactionTime = telcoTransactionTime;
		this.transactionCompletionTime = transactionCompletionTime;
		this.campMeeting = campMeeting;
		this.transactionTotalAmount = transactionTotalAmount;
		this.settlementAtClearenceAccount = settlementAtClearenceAccount;
		this.eMoneyAgent = eMoneyAgent;
		this.methodOfCollection = methodOfCollection;
		this.trustFund = trustFund;
		this.thirteenthSabbath = thirteenthSabbath;
		this.conferenceDevelopment = conferenceDevelopment;
		this.eMoneyNotificationTime = eMoneyNotificationTime;
	}

	public String getMeansOfGiving() {
		return meansOfGiving;
	}

	public void setMeansOfGiving(String meansOfGiving) {
		this.meansOfGiving = meansOfGiving;
	}

	public String getTrustFundsLessSpecialTrustFund() {
		return trustFundsLessSpecialTrustFund;
	}

	public void setTrustFundsLessSpecialTrustFund(String trustFundsLessSpecialTrustFund) {
		this.trustFundsLessSpecialTrustFund = trustFundsLessSpecialTrustFund;
	}

	public String getConferenceSettlementComment() {
		return conferenceSettlementComment;
	}

	public void setConferenceSettlementComment(String conferenceSettlementComment) {
		this.conferenceSettlementComment = conferenceSettlementComment;
	}

	public String getConferenceSettlementReference() {
		return conferenceSettlementReference;
	}

	public void setConferenceSettlementReference(String conferenceSettlementReference) {
		this.conferenceSettlementReference = conferenceSettlementReference;
	}

	public String getSpecialTrustFunds() {
		return specialTrustFunds;
	}

	public void setSpecialTrustFunds(String specialTrustFunds) {
		this.specialTrustFunds = specialTrustFunds;
	}

	public String getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	public String getTithe() {
		return tithe;
	}

	public void setTithe(String tithe) {
		this.tithe = tithe;
	}

	public String getCombinedOfferings() {
		return combinedOfferings;
	}

	public void setCombinedOfferings(String combinedOfferings) {
		this.combinedOfferings = combinedOfferings;
	}

	public String getContributorName() {
		return contributorName;
	}

	public void setContributorName(String contributorName) {
		this.contributorName = contributorName;
	}

	public String getConferenceSettlementConfirmation() {
		return conferenceSettlementConfirmation;
	}

	public void setConferenceSettlementConfirmation(String conferenceSettlementConfirmation) {
		this.conferenceSettlementConfirmation = conferenceSettlementConfirmation;
	}

	public String getContributorContact() {
		return contributorContact;
	}

	public void setContributorContact(String contributorContact) {
		this.contributorContact = contributorContact;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getEMoneyReference() {
		return eMoneyReference;
	}

	public void seteMoneyReference(String eMoneyReference) {
		this.eMoneyReference = eMoneyReference;
	}

	public String getTransactionInitiationTime() {
		return transactionInitiationTime;
	}

	public void setTransactionInitiationTime(String transactionInitiationTime) {
		this.transactionInitiationTime = transactionInitiationTime;
	}

	public String getTransactionReceiptNumber() {
		return transactionReceiptNumber;
	}

	public void setTransactionReceiptNumber(String transactionReceiptNumber) {
		this.transactionReceiptNumber = transactionReceiptNumber;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getConfirmedInBankRecords() {
		return confirmedInBankRecords;
	}

	public void setConfirmedInBankRecords(String confirmedInBankRecords) {
		this.confirmedInBankRecords = confirmedInBankRecords;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getTelcoTransactionTime() {
		return telcoTransactionTime;
	}

	public void setTelcoTransactionTime(String telcoTransactionTime) {
		this.telcoTransactionTime = telcoTransactionTime;
	}

	public String getTransactionCompletionTime() {
		return transactionCompletionTime;
	}

	public void setTransactionCompletionTime(String transactionCompletionTime) {
		this.transactionCompletionTime = transactionCompletionTime;
	}

	public String getCampMeeting() {
		return campMeeting;
	}

	public void setCampMeeting(String campMeeting) {
		this.campMeeting = campMeeting;
	}

	public String getTransactionTotalAmount() {
		return transactionTotalAmount;
	}

	public void setTransactionTotalAmount(String transactionTotalAmount) {
		this.transactionTotalAmount = transactionTotalAmount;
	}

	public String getSettlementAtClearenceAccount() {
		return settlementAtClearenceAccount;
	}

	public void setSettlementAtClearenceAccount(String settlementAtClearenceAccount) {
		this.settlementAtClearenceAccount = settlementAtClearenceAccount;
	}

	public String getEMoneyAgent() {
		return eMoneyAgent;
	}

	public void seteMoneyAgent(String eMoneyAgent) {
		this.eMoneyAgent = eMoneyAgent;
	}

	public String getMethodOfCollection() {
		return methodOfCollection;
	}

	public void setMethodOfCollection(String methodOfCollection) {
		this.methodOfCollection = methodOfCollection;
	}

	public String getTrustFund() {
		return trustFund;
	}

	public void setTrustFund(String trustFund) {
		this.trustFund = trustFund;
	}

	public String getThirteenthSabbath() {
		return thirteenthSabbath;
	}

	public void setThirteenthSabbath(String thirteenthSabbath) {
		this.thirteenthSabbath = thirteenthSabbath;
	}

	public String getConferenceDevelopment() {
		return conferenceDevelopment;
	}

	public void setConferenceDevelopment(String conferenceDevelopment) {
		this.conferenceDevelopment = conferenceDevelopment;
	}

	public String getEMoneyNotificationTime() {
		return eMoneyNotificationTime;
	}

	public void seteMoneyNotificationTime(String eMoneyNotificationTime) {
		this.eMoneyNotificationTime = eMoneyNotificationTime;
	}
}