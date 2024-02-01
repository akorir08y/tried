package com.example.tried.services;

import com.example.tried.dto.account.CheckAccountBalanceSyncResponse;
import com.example.tried.dto.account.InternalTransaction;
import com.example.tried.dto.account.TransactionReversalResponse;
import com.example.tried.dto.b2c.B2CTransactionSyncResponse;
import com.example.tried.dto.c2b.RegisterUrlResponse;
import com.example.tried.dto.c2b.SimulateC2BRequest;
import com.example.tried.dto.c2b.SimulateC2BResponse;
import com.example.tried.dto.express.InternalSTKPushRequest;
import com.example.tried.dto.express.STKPushSyncResponse;
import com.example.tried.dto.kra.InternalTaxRequest;
import com.example.tried.dto.kra.TaxRemittanceResponse;
import com.example.tried.dto.qrcode.QRCodeResponse;
import com.example.tried.dto.token.AccessTokenResponse;
import com.example.tried.dto.transactionstatus.InternalTransactionStatusRequest;
import com.example.tried.dto.transactionstatus.TransactionStatusSyncResponse;

// Interface to Access the Daraja API Functionality
public interface DarajaApi {

    AccessTokenResponse getAccessToken();

    RegisterUrlResponse getUrlResponse();

    SimulateC2BResponse simulateC2BTransaction(SimulateC2BRequest simulateC2BRequest);

    // B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest);
    B2CTransactionSyncResponse performB2CTransaction();

    TransactionStatusSyncResponse getTransactionResult(InternalTransactionStatusRequest internalTransactionStatusRequest);

    CheckAccountBalanceSyncResponse getAccountBalance();

    STKPushSyncResponse performSTKPushTransaction(InternalSTKPushRequest internalSTKPushRequest);

    TransactionReversalResponse performReversal(InternalTransaction internalTransaction);

    TaxRemittanceResponse performTaxRemittance(InternalTaxRequest internalTaxRequest);


    QRCodeResponse performQRCode();
}
