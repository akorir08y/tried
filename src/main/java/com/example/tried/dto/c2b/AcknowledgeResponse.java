package com.example.tried.dto.c2b;



// Acknowledge Response Data Access Object
public class AcknowledgeResponse {

    private String message;

    public AcknowledgeResponse() {

    }

    public AcknowledgeResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
