package com.example.tried.dto.account;


import com.example.tried.dto.b2c.Result;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionReversalAsyncResponse {

    @JsonProperty("Result")
    private Result result;

    public TransactionReversalAsyncResponse() {

    }

    public TransactionReversalAsyncResponse(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
