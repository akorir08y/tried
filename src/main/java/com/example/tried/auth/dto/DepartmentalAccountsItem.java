package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DepartmentalAccountsItem{

	@JsonProperty("account_1_number")
	private String account1Number;

	@JsonProperty("account_2_number")
	private String account2Number;

	@JsonProperty("account_1_name")
	private String account1Name;

	@JsonProperty("account_2_name")
	private String account2Name;

	public DepartmentalAccountsItem() {
	}

	public DepartmentalAccountsItem(String account1Number, String account2Number, String account1Name, String account2Name) {
		this.account1Number = account1Number;
		this.account2Number = account2Number;
		this.account1Name = account1Name;
		this.account2Name = account2Name;
	}

	public String getAccount1Number() {
		return account1Number;
	}

	public void setAccount1Number(String account1Number) {
		this.account1Number = account1Number;
	}

	public String getAccount2Number() {
		return account2Number;
	}

	public void setAccount2Number(String account2Number) {
		this.account2Number = account2Number;
	}

	public String getAccount1Name() {
		return account1Name;
	}

	public void setAccount1Name(String account1Name) {
		this.account1Name = account1Name;
	}

	public String getAccount2Name() {
		return account2Name;
	}

	public void setAccount2Name(String account2Name) {
		this.account2Name = account2Name;
	}
}