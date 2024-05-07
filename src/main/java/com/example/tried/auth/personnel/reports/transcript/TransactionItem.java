package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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

}