package com.example.tried.auth.personnel.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionsItem{

	@JsonProperty("business_member_id")
	private String businessMemberId;

	@JsonProperty("contributor_name")
	private String contributorName;

	@JsonProperty("sum")
	private String sum;

	public TransactionsItem() {
	}

	public TransactionsItem(String businessMemberId, String contributorName, String sum) {
		this.businessMemberId = businessMemberId;
		this.contributorName = contributorName;
		this.sum = sum;
	}

	public String getBusinessMemberId() {
		return businessMemberId;
	}

	public void setBusinessMemberId(String businessMemberId) {
		this.businessMemberId = businessMemberId;
	}

	public String getContributorName() {
		return contributorName;
	}

	public void setContributorName(String contributorName) {
		this.contributorName = contributorName;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
}