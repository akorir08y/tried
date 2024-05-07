package com.example.tried.auth.tracing.receipt;

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

	@JsonProperty("non_trust_fund")
	private String nonTrustFunds;

	@JsonProperty("special_trust_funds")
	private String specialTrustFunds;

	@JsonProperty("trust_fund")
	private String trustFund;

	@JsonProperty("giving_status")
	private String givingStatus;

	@JsonProperty("settlement_status")
	private String settlementStatus;
}