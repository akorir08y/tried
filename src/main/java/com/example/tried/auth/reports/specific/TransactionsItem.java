package com.example.tried.auth.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class TransactionsItem{

	@JsonProperty("business_member_id")
	private String businessMemberId;

	@JsonProperty("contributor_name")
	private String contributorName;

	@JsonProperty("sum")
	private String sum;
}