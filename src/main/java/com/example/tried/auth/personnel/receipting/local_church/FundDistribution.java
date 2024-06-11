package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundDistribution{

	@JsonProperty("accountDetails")
	private AccountDetails accountDetails;

	@JsonProperty("accountabilityStatus")
	private AccountabilityStatus accountabilityStatus;

	@JsonProperty("trustFundsLocalChurchBalances")
	private TrustFundsLocalChurchBalances trustFundsLocalChurchBalances;

	@JsonProperty("trustFunds")
	private TrustFunds trustFunds;

	@JsonProperty("transferDuration")
	private TransferDuration transferDuration;

	@JsonProperty("documentLinks")
	private DocumentLinks documentLinks;

	@JsonProperty("trustFundsSourceAccountBalances")
	private TrustFundsSourceAccountBalances trustFundsSourceAccountBalances;

	public FundDistribution() {
	}

	public FundDistribution(AccountDetails accountDetails, AccountabilityStatus accountabilityStatus, TrustFundsLocalChurchBalances trustFundsLocalChurchBalances, TrustFunds trustFunds, TransferDuration transferDuration, DocumentLinks documentLinks, TrustFundsSourceAccountBalances trustFundsSourceAccountBalances) {
		this.accountDetails = accountDetails;
		this.accountabilityStatus = accountabilityStatus;
		this.trustFundsLocalChurchBalances = trustFundsLocalChurchBalances;
		this.trustFunds = trustFunds;
		this.transferDuration = transferDuration;
		this.documentLinks = documentLinks;
		this.trustFundsSourceAccountBalances = trustFundsSourceAccountBalances;
	}

}