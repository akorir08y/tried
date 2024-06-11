package com.example.tried.config;


import org.springframework.boot.context.properties.ConfigurationProperties;


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

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getOauthEndpoint() {
        return oauthEndpoint;
    }

    public int getShortCode() {
        return shortCode;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getConfirmationUrl() {
        return confirmationUrl;
    }

    public String getValidationUrl() {
        return validationUrl;
    }

    public String getRegisterUrlEndpoint() {
        return registerUrlEndpoint;
    }

    public String getSimulateTransactionEndpoint() {
        return simulateTransactionEndpoint;
    }

    public String getB2cTransactionEndpoint() {
        return b2cTransactionEndpoint;
    }

    public String getB2cResultUrl() {
        return b2cResultUrl;
    }

    public String getB2cQueueTimeoutUrl() {
        return b2cQueueTimeoutUrl;
    }

    public String getB2cInitiatorName() {
        return b2cInitiatorName;
    }

    public String getB2cInitiatorPassword() {
        return b2cInitiatorPassword;
    }

    public String getTransactionResultUrl() {
        return transactionResultUrl;
    }

    public String getAccountBalanceUrl() {
        return accountBalanceUrl;
    }

    public String getBillRefNumber() {
        return billRefNumber;
    }

    public long getPartyB() {
        return partyB;
    }

    public String getStkPassKey() {
        return stkPassKey;
    }

    public String getStkPushShortCode() {
        return stkPushShortCode;
    }

    public String getStkPushRequestUrl() {
        return stkPushRequestUrl;
    }

    public String getStkPushRequestCallbackUrl() {
        return stkPushRequestCallbackUrl;
    }

    public String getApiReversalUrl() {
        return apiReversalUrl;
    }

    public String getReversalResultUrl() {
        return reversalResultUrl;
    }

    public String getTaxRemittanceUrl() {
        return taxRemittanceUrl;
    }

    public String getTaxRemittanceResultUrl() {
        return taxRemittanceResultUrl;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public MpesaConfiguration(String consumerKey, String consumerSecret, String grantType, String oauthEndpoint, int shortCode, String responseType, String confirmationUrl, String validationUrl, String registerUrlEndpoint, String simulateTransactionEndpoint, String b2cTransactionEndpoint, String b2cResultUrl, String b2cQueueTimeoutUrl, String b2cInitiatorName, String b2cInitiatorPassword, String transactionResultUrl, String accountBalanceUrl, String billRefNumber, long partyB, String stkPassKey, String stkPushShortCode, String stkPushRequestUrl, String stkPushRequestCallbackUrl, String apiReversalUrl, String reversalResultUrl, String taxRemittanceUrl, String taxRemittanceResultUrl, String qrCodeUrl) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.grantType = grantType;
        this.oauthEndpoint = oauthEndpoint;
        this.shortCode = shortCode;
        this.responseType = responseType;
        this.confirmationUrl = confirmationUrl;
        this.validationUrl = validationUrl;
        this.registerUrlEndpoint = registerUrlEndpoint;
        this.simulateTransactionEndpoint = simulateTransactionEndpoint;
        this.b2cTransactionEndpoint = b2cTransactionEndpoint;
        this.b2cResultUrl = b2cResultUrl;
        this.b2cQueueTimeoutUrl = b2cQueueTimeoutUrl;
        this.b2cInitiatorName = b2cInitiatorName;
        this.b2cInitiatorPassword = b2cInitiatorPassword;
        this.transactionResultUrl = transactionResultUrl;
        this.accountBalanceUrl = accountBalanceUrl;
        this.billRefNumber = billRefNumber;
        this.partyB = partyB;
        this.stkPassKey = stkPassKey;
        this.stkPushShortCode = stkPushShortCode;
        this.stkPushRequestUrl = stkPushRequestUrl;
        this.stkPushRequestCallbackUrl = stkPushRequestCallbackUrl;
        this.apiReversalUrl = apiReversalUrl;
        this.reversalResultUrl = reversalResultUrl;
        this.taxRemittanceUrl = taxRemittanceUrl;
        this.taxRemittanceResultUrl = taxRemittanceResultUrl;
        this.qrCodeUrl = qrCodeUrl;
    }
}
