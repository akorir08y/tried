package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestChurchDetailsResponse{

	@JsonProperty("phoneOneModeOfPayment")
	private String phoneOneModeOfPayment;

	@JsonProperty("departmentalAccounts")
	private String departmentalAccounts;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("districtName")
	private String districtName;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("otherPhoneNumber")
	private String otherPhoneNumber;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("isPessonnel")
	private Boolean isPessonnel;

	@JsonProperty("groups")
	private String groups;

	@JsonProperty("districtNumber")
	private String districtNumber;

	@JsonProperty("churchNumber")
	private String churchNumber;

	@JsonProperty("otherPhoneModeOfPayment")
	private String otherPhoneModeOfPayment;

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("trustFundAccounts")
	private String trustFundAccounts;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("specialTrustFundAccounts")
	private String specialTrustFundAccounts;

	@JsonProperty("function")
	private String function;

	@JsonProperty("availableMeansOfPayment")
	private String availableMeansOfPayment;

	@JsonProperty("status")
	private Integer status;
}