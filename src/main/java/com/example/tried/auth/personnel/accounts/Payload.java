package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("accountList")
	private AccountList accountList;

	public Payload() {
	}

	public Payload(AccountList accountList) {
		this.accountList = accountList;
	}

	public AccountList getAccountList() {
		return accountList;
	}

	public void setAccountList(AccountList accountList) {
		this.accountList = accountList;
	}
}