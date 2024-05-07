package com.example.tried.auth.reports.payment_mode.date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}