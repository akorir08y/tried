package com.example.tried.services;

import com.example.tried.auth.member.specific.SpecificOfferingStatement;
import com.example.tried.auth.member.specific.SpecificOfferingStatementResponse;
import com.example.tried.auth.personnel.accounts.LocalChurchAccounts;
import com.example.tried.auth.personnel.accounts.LocalChurchAccountsSelectResponse;
import com.example.tried.auth.personnel.accounts.new_account.CreateLocalChurchAccount;
import com.example.tried.auth.personnel.accounts.new_account.CreateLocalChurchAccountResponse;
import com.example.tried.auth.personnel.accounts.update_account.UpdateLocalChurchAccount;
import com.example.tried.auth.personnel.accounts.update_account.UpdateLocalChurchAccountResponse;
import com.example.tried.auth.personnel.department.DepartmentRequest;
import com.example.tried.auth.personnel.department.DepartmentResponse;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccounts;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccountsResponse;
import com.example.tried.auth.personnel.receipting.CashReceipting;
import com.example.tried.auth.personnel.receipting.CashReceiptingResponse;
import com.example.tried.auth.personnel.receipting.traced.member.TransactionTracingMemberPhone;
import com.example.tried.auth.personnel.receipting.traced.member.TransactionTracingMemberPhoneResponse;
import com.example.tried.auth.personnel.receipting.traced.receipt.TransactionTracingMemberReceipt;
import com.example.tried.auth.personnel.receipting.traced.receipt.TransactionTracingMemberReceiptResponse;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscript;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscriptResponse;
import com.example.tried.auth.reports.specific.LocalChurchSpecificAccountSummary;
import com.example.tried.auth.reports.specific.LocalChurchSpecificAccountSummaryResponse;
import org.json.JSONObject;

import java.util.HashMap;

public interface PersonnelApi {

    // Personnel Cash Receipting
    CashReceiptingResponse getCashReceipting(CashReceipting receipting);

    // Creating Local Church Accounts
    CreateLocalChurchAccountResponse createLocalChurchAccount(CreateLocalChurchAccount localChurchAccount);

    // Updating Local Church Accounts
    UpdateLocalChurchAccountResponse updateLocalChurchAccount(UpdateLocalChurchAccount localChurchAccount);

    // Generate Trust Fund Transcript Report
    TrustFundTranscriptResponse getTrustFundTranscript(TrustFundTranscript transcript);


    // Select Local Church Accounts
    LocalChurchAccountsSelectResponse selectLocalChurchAccounts(LocalChurchAccounts selectAccounts);


    // List Payment Accounts for Personnel Receipting
    ListLocalChurchPaymentAccountsResponse selectChurchPaymentAccounts(ListLocalChurchPaymentAccounts selectAccounts);


    // Generate Specific Offering Summary Statement
    SpecificOfferingStatementResponse getSpecificMemberOfferingStatement(SpecificOfferingStatement statement);

    //Use Phone Number to do Transaction Tracing
    TransactionTracingMemberPhoneResponse getTransactionTracingMemberByPhoneNumber
    (TransactionTracingMemberPhone memberPhone);

    // Use Receipt Number to do Transaction Tracing
    TransactionTracingMemberReceiptResponse getTransactionTracingMemberByReceiptNumber
    (TransactionTracingMemberReceipt receipt);

    // Local Church Specific Account Summary
    LocalChurchSpecificAccountSummaryResponse getLocalChurchSpecificAccountSummary
    (LocalChurchSpecificAccountSummary specificAccountSummary);


    // Get Local Church Accounts
    public HashMap<String, Object> getLocalChurchAccounts(HashMap<String,Object> hashMap);


    // Get the Departmental Accounts
    public DepartmentResponse getDepartmentAccounts(DepartmentRequest request);
}
