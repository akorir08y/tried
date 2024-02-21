package com.example.tried.auth.dashboard.trust_funds;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Trupayload{

	@JsonProperty("localChurchNumber")
	private String localChurchNumber;

	@JsonProperty("localChurchName")
	private String localChurchName;

	@JsonProperty("transactions")
	private List<TransactionsItem> transactions;
}