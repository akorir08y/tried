package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestChurchDetailsWithCodeResponse{

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

	@JsonProperty("groups")
	private String groups;

	@JsonProperty("districtNumber")
	private String districtNumber;

	@JsonProperty("churchNumber")
	private String churchNumber;

	@JsonProperty("settlementAccountNumber")
	private String settlementAccountNumber;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("specialTrustFundAccounts")
	private String specialTrustFundAccounts;

	@JsonProperty("function")
	private String function;

	@JsonProperty("redactedSettlementAccountNumber")
	private String redactedSettlementAccountNumber;

	@JsonProperty("availableMeansOfPayment")
	private String availableMeansOfPayment;

	@JsonProperty("status")
	private Integer status;

	public String getDepartmentalAccounts(){
		return departmentalAccounts;
	}

	public String getChurchName(){
		return churchName;
	}

	public String getConferenceName(){
		return conferenceName;
	}

	public String getDistrictName(){
		return districtName;
	}

	public String getConferenceNumber(){
		return conferenceNumber;
	}

	public String getGroups(){
		return groups;
	}

	public String getDistrictNumber(){
		return districtNumber;
	}

	public String getChurchNumber(){
		return churchNumber;
	}

	public String getSettlementAccountNumber(){
		return settlementAccountNumber;
	}

	public Integer getSessionNumber(){
		return sessionNumber;
	}

	public String getSpecialTrustFundAccounts(){
		return specialTrustFundAccounts;
	}

	public String getFunction(){
		return function;
	}

	public String getRedactedSettlementAccountNumber(){
		return redactedSettlementAccountNumber;
	}

	public String getAvailableMeansOfPayment(){
		return availableMeansOfPayment;
	}

	public Integer getStatus(){
		return status;
	}
}