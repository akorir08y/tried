package com.example.tried.services;


import com.example.tried.config.MpesaConfiguration;
import com.example.tried.dto.account.*;
import com.example.tried.dto.b2c.B2CTransactionRequest;
import com.example.tried.dto.b2c.B2CTransactionSyncResponse;
import com.example.tried.dto.c2b.RegisterUrlRequest;
import com.example.tried.dto.c2b.RegisterUrlResponse;
import com.example.tried.dto.c2b.SimulateC2BRequest;
import com.example.tried.dto.c2b.SimulateC2BResponse;
import com.example.tried.dto.express.ExternalSTKPushRequest;
import com.example.tried.dto.express.InternalSTKPushRequest;
import com.example.tried.dto.express.STKPushSyncResponse;
import com.example.tried.dto.kra.InternalTaxRequest;
import com.example.tried.dto.kra.TaxRemittanceRequest;
import com.example.tried.dto.kra.TaxRemittanceResponse;
import com.example.tried.dto.qrcode.QRCodeRequest;
import com.example.tried.dto.qrcode.QRCodeResponse;
import com.example.tried.dto.token.AccessTokenResponse;
import com.example.tried.dto.transactionstatus.InternalTransactionStatusRequest;
import com.example.tried.dto.transactionstatus.TransactionStatusRequest;
import com.example.tried.dto.transactionstatus.TransactionStatusSyncResponse;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static com.example.tried.utils.Constants.*;


// Daraja API Implementation
@Service
@Slf4j
public class DarajaApiImpl implements DarajaApi {
    private final MpesaConfiguration mpesaConfiguration;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    // Constructor Initialization of Variables
    public DarajaApiImpl(MpesaConfiguration mpesaConfiguration, OkHttpClient okHttpClient,
                         ObjectMapper objectMapper) {
        this.mpesaConfiguration = mpesaConfiguration;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    // Get Authorization Token of Mpesa
    @Override
    public AccessTokenResponse getAccessToken(){

        // Base64 Encoded Credentials used to get the token
        String encodedCredentials = HelperUtility.toBase64String(String.format
                ("%s:%s",mpesaConfiguration.getConsumerKey(),mpesaConfiguration.getConsumerSecret()));
        System.out.println("Base64 Encoded Credentials: "+ encodedCredentials);

        String encodedCredentials1 = "cFJZcjZ6anEwaThMMXp6d1FETUxwWkIzeVBDa2hNc2M6UmYyMkJmWm9nMHFRR2xWOQ==";

        // Request Building using OkHttp
        Request request = new Request.Builder()
                .url(String.format("%s?grant_type=%s",mpesaConfiguration.getOauthEndpoint(),
                        mpesaConfiguration.getGrantType()))
                .method("GET", null)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BASIC_AUTH_STRING, encodedCredentials1))
                .addHeader(CACHE_CONTROL_HEADER,CACHE_CONTROL_HEADER_VALUE)
                .build();

        // Response Generation using OkHttp


        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println("Response: "+ response.peekBody(Long.MAX_VALUE).string());
            // Check if the Response Body is Null
            assert response.body() != null;

