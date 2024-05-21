package com.example.tried.services;

import com.example.tried.auth.dto.SMS;
import com.example.tried.auth.dto.SMSResponse;
import com.example.tried.auth.dto.SmsRequest;
import com.example.tried.auth.funds.SelectTrustFunds;
import com.example.tried.auth.funds.SelectTrustFundsResponse;
import com.example.tried.auth.funds.delete.DeleteCashReceipting;
import com.example.tried.auth.funds.delete.DeleteCashReceiptingResponse;
import com.example.tried.auth.funds.non.SelectNonTrustFunds;
import com.example.tried.auth.funds.non.SelectNonTrustFundsResponse;
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
import com.example.tried.auth.personnel.receipting.edit.EditCashReceipting;
import com.example.tried.auth.personnel.receipting.edit.EditCashReceiptingResponse;
import com.example.tried.auth.personnel.receipting.select.SelectCashReceipting;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscript;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscript1;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscriptResponse;
import com.example.tried.auth.personnel.transfer.MobileReceiveFundsTransfer;
import com.example.tried.auth.personnel.transfer.MobileReceiveFundsTransferResponse;
import com.example.tried.auth.reports.payment_mode.TrustFundsSummaryWithPaymentMode;
import com.example.tried.auth.reports.payment_mode.TrustFundsSummaryWithPaymentModeResponse;
import com.example.tried.auth.reports.payment_mode.date_to_date.TrustFundSummaryDateToDatePaymentMode;
import com.example.tried.auth.reports.payment_mode.date_to_date.TrustFundSummaryDateToDatePaymentModeResponse;
import com.example.tried.auth.reports.specific.LocalChurchSpecificAccountSummary;
import com.example.tried.auth.reports.specific.LocalChurchSpecificAccountSummaryResponse;
import com.example.tried.auth.reports.statements.LocalChurchOfferingStatement;
import com.example.tried.auth.reports.statements.LocalChurchOfferingStatementResponse;
import com.example.tried.auth.reports.trust_funds_date_to_date.TrustFundDateToDate;
import com.example.tried.auth.reports.trust_funds_date_to_date.TrustFundDateToDateResponse;
import com.example.tried.auth.tracing.TransactionTracingMember;
import com.example.tried.auth.tracing.TransactionTracingMemberResponse;
import com.example.tried.auth.tracing.receipt.TransactionTracingMemberReceipt;
import com.example.tried.auth.tracing.receipt.TransactionTracingMemberReceiptResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;

public interface PersonnelApi {

    // Personnel Cash Receipting
    CashReceiptingResponse getCashReceipting(CashReceipting receipting) throws JsonProcessingException;

    // Creating Local Church Accounts
    CreateLocalChurchAccountResponse createLocalChurchAccount(CreateLocalChurchAccount localChurchAccount) throws JsonProcessingException;

    // Updating Local Church Accounts
    UpdateLocalChurchAccountResponse updateLocalChurchAccount(UpdateLocalChurchAccount localChurchAccount) throws JsonProcessingException;

    // Generate Trust Fund Transcript Report
    TrustFundTranscriptResponse getTrustFundTranscript(TrustFundTranscript transcript) throws JsonProcessingException;


    // Generate Trust Fund Transcript Report without Any Payment Modes
    TrustFundTranscriptResponse getTrustFundTranscriptAll(TrustFundTranscript1 transcript) throws JsonProcessingException;


    // Select Local Church Accounts
    LocalChurchAccountsSelectResponse selectLocalChurchAccounts(LocalChurchAccounts selectAccounts) throws JsonProcessingException;


    // List Payment Accounts for Personnel Receipting
    ListLocalChurchPaymentAccountsResponse selectChurchPaymentAccounts(ListLocalChurchPaymentAccounts selectAccounts) throws JsonProcessingException;


    // Generate Specific Offering Summary Statement
    SpecificOfferingStatementResponse getSpecificMemberOfferingStatement(SpecificOfferingStatement statement) throws JsonProcessingException;



    // Local Church Specific Account Summary
    LocalChurchSpecificAccountSummaryResponse getLocalChurchSpecificAccountSummary
    (LocalChurchSpecificAccountSummary specificAccountSummary) throws JsonProcessingException;


    // Get Local Church Accounts
    public HashMap<String, Object> getLocalChurchAccounts(HashMap<String,Object> hashMap) throws JsonProcessingException;


    // Get the Departmental Accounts
    public DepartmentResponse getDepartmentAccounts(DepartmentRequest request) throws JsonProcessingException;


    // Get the Local Church Offering Statement
    public LocalChurchOfferingStatementResponse getLocalChurchOfferingStatement(LocalChurchOfferingStatement statement) throws JsonProcessingException;

    // Get the Trust Funds for Transfer
    public SelectTrustFundsResponse getTrustFundsforTransfer(SelectTrustFunds trustFunds) throws JsonProcessingException;


    // Get Transaction Tracing Using Member Phone Number
    public TransactionTracingMemberResponse getTransactionTracingByPhone(TransactionTracingMember tracing) throws JsonProcessingException;

    // Get Transaction Tracing Using Receipt Number
    public TransactionTracingMemberReceiptResponse getTransactionTracingByReceipt(TransactionTracingMemberReceipt receipt) throws JsonProcessingException;

    // Get the Specific Account Summary Reports
    public LocalChurchSpecificAccountSummaryResponse getSpecificAccountSummary(LocalChurchSpecificAccountSummary localChurchSpecificAccountSummary) throws JsonProcessingException;

    // Select Cash Receipting Transactions
    public HashMap<String, Object> getCashReceiptingTransactions(SelectCashReceipting transactions) throws JsonProcessingException;


    // Send Registration Message
    public SMSResponse sendRegistrationMessage(SMS request) throws JsonProcessingException;


    // Trust Fund Date to Date Summary
    public TrustFundDateToDateResponse getTrustFromDateToDate(TrustFundDateToDate dateToDate) throws JsonProcessingException;

    // Trust Fund Summary Month By Month Report
    public TrustFundsSummaryWithPaymentModeResponse getTrustFundSummaryWithPaymentMode(TrustFundsSummaryWithPaymentMode trustFundsPayment)
            throws JsonProcessingException;

    // Trust Fund Summary Date By Date Report
    public TrustFundSummaryDateToDatePaymentModeResponse getTrustFundSummaryDateByDateWithPaymentMode(TrustFundSummaryDateToDatePaymentMode dateToDatePaymentMode)
            throws JsonProcessingException;


    // Fund Transfer
    public MobileReceiveFundsTransferResponse getFundsTransfertoConference(MobileReceiveFundsTransfer fundsTransfer) throws JsonProcessingException;


    public EditCashReceiptingResponse editCashReceipt(EditCashReceipting receipting) throws JsonProcessingException;

    // Delete Cash Receipts
    public DeleteCashReceiptingResponse deleteCashReceipt(DeleteCashReceipting cashReceipting) throws JsonProcessingException;


    // Select Non Trust Funds
    public SelectNonTrustFundsResponse getNonTrustFunds(SelectNonTrustFunds nonTrustFunds) throws JsonProcessingException;
}

