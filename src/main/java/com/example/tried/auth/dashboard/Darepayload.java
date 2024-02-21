package com.example.tried.auth.dashboard;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Darepayload{

	@JsonProperty("members")
	private List<MembersItem> members;
}