package com.example.tried.auth.personnel.payments_accounts;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("In-Kind")
	private String inKind;

	@JsonProperty("Mobile Money")
	private String mobileMoney;

	@JsonProperty("Bank Deposits")
	private String bankDeposits;

	@JsonProperty("typeOfAccount")
	private String typeOfAccount;

	@JsonProperty("Cash")
	private String cash;

	@JsonProperty("status")
	private Integer status;

	public Payload() {
	}

	public Payload(String inKind, String mobileMoney, String bankDeposits, String typeOfAccount, String cash, Integer status) {
		this.inKind = inKind;
		this.mobileMoney = mobileMoney;
		this.bankDeposits = bankDeposits;
		this.typeOfAccount = typeOfAccount;
		this.cash = cash;
		this.status = status;
	}

	public String getInKind() {
		return inKind;
	}

	public void setInKind(String inKind) {
		this.inKind = inKind;
	}

	public String getMobileMoney() {
		return mobileMoney;
	}

	public void setMobileMoney(String mobileMoney) {
		this.mobileMoney = mobileMoney;
	}

	public String getBankDeposits() {
		return bankDeposits;
	}

	public void setBankDeposits(String bankDeposits) {
		this.bankDeposits = bankDeposits;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}