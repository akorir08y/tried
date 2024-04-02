package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class AccountList{

	@JsonProperty("000000")
	private List<HashMap<String,Object>> getMemberList = new ArrayList<HashMap<String,Object>>();

	public List<HashMap<String, Object>> getGetMemberList() {
		return getMemberList;
	}

	public void setGetMemberList(List<HashMap<String, Object>> getMemberList) {
		this.getMemberList = getMemberList;
	}
}