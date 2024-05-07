package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("churchName")
	private String churchName;


	public Payload() {
	}

	public Payload(String endDate, String churchCode, String startDate, String churchName) {
		this.endDate = endDate;
		this.churchCode = churchCode;
		this.startDate = startDate;
		this.churchName = churchName;
	}


	public Payload(String endDate, String churchCode, String meansOfPayment, String startDate, String churchName) {
		this.endDate = endDate;
		this.churchCode = churchCode;
		this.meansOfPayment = meansOfPayment;
		this.startDate = startDate;
		this.churchName = churchName;
	}
}