            // Parsing JSON
            AccessTokenResponse Token = objectMapper.readValue(response.body().string(), AccessTokenResponse.class);
            String made_token = Token.getAccess_token();
            // System.out.println("Token: "+ made_token);
            return Token;
        } catch (IOException e) {
            log.error(String.format("Could not get the Authorization Token -> %s", e.getLocalizedMessage()));
            return null;
        }
    }


    // Register URL Response
    @Override
    public RegisterUrlResponse getUrlResponse(){

        // Get the Authorization Token
        AccessTokenResponse accessTokenResponse = getAccessToken();

        // Register URL Request. Get the Confirmation URL, Response Type,Short Code and Validation URL
        RegisterUrlRequest registerUrlRequest = new RegisterUrlRequest();
        registerUrlRequest.setConfirmationURL(mpesaConfiguration.getConfirmationUrl());
        registerUrlRequest.setResponseType(mpesaConfiguration.getResponseType());
        registerUrlRequest.setShortCode(mpesaConfiguration.getShortCode());
        registerUrlRequest.setValidationURL(mpesaConfiguration.getValidationUrl());

        System.out.println("Register URL Request: "+ registerUrlRequest.toString());

        // Register URL Response
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(registerUrlRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getRegisterUrlEndpoint())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("%s %s",BEARER_AUTH_STRING,accessTokenResponse.getAccess_token()))
                .build();

        System.out.println("Request Head and Body: "+ request);


        // Getting the Register URL Response
        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), RegisterUrlResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not register url -> %s", e.getLocalizedMessage()));
            return null;
        }
    }


    // Simulate Customer to Business Request and Response
    @Override
    public SimulateC2BResponse simulateC2BTransaction(SimulateC2BRequest simulateC2BRequest) {
        AccessTokenResponse accessTokenResponse = getAccessToken();

        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(simulateC2BRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getSimulateTransactionEndpoint())
                .method("POST", body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), SimulateC2BResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Simulate Customer To Business Transaction:  %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public B2CTransactionSyncResponse performB2CTransaction() {
        AccessTokenResponse accessTokenResponse = getAccessToken();
        B2CTransactionRequest b2CTransactionRequest = new B2CTransactionRequest();

        b2CTransactionRequest.setCommandID("SalaryPayment");
        b2CTransactionRequest.setAmount(AMOUNT_TO_BE_PAID);
        b2CTransactionRequest.setPartyB(mpesaConfiguration.getPartyB());
        b2CTransactionRequest.setRemarks("All the Remarks have been made");
        b2CTransactionRequest.setOccassion("Online Payment");

        // Get the Security Credentials
        b2CTransactionRequest.setSecurityCredential(HelperUtility.getSecurityCredentials
                (mpesaConfiguration.getB2cInitiatorPassword()));

        System.out.println(String.format("Security Creds: %s", b2CTransactionRequest.getSecurityCredential()));
        b2CTransactionRequest.setResultURL(mpesaConfiguration.getB2cResultUrl());
        b2CTransactionRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
        b2CTransactionRequest.setInitiatorName(mpesaConfiguration.getB2cInitiatorName());
        b2CTransactionRequest.setPartyA(mpesaConfiguration.getShortCode());

        System.out.println("Business to Customer Transaction: "+b2CTransactionRequest.toString());
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(b2CTransactionRequest)));

        // Sending the B2C Request
        Request request = new Request.Builder()
                .url(mpesaConfiguration.getB2cTransactionEndpoint())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",
                        BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .build();

        // B2CResponse
        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), B2CTransactionSyncResponse.class);
        } catch (IOException e) {
            System.out.println(String.format("Could not perform the B2C Transaction -> %s", e.getLocalizedMessage()));
            throw new RuntimeException(e);
        }
    }

    // Transaction Status Request and Response
    @Override
    public TransactionStatusSyncResponse getTransactionResult(InternalTransactionStatusRequest internalTransactionStatusRequest) {
        // ==== Transaction Status Request ==== //

        TransactionStatusRequest transactionStatusRequest = new TransactionStatusRequest();
        transactionStatusRequest.setTransactionID(internalTransactionStatusRequest.getTransactionID());

        transactionStatusRequest.setInitiator(mpesaConfiguration.getB2cInitiatorName());
        // transactionStatusRequest.setOriginatorConversationID("AG_20230810_2010149b526be1d36bc7");
        transactionStatusRequest.setSecurityCredential(HelperUtility.getSecurityCredentials
                (mpesaConfiguration.getB2cInitiatorPassword()));
        transactionStatusRequest.setCommandID(TRANSACTION_STATUS_QUERY_COMMAND);
        transactionStatusRequest.setPartyA(SHORT_CODE);
        transactionStatusRequest.setIdentifierType(SHORT_CODE_IDENTIFIER);
        transactionStatusRequest.setResultURL(mpesaConfiguration.getB2cResultUrl());
        transactionStatusRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
        transactionStatusRequest.setRemarks(TRANSACTION_STATUS_VALUE);
        transactionStatusRequest.setOccasion(TRANSACTION_STATUS_VALUE);

        // Get the Access Token
        AccessTokenResponse accessTokenResponse = getAccessToken();
        System.out.println("Transaction Status Request"+ transactionStatusRequest.toString());
        System.out.println("Transaction Status Request JSON"+ HelperUtility.toJSON(transactionStatusRequest));

        // Set up the Transaction Request
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(transactionStatusRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getTransactionResultUrl())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",
                        BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .build();

        System.out.println("Request: "+ request.toString());

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), TransactionStatusSyncResponse.class);
        } catch (IOException e) {
            System.out.println(String.format("Could not fetch the status of the transaction -> %s", e.getLocalizedMessage()));
            throw new RuntimeException(e);
        }
    }

    // ==== Check Account Balance ====
    @Override
    public CheckAccountBalanceSyncResponse getAccountBalance() {
        CheckAccountBalanceRequest checkAccountBalanceRequest = new CheckAccountBalanceRequest();

        checkAccountBalanceRequest.setInitiator(mpesaConfiguration.getB2cInitiatorName());
        checkAccountBalanceRequest.setSecurityCredential(HelperUtility.
                getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));

        System.out.println("Get the Security Credentials: "+HelperUtility.
                        getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));
        checkAccountBalanceRequest.setCommandID(ACCOUNT_BALANCE_COMMAND);
        checkAccountBalanceRequest.setPartyA(mpesaConfiguration.getShortCode());
        checkAccountBalanceRequest.setIdentifierType(SHORT_CODE_IDENTIFIER);
        checkAccountBalanceRequest.setRemarks(ACCOUNT_BALANCE_REMARKS);
        checkAccountBalanceRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
        checkAccountBalanceRequest.setResultURL(mpesaConfiguration.getB2cResultUrl());

        // Get the Access Token
        AccessTokenResponse accessTokenResponse = getAccessToken();

        System.out.println("Check Account Balance Request: "+HelperUtility.toJSON(checkAccountBalanceRequest));


        // Set up the Check Balance Request
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(checkAccountBalanceRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getAccountBalanceUrl())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",
                        BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), CheckAccountBalanceSyncResponse.class);
        } catch (IOException e) {
            System.out.println(String.format("Could not check the Account Balance -> %s", e.getLocalizedMessage()));
            throw new RuntimeException(e);
        }
    }

    // ==== External STK Push ====
    @Override
    public STKPushSyncResponse performSTKPushTransaction(InternalSTKPushRequest internalSTKPushRequest) {
        AccessTokenResponse accessTokenResponse = getAccessToken();

        // STK Push Functional Definition
        ExternalSTKPushRequest externalSTKPushRequest = new ExternalSTKPushRequest();

        externalSTKPushRequest.setBusinessShortCode(mpesaConfiguration.getStkPushShortCode());


        // STK Push Password
        String timeStamp = HelperUtility.getTransactionTimestamp();
        String StkPushPassword = HelperUtility.getStkPushPassword(mpesaConfiguration.getStkPushShortCode(),
                mpesaConfiguration.getStkPassKey(), timeStamp);
        externalSTKPushRequest.setPassword(StkPushPassword);
        externalSTKPushRequest.setTimestamp(timeStamp);
        externalSTKPushRequest.setTransactionType(CUSTOMER_PAYBILL_ONLINE);
        externalSTKPushRequest.setAmount(internalSTKPushRequest.getAmount());
        externalSTKPushRequest.setPartyA(internalSTKPushRequest.getPhoneNumber());
        externalSTKPushRequest.setPartyB(mpesaConfiguration.getStkPushShortCode());
        externalSTKPushRequest.setPhoneNumber(internalSTKPushRequest.getPhoneNumber());
        externalSTKPushRequest.setCallBackURL(mpesaConfiguration.getStkPushRequestCallbackUrl());
        externalSTKPushRequest.setAccountReference(HelperUtility.getTransactionUniqueNumber());
        externalSTKPushRequest.setTransactionDesc(String.format("%s Transaction",
                internalSTKPushRequest.getPhoneNumber()));

        System.out.println("External STK Push Request: "+HelperUtility.toJSON(externalSTKPushRequest));


        // Set up the Check Balance Request
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(externalSTKPushRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getStkPushRequestUrl())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",
                        BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), STKPushSyncResponse.class);
        } catch (IOException e) {
            System.out.println(String.format("Could not perform the STK Push Request -> %s", e.getLocalizedMessage()));
            throw new RuntimeException(e);
        }
    }


    // ==== Reverse Transfer ====
    @Override
    public TransactionReversalResponse performReversal(InternalTransaction internalTransaction) {
        // Get the Access Token
        AccessTokenResponse accessTokenResponse = getAccessToken();

        // Perform a Reverse Transaction
        TransactionReversal transactionReversal = new TransactionReversal();
        transactionReversal.setInitiator(mpesaConfiguration.getB2cInitiatorName());
        transactionReversal.setSecurityCredential(HelperUtility.
                getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));

        transactionReversal.setTransactionID(internalTransaction.getTransactionID());
        transactionReversal.setCommandID(REVERSAL_COMMAND);
        transactionReversal.setRemarks(ACCOUNT_BALANCE_REMARKS);
        transactionReversal.setReceiverParty(String.valueOf(mpesaConfiguration.getShortCode()));
        transactionReversal.setOccassion(internalTransaction.getOccassion());
        transactionReversal.setAmount(internalTransaction.getAmount());
        transactionReversal.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
        transactionReversal.setResultURL(mpesaConfiguration.getReversalResultUrl());
        transactionReversal.setRecieverIdentifierType(String.valueOf(RECIEVER_IDENTIFIER_TYPE));

        System.out.println("Transaction Reversal Request: "+HelperUtility.toJSON(transactionReversal));

        // Set up the Reverse Transaction Request
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(transactionReversal)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getApiReversalUrl())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",
                        BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), TransactionReversalResponse.class);
        } catch (IOException e) {
            System.out.println(String.format("Could not perform the Reverse Transaction Request -> %s", e.getLocalizedMessage()));
            throw new RuntimeException(e);
        }
    }


    // ==== Perform Tax Remittance ====
    @Override
    public TaxRemittanceResponse performTaxRemittance(InternalTaxRequest internalTaxRequest) {
        // Get the Access Token
        AccessTokenResponse accessTokenResponse = getAccessToken();

        TaxRemittanceRequest taxRemittanceRequest = new TaxRemittanceRequest();
        taxRemittanceRequest.setInitiator(mpesaConfiguration.getB2cInitiatorName());
        taxRemittanceRequest.setSecurityCredential(HelperUtility.
                getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));
        taxRemittanceRequest.setAmount(internalTaxRequest.getAmount());
        taxRemittanceRequest.setCommandID(TAX_COMMAND_KRA);
        taxRemittanceRequest.setPartyA(CUSTOMER_PAYBILL);
        taxRemittanceRequest.setPartyB(KRA_PAYBILL);
        taxRemittanceRequest.setAccountReference(ACCOUNT_REFERENCE);
        taxRemittanceRequest.setRemarks(REMARKS);
        taxRemittanceRequest.setResultURL(mpesaConfiguration.getTaxRemittanceResultUrl());
        taxRemittanceRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
        taxRemittanceRequest.setRecieverIdentifierType(KRA_RECIEVER_IDENTIFIER);
        taxRemittanceRequest.setSenderIdentifierType(KRA_SENDER_IDENTIFIER);

        System.out.println("Tax Remittance Request: "+HelperUtility.toJSON(taxRemittanceRequest));

        // Set up the Tax Remittance Request
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(taxRemittanceRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getTaxRemittanceUrl())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",
                        BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), TaxRemittanceResponse.class);
        } catch (IOException e) {
            System.out.println(String.format("Could not perform the KRA Tax Remittance Transaction Request -> %s", e.getLocalizedMessage()));
            throw new RuntimeException(e);
        }
    }


    // ==== Generate QR Code ====
    @Override
    public QRCodeResponse performQRCode() {
        // Get the Access Token
        AccessTokenResponse accessTokenResponse = getAccessToken();

        // Generate QR Code
        QRCodeRequest qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setSize(QR_CODE_SIZE);
        qrCodeRequest.setCPI(mpesaConfiguration.getStkPushShortCode());
        qrCodeRequest.setAmount(AMOUNT_TO_BE_PAID);
        qrCodeRequest.setTrxCode(TRX_CODE);
        qrCodeRequest.setRefNo(REF_NO);
        qrCodeRequest.setMerchantName(MERCHANT_NAME);

        System.out.println("QR Code Request: "+HelperUtility.toJSON(qrCodeRequest));

        // Set up the Tax Remittance Request
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(qrCodeRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getQrCodeUrl())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s",
                        BEARER_AUTH_STRING, accessTokenResponse.getAccess_token()))
                .build();


        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), QRCodeResponse.class);
        } catch (IOException e) {
            System.out.println(String.format("Could not generate the qr code request -> %s", e.getLocalizedMessage()));
            throw new RuntimeException(e);
        }
    }


}
