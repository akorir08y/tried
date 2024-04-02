package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionItem{

	@JsonProperty("meansOfGiving")
	private String meansOfGiving;

	@JsonProperty("trustFundsLessSpecialTrustFund")
	private String trustFundsLessSpecialTrustFund;

	@JsonProperty("eMoneyNotificationTime")
	private String eMoneyNotificationTime;

	@JsonProperty("specialTrustFunds")
	private String specialTrustFunds;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("tithe")
	private String tithe;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("conferenceSettlementConfirmation")
	private String conferenceSettlementConfirmation;

	@JsonProperty("conferenceAccountName")
	private String conferenceAccountName;

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
}