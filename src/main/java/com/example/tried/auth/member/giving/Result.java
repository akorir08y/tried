package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@Data
public class Result {

    @JsonProperty("function")
    private String function;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("sessionNumber")
    private String sessionNumber;

    @JsonProperty("membershipNumber")
    private String membershipNumber;


    @JsonProperty("state")
    private String state;

    @JsonProperty("cfmsTransactionId")
    private String cfmsTransactionId;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("extraData")
    private ExtraData extraData;

    @JsonProperty("notice")
    private String notice;

    @JsonProperty("message")
    private String message;

    //personnel
    @JsonProperty("churchName")
    private String churchName;

    @JsonProperty("payload")
    private Payload payload;

    @JsonProperty("churchCode")
    private String churchCode;

    @JsonProperty("department")
    private JSONObject department;

    @JsonProperty("departments")
    private JSONArray departments;

    @JsonProperty("activity")
    private JSONObject activities;

    @JsonProperty("members")
    private ArrayList<Member> members = null;
    //
    @JsonProperty("fileDownloadUri")
    private String fileDownloadUri;
    //

    public String getFunction() {
        return function;
    }

    public Integer getStatus() {
        return status;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public String getState() {
        return state;
    }

    public String getNotice() {
        return notice;
    }

    public String getCfmsTransactionId() {
        return cfmsTransactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public ExtraData getExtraData() {
        return extraData;
    }

    public String getChurchName() {
        return churchName;
    }

    public Payload getPayload() {
        return payload;
    }

    public String getChurchCode() {
        return churchCode;
    }

    public JSONObject getDepartment() {
        return department;
    }

    public JSONArray getDepartments() {
        return departments;
    }

    public String getMessage() {
        return message;
    }

    public JSONObject getActivities() {
        return activities;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    @NonNull
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        if (function != null) sb.append("function='").append(function).append('\'');
        if (status != null) sb.append(", status=").append(status);
        if (sessionNumber != null) sb.append(", sessionNumber=").append(sessionNumber);
        if (membershipNumber != null)
            sb.append(", membershipNumber='").append(membershipNumber).append('\'');
        if (state != null) sb.append(", state='").append(state).append('\'');
        if (cfmsTransactionId != null)
            sb.append(", cfmsTransactionId='").append(cfmsTransactionId).append('\'');
        if (accountNumber != null)
            sb.append(", accountNumber='").append(accountNumber).append('\'');
        if (extraData != null) sb.append(", extraData=").append(extraData);
        if (notice != null) sb.append(", notice='").append(notice).append('\'');
        if (message != null) sb.append(", message='").append(message).append('\'');
        if (churchName != null) sb.append(", churchName='").append(churchName).append('\'');
        if (payload != null) sb.append(", payload=").append(payload);
        if (churchCode != null) sb.append(", churchCode='").append(churchCode).append('\'');
        if (department != null) sb.append(", department=").append(department);
        if (departments != null) sb.append(", departments=").append(departments);
        if (activities != null) sb.append(", activities=").append(activities);
        sb.append('}');
        return sb.toString();
    }
}