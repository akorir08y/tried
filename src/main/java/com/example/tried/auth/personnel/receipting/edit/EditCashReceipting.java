package com.example.tried.auth.personnel.receipting.edit;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EditCashReceipting{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	public EditCashReceipting() {
	}

	public EditCashReceipting(Payload payload, String function, String functionType, String receiptNumber) {
		this.payload = payload;
		this.function = function;
		this.functionType = functionType;
		this.receiptNumber = receiptNumber;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
}