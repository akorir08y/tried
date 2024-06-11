package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MembersItem{

	@JsonProperty("balance")
	private String balance;

	@JsonProperty("beneficiaryChurchNumber")
	private String beneficiaryChurchNumber;

	@JsonProperty("depositAccount")
	private String depositAccount;

	@JsonProperty("narration")
	private String narration;

	@JsonProperty("amountRefunded")
	private String amountRefunded;

	@JsonProperty("beneficiaryChurchName")
	private String beneficiaryChurchName;

	@JsonProperty("transactionDate")
	private String transactionDate;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	@JsonProperty("amountReceived")
	private String amountReceived;

	public MembersItem() {
	}

	public MembersItem(String balance, String beneficiaryChurchNumber, String depositAccount, String narration, String amountRefunded, String beneficiaryChurchName, String transactionDate, String meansOfPayment, String receiptNumber, String amountReceived) {
		this.balance = balance;
		this.beneficiaryChurchNumber = beneficiaryChurchNumber;
		this.depositAccount = depositAccount;
		this.narration = narration;
		this.amountRefunded = amountRefunded;
		this.beneficiaryChurchName = beneficiaryChurchName;
		this.transactionDate = transactionDate;
		this.meansOfPayment = meansOfPayment;
		this.receiptNumber = receiptNumber;
		this.amountReceived = amountReceived;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBeneficiaryChurchNumber() {
		return beneficiaryChurchNumber;
	}

	public void setBeneficiaryChurchNumber(String beneficiaryChurchNumber) {
		this.beneficiaryChurchNumber = beneficiaryChurchNumber;
	}

	public String getDepositAccount() {
		return depositAccount;
	}

	public void setDepositAccount(String depositAccount) {
		this.depositAccount = depositAccount;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getAmountRefunded() {
		return amountRefunded;
	}

	public void setAmountRefunded(String amountRefunded) {
		this.amountRefunded = amountRefunded;
	}

	public String getBeneficiaryChurchName() {
		return beneficiaryChurchName;
	}

	public void setBeneficiaryChurchName(String beneficiaryChurchName) {
		this.beneficiaryChurchName = beneficiaryChurchName;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
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

	public String getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(String amountReceived) {
		this.amountReceived = amountReceived;
	}
}