package com.example.tried.auth.dashboard.deactivated;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListDeactivatedMembersResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("members")
	private List<MembersItem> members;

	@JsonProperty("status")
	private Integer status;
}