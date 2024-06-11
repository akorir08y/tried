package com.example.tried.dto.c2b;

import com.fasterxml.jackson.annotation.JsonProperty;


// Simulate Customer to Business Response
public class SimulateC2BResponse{

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("ResponseDescription")
	private String responseDescription;

	@JsonProperty("OriginatorCoversationID")
	private String originatorCoversationID;

	public SimulateC2BResponse() {

	}

	public SimulateC2BResponse(String responseCode, String responseDescription, String originatorCoversationID) {
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
		this.originatorCoversationID = originatorCoversationID;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getOriginatorCoversationID() {
		return originatorCoversationID;
	}

	public void setOriginatorCoversationID(String originatorCoversationID) {
		this.originatorCoversationID = originatorCoversationID;
	}
}