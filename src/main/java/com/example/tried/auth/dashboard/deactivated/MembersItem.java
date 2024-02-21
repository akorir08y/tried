package com.example.tried.auth.dashboard.deactivated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MembersItem{

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

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("districtNumber")
	private String districtNumber;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("churchNumber")
	private String churchNumber;
}