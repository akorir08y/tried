package com.example.tried.auth.personnel.receipting.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EditCashReceiptingResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private Integer status;
}
