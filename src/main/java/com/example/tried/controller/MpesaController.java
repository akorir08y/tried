package com.example.tried.controller;


import com.example.tried.config.MpesaConfiguration;
import com.example.tried.dto.account.CheckAccountBalanceSyncResponse;
import com.example.tried.dto.account.InternalTransaction;
import com.example.tried.dto.account.TransactionReversalAsyncResponse;
import com.example.tried.dto.account.TransactionReversalResponse;
import com.example.tried.dto.b2c.B2CTransactionAsyncResponse;
import com.example.tried.dto.b2c.B2CTransactionSyncResponse;
import com.example.tried.dto.b2c.TransactionResults;
import com.example.tried.dto.c2b.AcknowledgeResponse;
import com.example.tried.dto.c2b.RegisterUrlResponse;
import com.example.tried.dto.c2b.SimulateC2BRequest;
import com.example.tried.dto.c2b.SimulateC2BResponse;
import com.example.tried.dto.express.*;
import com.example.tried.dto.kra.InternalTaxRequest;
import com.example.tried.dto.kra.TaxRemittanceAsyncResponse;
import com.example.tried.dto.kra.TaxRemittanceResponse;
import com.example.tried.dto.qrcode.QRCodeResponse;
import com.example.tried.dto.token.AccessTokenResponse;
import com.example.tried.dto.transactionstatus.InternalTransactionStatusRequest;
import com.example.tried.dto.transactionstatus.TransactionStatusSyncResponse;
import com.example.tried.services.DarajaApi;
import com.example.tried.services.MpesaService;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.util.*;

@RestController
@RequestMapping("mobile-money")
@Slf4j
public class MpesaController {
    private final DarajaApi darajaApi;
    private final ObjectMapper objectMapper;
    private final AcknowledgeResponse acknowledgeResponse;

    @Autowired
    MpesaService mpesaService;

    private final MpesaConfiguration mpesaConfiguration;

    Map<String,String> otpMap = new HashMap<>();

    public MpesaController(DarajaApi darajaApi, ObjectMapper objectMapper,
                           AcknowledgeResponse acknowledgeResponse, MpesaConfiguration mpesaConfiguration) {
        this.darajaApi = darajaApi;
        this.objectMapper = objectMapper;
        this.acknowledgeResponse = acknowledgeResponse;
        this.mpesaConfiguration = mpesaConfiguration;
    }

    // Get the Access Token
    // http://localhost:8080/mobile-money/token
    @GetMapping(path="/token",produces = "application/json")
    public ResponseEntity<AccessTokenResponse> getAccessToken(){
        return ResponseEntity.ok(darajaApi.getAccessToken());
    }

    // Get the Register URL
    // http://localhost:8080/mobile-money/register-url
    @PostMapping(path="/register-url", produces = "application/json")
    public ResponseEntity<RegisterUrlResponse> registerUrl(){
        return ResponseEntity.ok(darajaApi.getUrlResponse());
    }


