package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RmPayload{

	@JsonProperty("receiverName")
	private String receiverName;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("collectingParty")
	private String collectingParty;

	@JsonProperty("totalAmount")
	private Integer totalAmount;

	@JsonProperty("contributorContactType")
	private String contributorContactType;

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("contributingAs")
	private String contributingAs;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("receiverContact")
	private String receiverContact;

	@JsonProperty("contributorType")
	private String contributorType;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("contributingFor")
	private String contributingFor;

	public void setReceiverName(String receiverName){
		this.receiverName = receiverName;
	}

	public String getReceiverName(){
		return receiverName;
	}

	public void setContributorName(String contributorName){
		this.contributorName = contributorName;
	}

	public String getContributorName(){
		return contributorName;
	}

	public void setCollectingParty(String collectingParty){
		this.collectingParty = collectingParty;
	}

	public String getCollectingParty(){
		return collectingParty;
	}

	public void setTotalAmount(Integer totalAmount){
		this.totalAmount = totalAmount;
	}

	public Integer getTotalAmount(){
		return totalAmount;
	}

	public void setContributorContactType(String contributorContactType){
		this.contributorContactType = contributorContactType;
	}

	public String getContributorContactType(){
		return contributorContactType;
	}

	public void setReceiverId(String receiverId){
		this.receiverId = receiverId;
	}

	public String getReceiverId(){
		return receiverId;
	}

	public void setSessionNumber(Integer sessionNumber){
		this.sessionNumber = sessionNumber;
	}

	public Integer getSessionNumber(){
		return sessionNumber;
	}

	public void setContributingAs(String contributingAs){
		this.contributingAs = contributingAs;
	}

	public String getContributingAs(){
		return contributingAs;
	}

	public void setChurchCode(String churchCode){
		this.churchCode = churchCode;
	}

	public String getChurchCode(){
		return churchCode;
	}

	public void setContributorContact(String contributorContact){
		this.contributorContact = contributorContact;
	}

	public String getContributorContact(){
		return contributorContact;
	}

	public void setContributorType(String contributorType){
		this.contributorType = contributorType;
	}

	public String getContributorType(){
		return contributorType;
	}

	public void setMeansOfPayment(String meansOfPayment){
		this.meansOfPayment = meansOfPayment;
	}

	public String getMeansOfPayment(){
		return meansOfPayment;
	}

	public void setFundDistribution(FundDistribution fundDistribution){
		this.fundDistribution = fundDistribution;
	}

	public FundDistribution getFundDistribution(){
		return fundDistribution;
	}

	public void setContributingFor(String contributingFor){
		this.contributingFor = contributingFor;
	}

	public String getContributingFor(){
		return contributingFor;
	}

	@Override
 	public String toString(){
		return 
			"RmPayload{" + 
			"receiverName = '" + receiverName + '\'' + 
			",contributorName = '" + contributorName + '\'' + 
			",collectingParty = '" + collectingParty + '\'' + 
			",totalAmount = '" + totalAmount + '\'' + 
			",contributorContactType = '" + contributorContactType + '\'' + 
			",receiverId = '" + receiverId + '\'' + 
			",sessionNumber = '" + sessionNumber + '\'' + 
			",contributingAs = '" + contributingAs + '\'' + 
			",churchCode = '" + churchCode + '\'' + 
			",contributorContact = '" + contributorContact + '\'' + 
			",contributorType = '" + contributorType + '\'' + 
			",meansOfPayment = '" + meansOfPayment + '\'' + 
			",fundDistribution = '" + fundDistribution + '\'' + 
			",contributingFor = '" + contributingFor + '\'' + 
			"}";
		}
}