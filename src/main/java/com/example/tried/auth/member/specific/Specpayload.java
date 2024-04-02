package com.example.tried.auth.member.specific;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Specpayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("members")
	private List<MembersItem> members;

	@JsonProperty("churchNumber")
	private String churchNumber;
}