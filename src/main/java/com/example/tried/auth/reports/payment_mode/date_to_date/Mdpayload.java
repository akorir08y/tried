package com.example.tried.auth.reports.payment_mode.date_to_date;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Mdpayload{

	@JsonProperty("localChurchNumber")
	private String localChurchNumber;

	@JsonProperty("localChurchName")
	private String localChurchName;

	@JsonProperty("transactions")
	private List<TransactionsItem> transactions;
}