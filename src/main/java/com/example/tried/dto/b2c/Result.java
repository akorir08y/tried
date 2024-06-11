package com.example.tried.dto.b2c;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Result{

	@JsonProperty("ConversationID")
	private String conversationID;

	@JsonProperty("ReferenceData")
	private ReferenceData referenceData;

	@JsonProperty("OriginatorConversationID")
	private String originatorConversationID;

	@JsonProperty("ResultDesc")
	private String resultDesc;

	@JsonProperty("ResultType")
	private int resultType;

	@JsonProperty("ResultCode")
	private String resultCode;

	@JsonProperty("ResultParameters")
	private ResultParameters resultParameters;

	@JsonProperty("TransactionID")
	private String transactionID;

	public Result() {

	}

	public Result(String conversationID, ReferenceData referenceData, String originatorConversationID, String resultDesc, int resultType, String resultCode, ResultParameters resultParameters, String transactionID) {
		this.conversationID = conversationID;
		this.referenceData = referenceData;
		this.originatorConversationID = originatorConversationID;
		this.resultDesc = resultDesc;
		this.resultType = resultType;
		this.resultCode = resultCode;
		this.resultParameters = resultParameters;
		this.transactionID = transactionID;
	}

	public String getConversationID() {
		return conversationID;
	}

	public void setConversationID(String conversationID) {
		this.conversationID = conversationID;
	}

	public ReferenceData getReferenceData() {
		return referenceData;
	}

	public void setReferenceData(ReferenceData referenceData) {
		this.referenceData = referenceData;
	}

	public String getOriginatorConversationID() {
		return originatorConversationID;
	}

	public void setOriginatorConversationID(String originatorConversationID) {
		this.originatorConversationID = originatorConversationID;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public ResultParameters getResultParameters() {
		return resultParameters;
	}

	public void setResultParameters(ResultParameters resultParameters) {
		this.resultParameters = resultParameters;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
}