package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MembersItem{

	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("local_combined_offerings")
	private Integer localCombinedOfferings;

	@JsonProperty("memberNumber")
	private String memberNumber;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("receiptNumber")
	private String receiptNumber;
}