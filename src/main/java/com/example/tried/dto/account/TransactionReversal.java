package com.example.tried.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionReversal{

	@JsonProperty("RecieverIdentifierType")
	private String recieverIdentifierType;

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("Occassion")
	private String occassion;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("ReceiverParty")
	private String receiverParty;

	@JsonProperty("ResultURL")
	private String resultURL;
}