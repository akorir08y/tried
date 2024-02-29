package com.example.tried.auth.personnel.reports.non_trust_funds;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class NonTrpayload{

	@JsonProperty("localChurchFunds")
	private LocalChurchFunds localChurchFunds;

	@JsonProperty("members")
	private List<MembersItem> members;

	@JsonProperty("meansOfPayment")
	private List<String> meansOfPayment;
}