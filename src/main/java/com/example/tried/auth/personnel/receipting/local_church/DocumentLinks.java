package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentLinks{

	@JsonProperty("auditReportLink")
	private String auditReportLink;

	@JsonProperty("thirdPartyReceiptLink")
	private String thirdPartyReceiptLink;

	public DocumentLinks() {
	}

	public DocumentLinks(String auditReportLink, String thirdPartyReceiptLink) {
		this.auditReportLink = auditReportLink;
		this.thirdPartyReceiptLink = thirdPartyReceiptLink;
	}

	public String getAuditReportLink() {
		return auditReportLink;
	}

	public void setAuditReportLink(String auditReportLink) {
		this.auditReportLink = auditReportLink;
	}

	public String getThirdPartyReceiptLink() {
		return thirdPartyReceiptLink;
	}

	public void setThirdPartyReceiptLink(String thirdPartyReceiptLink) {
		this.thirdPartyReceiptLink = thirdPartyReceiptLink;
	}
}