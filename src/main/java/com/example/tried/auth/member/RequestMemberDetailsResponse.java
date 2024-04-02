package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestMemberDetailsResponse{

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("districtName")
	private String districtName;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("districtNumber")
	private String districtNumber;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("churchNumber")
	private String churchNumber;

	@JsonProperty("error")
	private String error;
}