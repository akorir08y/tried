package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberTransfer{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Transferpayload transferpayload;

	@JsonProperty("authentication")
	private TransferAuthentication transferauthentication;

	public MemberTransfer() {
	}

	public MemberTransfer(String function, TransferAuthentication transferauthentication, Transferpayload transferpayload) {
		this.function = function;
		this.transferauthentication = transferauthentication;
		this.transferpayload = transferpayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Transferpayload getTransferpayload() {
		return transferpayload;
	}

	public void setTransferpayload(Transferpayload transferpayload) {
		this.transferpayload = transferpayload;
	}

	public TransferAuthentication getTransferauthentication() {
		return transferauthentication;
	}

	public void setTransferauthentication(TransferAuthentication transferauthentication) {
		this.transferauthentication = transferauthentication;
	}
}