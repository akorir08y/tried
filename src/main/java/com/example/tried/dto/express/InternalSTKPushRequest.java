package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;


public class InternalSTKPushRequest{

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	@JsonProperty("invoice_id")
	private int invoice_id;

	@JsonProperty("institution_level")
	private String institution_level;

	@JsonProperty("institution_name")
	private String institution_name;

	@JsonProperty("institution_number")
	private String institution_number;

	public InternalSTKPushRequest() {

	}

	public InternalSTKPushRequest(String amount, String phoneNumber, int invoice_id, String institution_level, String institution_name, String institution_number) {
		this.amount = amount;
		this.phoneNumber = phoneNumber;
		this.invoice_id = invoice_id;
		this.institution_level = institution_level;
		this.institution_name = institution_name;
		this.institution_number = institution_number;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getInstitution_level() {
		return institution_level;
	}

	public void setInstitution_level(String institution_level) {
		this.institution_level = institution_level;
	}

	public String getInstitution_name() {
		return institution_name;
	}

	public void setInstitution_name(String institution_name) {
		this.institution_name = institution_name;
	}

	public String getInstitution_number() {
		return institution_number;
	}

	public void setInstitution_number(String institution_number) {
		this.institution_number = institution_number;
	}
}