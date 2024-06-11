package com.example.tried.auth.funds.delete;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DeleteCashReceipting{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("function")
	private String function;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	public DeleteCashReceipting() {
	}

	public DeleteCashReceipting(String churchName, String function, String contributorContact, String churchCode, String functionType, String receiptNumber) {
		this.churchName = churchName;
		this.function = function;
		this.contributorContact = contributorContact;
		this.churchCode = churchCode;
		this.functionType = functionType;
		this.receiptNumber = receiptNumber;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getContributorContact() {
		return contributorContact;
	}

	public void setContributorContact(String contributorContact) {
		this.contributorContact = contributorContact;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
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