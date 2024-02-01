package com.example.tried.dto.b2c;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class B2CTransactionRequest {

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("InitiatorName")
	private String initiatorName;

	@JsonProperty("SecurityCredential")
	private String securityCredential;

	@JsonProperty("Occassion")
	private String occassion;

	@JsonProperty("CommandID")
	private String commandID;

	@JsonProperty("PartyA")
	private int partyA;

	@JsonProperty("PartyB")
	private long partyB;

	@JsonProperty("ResultURL")
	private String resultURL;
}