    // Get the Validation
    // http://localhost:8080/mobile-money/validation
    @PostMapping(path="/validation", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> validateTransaction(@RequestBody TransactionResults transactionResults){
        return ResponseEntity.ok(acknowledgeResponse);
    }

    // Simulate Customer to Business Request
    // http://localhost:8080/mobile-money/simulate-c2b
    @PostMapping(path="/simulate-c2b", produces = "application/json")
    public ResponseEntity<SimulateC2BResponse> simulateC2BTransaction(@RequestBody SimulateC2BRequest simulateC2BRequest){
        return ResponseEntity.ok(darajaApi.simulateC2BTransaction(simulateC2BRequest));
    }

    // ==== Business to Customer Transaction Region ==== //
    @SneakyThrows
    @PostMapping(path= "/transaction-result", produces="application/json")
    public ResponseEntity<AcknowledgeResponse> b2cTransactionAsyncResults(@RequestBody B2CTransactionAsyncResponse
                                                                                  b2CTransactionAsyncResponse){
        log.info("==== B2CTransaction Response ====");
        log.info(objectMapper.writeValueAsString(b2CTransactionAsyncResponse));
        return ResponseEntity.ok(acknowledgeResponse);
    }

    // Business to Customer Query Timeout
    @PostMapping(path="/b2c-queue-timeout", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> queueTimeout(@RequestBody Object object){
        return ResponseEntity.ok(acknowledgeResponse);
    }

    // Perform Business to Customer Transaction
    @PostMapping(path="/b2c-transaction", produces = "application/json")
    public ResponseEntity<B2CTransactionSyncResponse> performB2CTransaction(){//@RequestBody InternalB2CTransactionRequest
                                                                                    //internalB2CTransactionRequest){
        // internalB2CTransactionRequest
        return ResponseEntity.ok(darajaApi.performB2CTransaction());
    }

    // Get the Transaction Status Result
    @PostMapping(path="/simulate-transaction-result", produces="application/json")
    public ResponseEntity<TransactionStatusSyncResponse> getTransactionStatusResult(@RequestBody InternalTransactionStatusRequest internalTransactionStatusRequest){
        return ResponseEntity.ok(darajaApi.getTransactionResult(internalTransactionStatusRequest));
    }

    // Check the Account Balance
    @GetMapping(path="/check-account-balance", produces = "application/json")
    public ResponseEntity<CheckAccountBalanceSyncResponse> checkAccountBalance(){
        return ResponseEntity.ok(darajaApi.getAccountBalance());
    }

    // Perform the STK Push Request
    @PostMapping(path="/stk-transaction-request", produces = "application/json")
    public ResponseEntity<STKPushSyncResponse> performStkPushTransaction
    (@RequestBody InternalSTKPushRequest internalSTKPushRequest){

        // STK Push Response
        STKPushSyncResponse response = darajaApi.performSTKPushTransaction(internalSTKPushRequest);
        otpMap.put(response.getCheckoutRequestID(), internalSTKPushRequest.getPhoneNumber());

        // Mpesa Transaction Start
        MpesaTransaction mpesaTransaction = new MpesaTransaction();
        mpesaTransaction.setPayment_amount(Double.parseDouble(internalSTKPushRequest.getAmount()));
        mpesaTransaction.setInvoice_id(internalSTKPushRequest.getInvoice_id());
        mpesaTransaction.setInstitution_number(internalSTKPushRequest.getInstitution_number());
        mpesaTransaction.setInstitution_name(internalSTKPushRequest.getInstitution_name());
        mpesaTransaction.setInstitution_level(internalSTKPushRequest.getInstitution_level());
        mpesaTransaction.setPaybill_number(mpesaConfiguration.getStkPushShortCode());
        mpesaTransaction.setPhone_number(internalSTKPushRequest.getPhoneNumber());
        mpesaTransaction.setCheckout_code(response.getCheckoutRequestID());
        mpesaService.saveTransaction(mpesaTransaction);

        return ResponseEntity.ok(response);
    }

    // Get the STK Response
    @SneakyThrows
    @PostMapping(path="/stk-transaction-result", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> acknowledgeStkPushResponse
            (@RequestBody STKPushAsyncResponse stkPushAsyncResponse){

        log.info("==== STK Push Async Response ====");
        log.info(objectMapper.writeValueAsString(stkPushAsyncResponse));

        String getCheckOutId = stkPushAsyncResponse.getBody().getStkCallback().getCheckoutRequestID();
        CallbackMetadata items = stkPushAsyncResponse.getBody().getStkCallback().getCallbackMetadata();
        Set<String> keys = otpMap.keySet();

        // Get the Callback Metadata
        String payment_amount = String.valueOf(items.getItem().get(0).getValue());
        String payment_receipt_no = String.valueOf(items.getItem().get(1).getValue());
        String Balance = String.valueOf(items.getItem().get(2).getValue());
        String transaction_date = String.valueOf(items.getItem().get(3).getValue());
        String phone_number = String.valueOf(items.getItem().get(4).getValue());

        // Mobile Payment Transaction
        String checkOutCode = "";
        for(String key : keys)
            checkOutCode = key;
        String compare = otpMap.get(checkOutCode);

        // Saving Transaction
        if (getCheckOutId.equals(checkOutCode)) {
            if(phone_number.equals(compare)){
                MpesaTransaction mpesaTransaction = new MpesaTransaction();
                mpesaTransaction.setPayment_receipt_no(payment_receipt_no);
                mpesaTransaction.setTransaction_date(transaction_date);
                mpesaService.updateTransaction(mpesaTransaction);
            }
        }

        return ResponseEntity.ok(acknowledgeResponse);
    }

    // Reverse the Transaction
    @PostMapping(path="/transaction-reversal", produces = "application/json")
    public ResponseEntity<TransactionReversalResponse> performReversal
    (@RequestBody InternalTransaction internalTransaction){
        return ResponseEntity.ok(darajaApi.performReversal(internalTransaction));
    }


    // Get the Transaction Reversal Response
    @SneakyThrows
    @PostMapping(path="/transaction-reversal-result", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> reverseTransaction
    (@RequestBody TransactionReversalAsyncResponse transactionReversalAsyncResponse){

        log.info("==== Reverse the Transaction ====");
        log.info(objectMapper.writeValueAsString(transactionReversalAsyncResponse));

        FileWriter file = new FileWriter("reverse_transfer.json");
        file.write(Objects.requireNonNull(HelperUtility.toJSON(transactionReversalAsyncResponse)));
        file.flush();


        return ResponseEntity.ok(acknowledgeResponse);
    }


    @SneakyThrows
    @PostMapping(path="/tax-transaction-request", produces = "application/json")
    public ResponseEntity<TaxRemittanceResponse> performTaxTransaction
            (@RequestBody InternalTaxRequest internalTaxRequest){

        return ResponseEntity.ok(darajaApi.performTaxRemittance(internalTaxRequest));
    }


    @SneakyThrows
    @PostMapping(path="/tax-transaction-result", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> getTaxTransactionResult
            (@RequestBody TaxRemittanceAsyncResponse remittanceAsyncResponse){

        log.info("==== KRA Tax Transaction Response ====");
        log.info(objectMapper.writeValueAsString(remittanceAsyncResponse));

        FileWriter file = new FileWriter("kra_transfer.json");
        file.write(Objects.requireNonNull(HelperUtility.toJSON(remittanceAsyncResponse)));
        file.flush();


        return ResponseEntity.ok(acknowledgeResponse);
    }


    @SneakyThrows
    @PostMapping(path="/generate-qr-code", produces = "application/json")
    public ResponseEntity<QRCodeResponse> generateQRCode(){
        return ResponseEntity.ok(darajaApi.performQRCode());
    }
}
