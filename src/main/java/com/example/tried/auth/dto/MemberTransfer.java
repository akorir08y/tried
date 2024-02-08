package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberTransfer{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Transferpayload transferpayload;

	@JsonProperty("authentication")
	private TransferAuthentication transferauthentication;

	public String getFunction(){
		return function;
	}

	public Transferpayload getTransferpayload(){
		return transferpayload;
	}

	public TransferAuthentication getTransferauthentication(){
		return transferauthentication;
	}
}