package com.example.tried.auth.personnel.receipting.traced.receipt;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rtpayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("transactions")
	private List<TransactionsItem> transactions;

	@JsonProperty("churchNumber")
	private String churchNumber;
}