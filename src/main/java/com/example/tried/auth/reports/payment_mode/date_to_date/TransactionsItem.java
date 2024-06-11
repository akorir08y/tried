package com.example.tried.auth.reports.payment_mode.date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionsItem{

	@JsonProperty("campMeetingPaid")
	private String campMeetingPaid;

	@JsonProperty("modeOfPayment")
	private String modeOfPayment;

	@JsonProperty("combinedOfferingsPaid")
	private String combinedOfferingsPaid;

	@JsonProperty("tithe")
	private String tithe;

	@JsonProperty("thirteenthSabbathPaid")
	private String thirteenthSabbathPaid;

	@JsonProperty("thirteenthSabbath")
	private String thirteenthSabbath;

	@JsonProperty("totalReceiptedAmountPaid")
	private String totalReceiptedAmountPaid;

	@JsonProperty("combinedOfferings")
	private String combinedOfferings;

	@JsonProperty("campMeeting")
	private String campMeeting;

	@JsonProperty("balance")
	private String balance;

	@JsonProperty("tithePaid")
	private String tithePaid;

	@JsonProperty("conferenceDevelopmentPaid")
	private String conferenceDevelopmentPaid;

	@JsonProperty("conferenceDevelopment")
	private String conferenceDevelopment;

	@JsonProperty("totalReceiptedAmount")
	private String totalReceiptedAmount;

	@JsonProperty("sabbath")
	private String sabbath;

	@JsonProperty("transactionDate")
	private String transactionDate;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("settlementReference")
	private String settlementReference;

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	public TransactionsItem() {

	}

	public TransactionsItem(String campMeetingPaid, String modeOfPayment, String combinedOfferingsPaid, String tithe, String thirteenthSabbathPaid, String thirteenthSabbath, String totalReceiptedAmountPaid, String combinedOfferings, String campMeeting, String balance, String tithePaid, String conferenceDevelopmentPaid, String conferenceDevelopment, String totalReceiptedAmount, String sabbath, String transactionDate, String contributorName, String settlementReference, String receiptNumber) {
		this.campMeetingPaid = campMeetingPaid;
		this.modeOfPayment = modeOfPayment;
		this.combinedOfferingsPaid = combinedOfferingsPaid;
		this.tithe = tithe;
		this.thirteenthSabbathPaid = thirteenthSabbathPaid;
		this.thirteenthSabbath = thirteenthSabbath;
		this.totalReceiptedAmountPaid = totalReceiptedAmountPaid;
		this.combinedOfferings = combinedOfferings;
		this.campMeeting = campMeeting;
		this.balance = balance;
		this.tithePaid = tithePaid;
		this.conferenceDevelopmentPaid = conferenceDevelopmentPaid;
		this.conferenceDevelopment = conferenceDevelopment;
		this.totalReceiptedAmount = totalReceiptedAmount;
		this.sabbath = sabbath;
		this.transactionDate = transactionDate;
		this.contributorName = contributorName;
		this.settlementReference = settlementReference;
		this.receiptNumber = receiptNumber;
	}

	public String getCampMeetingPaid() {
		return campMeetingPaid;
	}

	public void setCampMeetingPaid(String campMeetingPaid) {
		this.campMeetingPaid = campMeetingPaid;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getCombinedOfferingsPaid() {
		return combinedOfferingsPaid;
	}

	public void setCombinedOfferingsPaid(String combinedOfferingsPaid) {
		this.combinedOfferingsPaid = combinedOfferingsPaid;
	}

	public String getTithe() {
		return tithe;
	}

	public void setTithe(String tithe) {
		this.tithe = tithe;
	}

	public String getThirteenthSabbathPaid() {
		return thirteenthSabbathPaid;
	}

	public void setThirteenthSabbathPaid(String thirteenthSabbathPaid) {
		this.thirteenthSabbathPaid = thirteenthSabbathPaid;
	}

	public String getThirteenthSabbath() {
		return thirteenthSabbath;
	}

	public void setThirteenthSabbath(String thirteenthSabbath) {
		this.thirteenthSabbath = thirteenthSabbath;
	}

	public String getTotalReceiptedAmountPaid() {
		return totalReceiptedAmountPaid;
	}

	public void setTotalReceiptedAmountPaid(String totalReceiptedAmountPaid) {
		this.totalReceiptedAmountPaid = totalReceiptedAmountPaid;
	}

	public String getCombinedOfferings() {
		return combinedOfferings;
	}

	public void setCombinedOfferings(String combinedOfferings) {
		this.combinedOfferings = combinedOfferings;
	}

	public String getCampMeeting() {
		return campMeeting;
	}

	public void setCampMeeting(String campMeeting) {
		this.campMeeting = campMeeting;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTithePaid() {
		return tithePaid;
	}

	public void setTithePaid(String tithePaid) {
		this.tithePaid = tithePaid;
	}

	public String getConferenceDevelopmentPaid() {
		return conferenceDevelopmentPaid;
	}

	public void setConferenceDevelopmentPaid(String conferenceDevelopmentPaid) {
		this.conferenceDevelopmentPaid = conferenceDevelopmentPaid;
	}

	public String getConferenceDevelopment() {
		return conferenceDevelopment;
	}

	public void setConferenceDevelopment(String conferenceDevelopment) {
		this.conferenceDevelopment = conferenceDevelopment;
	}

	public String getTotalReceiptedAmount() {
		return totalReceiptedAmount;
	}

	public void setTotalReceiptedAmount(String totalReceiptedAmount) {
		this.totalReceiptedAmount = totalReceiptedAmount;
	}

	public String getSabbath() {
		return sabbath;
	}

	public void setSabbath(String sabbath) {
		this.sabbath = sabbath;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getContributorName() {
		return contributorName;
	}

	public void setContributorName(String contributorName) {
		this.contributorName = contributorName;
	}

	public String getSettlementReference() {
		return settlementReference;
	}

	public void setSettlementReference(String settlementReference) {
		this.settlementReference = settlementReference;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
}