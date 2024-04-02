package com.example.tried.auth.personnel.reports.transcript;

import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Trpayload{

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("transactions")
	private List<TransactionItem> transactions;
}