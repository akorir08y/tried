package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Receivepayload{

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("MSISDN")
	private String mSISDN;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("paymentMode")
	private String paymentMode;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("extraData")
	private ExtraData extraData;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("narration")
	private String narration;

	@JsonProperty("customerNames")
	private String customerNames;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("accessPoint")
	private String accessPoint;

	@JsonProperty("datePaymentReceived")
	private String datePaymentReceived;

	public Receivepayload() {
	}

	public Receivepayload(Integer amount, String mSISDN, Integer sessionNumber, String paymentMode, String accountName, ExtraData extraData, String countryCode, String narration, String customerNames, String accountNumber, String accessPoint, String datePaymentReceived) {
		this.amount = amount;
		this.mSISDN = mSISDN;
		this.sessionNumber = sessionNumber;
		this.paymentMode = paymentMode;
		this.accountName = accountName;
		this.extraData = extraData;
		this.countryCode = countryCode;
		this.narration = narration;
		this.customerNames = customerNames;
		this.accountNumber = accountNumber;
		this.accessPoint = accessPoint;
		this.datePaymentReceived = datePaymentReceived;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getmSISDN() {
		return mSISDN;
	}

	public void setmSISDN(String mSISDN) {
		this.mSISDN = mSISDN;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public ExtraData getExtraData() {
		return extraData;
	}

	public void setExtraData(ExtraData extraData) {
		this.extraData = extraData;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(String customerNames) {
		this.customerNames = customerNames;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccessPoint() {
		return accessPoint;
	}

	public void setAccessPoint(String accessPoint) {
		this.accessPoint = accessPoint;
	}

	public String getDatePaymentReceived() {
		return datePaymentReceived;
	}

	public void setDatePaymentReceived(String datePaymentReceived) {
		this.datePaymentReceived = datePaymentReceived;
	}
}