package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectLocalChurchPayload{

	@JsonProperty("selectMode")
	private Boolean selectMode;

	@JsonProperty("editMode")
	private Boolean editMode;

	@JsonProperty("newEntryMode")
	private Boolean newEntryMode;
}