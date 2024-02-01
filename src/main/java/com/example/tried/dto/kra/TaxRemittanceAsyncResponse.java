package com.example.tried.dto.kra;


import com.example.tried.dto.b2c.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaxRemittanceAsyncResponse {

    @JsonProperty("Result")
    private Result result;
}
