package com.example.tried.dto.b2c;

import com.fasterxml.jackson.annotation.JsonProperty;


// Transaction Results Data Access Object
public class TransactionResults{

	@JsonProperty("TransactionType")
	private String transactionType;

	@JsonProperty("BillRefNumber")
	private String billRefNumber;

	@JsonProperty("MSISDN")
	private String mSISDN;

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("MiddleName")
	private String middleName;

	@JsonProperty("BusinessShortCode")
	private String businessShortCode;

	@JsonProperty("OrgAccountBalance")
	private String orgAccountBalance;

	@JsonProperty("TransAmount")
	private String transAmount;

	@JsonProperty("ThirdPartyTransID")
	private String thirdPartyTransID;

	@JsonProperty("InvoiceNumber")
	private String invoiceNumber;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("TransID")
	private String transID;

	@JsonProperty("TransTime")
	private String transTime;

	public TransactionResults() {

	}

	public TransactionResults(String transactionType, String billRefNumber, String mSISDN, String firstName, String middleName, String businessShortCode, String orgAccountBalance, String transAmount, String thirdPartyTransID, String invoiceNumber, String lastName, String transID, String transTime) {
		this.transactionType = transactionType;
		this.billRefNumber = billRefNumber;
		this.mSISDN = mSISDN;
		this.firstName = firstName;
		this.middleName = middleName;
		this.businessShortCode = businessShortCode;
		this.orgAccountBalance = orgAccountBalance;
		this.transAmount = transAmount;
		this.thirdPartyTransID = thirdPartyTransID;
		this.invoiceNumber = invoiceNumber;
		this.lastName = lastName;
		this.transID = transID;
		this.transTime = transTime;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getBillRefNumber() {
		return billRefNumber;
	}

	public void setBillRefNumber(String billRefNumber) {
		this.billRefNumber = billRefNumber;
	}

	public String getmSISDN() {
		return mSISDN;
	}

	public void setmSISDN(String mSISDN) {
		this.mSISDN = mSISDN;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getBusinessShortCode() {
		return businessShortCode;
	}

	public void setBusinessShortCode(String businessShortCode) {
		this.businessShortCode = businessShortCode;
	}

	public String getOrgAccountBalance() {
		return orgAccountBalance;
	}

	public void setOrgAccountBalance(String orgAccountBalance) {
		this.orgAccountBalance = orgAccountBalance;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public String getThirdPartyTransID() {
		return thirdPartyTransID;
	}

	public void setThirdPartyTransID(String thirdPartyTransID) {
		this.thirdPartyTransID = thirdPartyTransID;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
}