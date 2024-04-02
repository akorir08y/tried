package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionsItem{

	@JsonProperty("transactionDetails")
	private TransactionDetails transactionDetails;

	@JsonProperty("cfms_transaction_id")
	private String cfmsTransactionId;

	@JsonProperty("receiver_id")
	private String receiverId;

	@JsonProperty("special_trust_funds")
	private String specialTrustFunds;

	@JsonProperty("giving_status")
	private String givingStatus;

	@JsonProperty("non_trust_fund")
	private String nonTrustFund;

	@JsonProperty("trust_fund")
	private String trustFund;

	@JsonProperty("settlement_status")
	private String settlementStatus;
}