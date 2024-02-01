package com.example.tried.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckAccountBalanceRequest{

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Initiator")
	private String initiator;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("PartyA")
	private int partyA;

	@JsonProperty("IdentifierType")
	private int identifierType;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("ResultURL")
	private String resultURL;
}