package com.example.tried.dto.transactionstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternalTransactionStatusRequest{

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("OriginatorConversationID")
	private String originatorConversationID;
}