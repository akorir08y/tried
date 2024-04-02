package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentLinks{

	@JsonProperty("auditReportLink")
	private String auditReportLink;

	@JsonProperty("thirdPartyReceiptLink")
	private String thirdPartyReceiptLink;
}