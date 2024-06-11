package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;


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

	public MembersItem() {
	}

	public MembersItem(String totalAmount, Integer localCombinedOfferings, String memberNumber, String memberName, String meansOfPayment, String receiptNumber) {
		this.totalAmount = totalAmount;
		this.localCombinedOfferings = localCombinedOfferings;
		this.memberNumber = memberNumber;
		this.memberName = memberName;
		this.meansOfPayment = meansOfPayment;
		this.receiptNumber = receiptNumber;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getLocalCombinedOfferings() {
		return localCombinedOfferings;
	}

	public void setLocalCombinedOfferings(Integer localCombinedOfferings) {
		this.localCombinedOfferings = localCombinedOfferings;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMeansOfPayment() {
		return meansOfPayment;
	}

	public void setMeansOfPayment(String meansOfPayment) {
		this.meansOfPayment = meansOfPayment;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
}