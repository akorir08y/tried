package com.example.tried.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="mpesa.daraja")
public class MpesaConfiguration {
    private final String consumerKey;
    private final String consumerSecret;
    private final String grantType;
    private final String oauthEndpoint;
    private final int shortCode;
    private final String responseType;
    private final String confirmationUrl;
    private final String validationUrl;
    private final String registerUrlEndpoint;
    private final String simulateTransactionEndpoint;
    private final String b2cTransactionEndpoint;
    private final String b2cResultUrl;
    private final String b2cQueueTimeoutUrl;
    private final String b2cInitiatorName;
    private final String b2cInitiatorPassword;
    private final String transactionResultUrl;
    private final String accountBalanceUrl;
    private final String billRefNumber;
    private final long partyB;
    private final String stkPassKey;
    private final String stkPushShortCode;
    private final String stkPushRequestUrl;
    private final String stkPushRequestCallbackUrl;
    private final String apiReversalUrl;
    private final String reversalResultUrl;
    private final String taxRemittanceUrl;
    private final String taxRemittanceResultUrl;
    private final String qrCodeUrl;


    @Override
    public String toString() {
        return String.format("{consumerKey='%s', consumerSecret='%s', grantType='%s', oauthEndpoint='%s'}",
                consumerKey, consumerSecret, grantType, oauthEndpoint);
    }
}
