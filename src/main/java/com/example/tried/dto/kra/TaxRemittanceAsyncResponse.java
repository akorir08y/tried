package com.example.tried.dto.kra;


import com.example.tried.dto.b2c.Result;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TaxRemittanceAsyncResponse {

    @JsonProperty("Result")
    private Result result;

    public TaxRemittanceAsyncResponse() {

    }

    public TaxRemittanceAsyncResponse(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
