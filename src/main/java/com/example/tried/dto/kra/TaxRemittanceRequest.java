package com.example.tried.dto.kra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaxRemittanceRequest{

	@JsonProperty("SenderIdentifierType")
	private String senderIdentifierType;

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("RecieverIdentifierType")
	private String recieverIdentifierType;

	@JsonProperty("PartyA")
	private String partyA;

	@JsonProperty("PartyB")
	private String partyB;

	@JsonProperty("AccountReference")
	private String accountReference;

	@JsonProperty("Command ID")
	private String commandID;

	@JsonProperty("ResultURL")
	private String resultURL;
}