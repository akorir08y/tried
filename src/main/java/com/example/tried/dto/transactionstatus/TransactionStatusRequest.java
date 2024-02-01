package com.example.tried.dto.transactionstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionStatusRequest{

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("OriginatorConversationID")
	private String originatorConversationID;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Occasion")
	private String occasion;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("PartyA")
	private int partyA;

	@JsonProperty("IdentifierType")
	private int identifierType;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("ResultURL")
	private String resultURL;
}