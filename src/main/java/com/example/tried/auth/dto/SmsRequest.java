package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class SmsRequest{

	@JsonProperty("date")
	private String date;

	@JsonProperty("clintLevel")
	private String clintLevel;

	@JsonProperty("clientAccount")
	private String clientAccount;

	@JsonProperty("phoneNumber")
	private List<String> phoneNumber;

	@JsonProperty("clientName")
	private String clientName;

	@JsonProperty("messages")
	private String messages;

	@JsonProperty("messageGroup")
	private String messageGroup;

	public SmsRequest() {
	}

	public SmsRequest(String date, String clintLevel, String clientAccount, List<String> phoneNumber, String clientName, String messages, String messageGroup) {
		this.date = date;
		this.clintLevel = clintLevel;
		this.clientAccount = clientAccount;
		this.phoneNumber = phoneNumber;
		this.clientName = clientName;
		this.messages = messages;
		this.messageGroup = messageGroup;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClintLevel() {
		return clintLevel;
	}

	public void setClintLevel(String clintLevel) {
		this.clintLevel = clintLevel;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getMessageGroup() {
		return messageGroup;
	}

	public void setMessageGroup(String messageGroup) {
		this.messageGroup = messageGroup;
	}
}