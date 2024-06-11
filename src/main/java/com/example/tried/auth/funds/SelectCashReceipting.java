package com.example.tried.auth.funds;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SelectCashReceipting{

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("typeOfPayment")
	private String typeOfPayment;

	@JsonProperty("startDate")
	private String startDate;

	public SelectCashReceipting() {
	}

	public SelectCashReceipting(String receiverId, String endDate, String function, String churchCode, String functionType, String typeOfPayment, String startDate) {
		this.receiverId = receiverId;
		this.endDate = endDate;
		this.function = function;
		this.churchCode = churchCode;
		this.functionType = functionType;
		this.typeOfPayment = typeOfPayment;
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
}