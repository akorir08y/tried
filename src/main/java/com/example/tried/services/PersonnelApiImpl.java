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
import com.example.tried.auth.personnel.accounts.new_account.Authentication;
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
import com.example.tried.config.AuthConfiguration;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.tried.utils.Constants.JSON_MEDIA_TYPE;

@Service
@Slf4j
public class PersonnelApiImpl implements PersonnelApi {

    private final AuthConfiguration authConfiguration;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public PersonnelApiImpl(AuthConfiguration authConfiguration, OkHttpClient client, ObjectMapper objectMapper) {
        this.authConfiguration = authConfiguration;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public CashReceiptingResponse getCashReceipting(CashReceipting receipting) throws JsonProcessingException {
        receipting.setFunction("cashTransactionConfirmation");
        String requested = objectMapper.writeValueAsString(receipting);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(receipting));
        Request request = new Request.Builder()
                .url(authConfiguration.getPayment_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), CashReceiptingResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not complete Cash Receipting Process -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), CashReceiptingResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Edit Cash Receipting
    @Override
    public EditCashReceiptingResponse editCashReceipt(EditCashReceipting receipting) throws JsonProcessingException {
        receipting.setFunction("cashTransactionConfirmation");
        receipting.setFunctionType("edit");
        String requested = objectMapper.writeValueAsString(receipting);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(receipting));
        Request request = new Request.Builder()
                .url(authConfiguration.getPayment_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), EditCashReceiptingResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not complete Cash Receipting Editing Process -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), EditCashReceiptingResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Create Local Church Accounts
    @Override
    public CreateLocalChurchAccountResponse createLocalChurchAccount(CreateLocalChurchAccount localChurchAccount) throws JsonProcessingException {
        Authentication authentication = new Authentication();
        authentication.setPassword("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setUserName("7Zu2pBUFgsaTOuMOvfqNpg==");

        localChurchAccount.setAuthentication(authentication);
        localChurchAccount.setFunction("createLocalChurchAccount");

        String requested = objectMapper.writeValueAsString(localChurchAccount);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(localChurchAccount)));

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(localChurchAccount));
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), CreateLocalChurchAccountResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not complete Create Local Church Account -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), CreateLocalChurchAccountResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public UpdateLocalChurchAccountResponse updateLocalChurchAccount(UpdateLocalChurchAccount localChurchAccount) throws JsonProcessingException {
        com.example.tried.auth.personnel.accounts.update_account.Authentication authentication = new
                com.example.tried.auth.personnel.accounts.update_account.Authentication();
        authentication.setPassword("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setUserName("7Zu2pBUFgsaTOuMOvfqNpg==");

        localChurchAccount.setAuthentication(authentication);
        localChurchAccount.setFunction("createLocalChurchAccount");

        String requested = objectMapper.writeValueAsString(localChurchAccount);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(localChurchAccount));
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), UpdateLocalChurchAccountResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not complete Update Local Church Account -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), UpdateLocalChurchAccountResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public TrustFundTranscriptResponse getTrustFundTranscript(TrustFundTranscript transcript) throws JsonProcessingException {
        transcript.setFunction("getLocalChurchTrustFundTranscript");
        String requested = objectMapper.writeValueAsString(transcript);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        System.out.println("Request Body JSON: " + HelperUtility.toJSON(transcript));
        Request request = new Request.Builder()
                .url(authConfiguration.getTrust_funds_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TrustFundTranscriptResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Trust Fund Transcript -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), TrustFundTranscriptResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    @Override
    public TrustFundTranscriptResponse getTrustFundTranscriptAll(TrustFundTranscript1 transcript) throws JsonProcessingException {
        transcript.setFunction("getLocalChurchTrustFundTranscript");
        String requested = objectMapper.writeValueAsString(transcript);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        System.out.println("Request Body JSON: " + HelperUtility.toJSON(transcript));
        Request request = new Request.Builder()
                .url(authConfiguration.getTrust_funds_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TrustFundTranscriptResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Trust Fund Transcript -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), TrustFundTranscriptResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchAccountsSelectResponse selectLocalChurchAccounts(LocalChurchAccounts selectAccounts) throws JsonProcessingException {
        com.example.tried.auth.personnel.accounts.Authentication authentication =
                new com.example.tried.auth.personnel.accounts.Authentication();
        authentication.setUserName("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setPassword("7Zu2pBUFgsaTOuMOvfqNpg==");

        selectAccounts.setFunction("createLocalChurchAccount");
        selectAccounts.setAuthentication(authentication);

        String requested = objectMapper.writeValueAsString(selectAccounts);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(selectAccounts));
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), LocalChurchAccountsSelectResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Trust Fund Transcript -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), LocalChurchAccountsSelectResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public ListLocalChurchPaymentAccountsResponse selectChurchPaymentAccounts(ListLocalChurchPaymentAccounts selectAccounts) throws JsonProcessingException {
        selectAccounts.setFunction("getLocalChurchPaymentAccounts");
        String requested = objectMapper.writeValueAsString(selectAccounts);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(selectAccounts));
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), ListLocalChurchPaymentAccountsResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Local Church Payment Accounts -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), ListLocalChurchPaymentAccountsResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public SpecificOfferingStatementResponse getSpecificMemberOfferingStatement(SpecificOfferingStatement statement) throws JsonProcessingException {
        statement.setFunction("getMemberSpecificOfferingStatement");
        String requested = objectMapper.writeValueAsString(statement);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(statement));
        Request request = new Request.Builder()
                .url(authConfiguration.getDashboard_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), SpecificOfferingStatementResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not generate Specific Offering Statement -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), SpecificOfferingStatementResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchSpecificAccountSummaryResponse getLocalChurchSpecificAccountSummary(LocalChurchSpecificAccountSummary specificAccountSummary) throws JsonProcessingException {
        specificAccountSummary.setFunction("getLocalChurchSpecificAccountSummary");
        String requested = objectMapper.writeValueAsString(specificAccountSummary);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(specificAccountSummary));
        Request request = new Request.Builder()
                .url(authConfiguration.getReconciliation_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), LocalChurchSpecificAccountSummaryResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Local Church Specific Account Summary -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), LocalChurchSpecificAccountSummaryResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public HashMap<String, Object> getLocalChurchAccounts(HashMap<String, Object> jsonObject) throws JsonProcessingException {

        String requested = objectMapper.writeValueAsString(jsonObject);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), HashMap.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Local Church Specific Account Summary -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public DepartmentResponse getDepartmentAccounts(DepartmentRequest departmentRequest) throws JsonProcessingException {
        departmentRequest.setFunction("getDepartmentalAccountCreationMetaData");
        String requested = objectMapper.writeValueAsString(departmentRequest);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), DepartmentResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Department Accounts of the Local Church Accounts -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public LocalChurchOfferingStatementResponse getLocalChurchOfferingStatement(LocalChurchOfferingStatement statement) throws JsonProcessingException {
        statement.setFunction("getLocalChurchOfferingStatement");
        String requested = objectMapper.writeValueAsString(statement);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getFinancial_data_https_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), LocalChurchOfferingStatementResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Local Church Offering Statement -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public SelectTrustFundsResponse getTrustFundsforTransfer(SelectTrustFunds trustFunds) throws JsonProcessingException {
        trustFunds.setFunction("getTrustFunds");
        String requested = objectMapper.writeValueAsString(trustFunds);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), SelectTrustFundsResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Trust Funds for Transfer -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public TransactionTracingMemberResponse getTransactionTracingByPhone(TransactionTracingMember tracing) throws JsonProcessingException {
        tracing.setFunction("getMemberPaymentTraceReport");
        String requested = objectMapper.writeValueAsString(tracing);

        System.out.println("Tracing Member Phone: "+ requested);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getReconciliation_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TransactionTracingMemberResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Transaction Tracing using Phone Number -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public TransactionTracingMemberReceiptResponse getTransactionTracingByReceipt(TransactionTracingMemberReceipt receipt) throws JsonProcessingException {
        receipt.setFunction("getReceiptPaymentTraceReport");
        String requested = objectMapper.writeValueAsString(receipt);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getReconciliation_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TransactionTracingMemberReceiptResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Transaction Tracing using Receipt -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public LocalChurchSpecificAccountSummaryResponse getSpecificAccountSummary(LocalChurchSpecificAccountSummary localChurchSpecificAccountSummary) throws JsonProcessingException {
        localChurchSpecificAccountSummary.setFunction("getLocalChurchSpecificAccountSummary");
        String requested = objectMapper.writeValueAsString(localChurchSpecificAccountSummary);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getReconciliation_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), LocalChurchSpecificAccountSummaryResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Local Church Specific Account Summary -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public HashMap<String, Object> getCashReceiptingTransactions(SelectCashReceipting transactions) throws JsonProcessingException {
        transactions.setFunction("cashTransactionConfirmation");
        transactions.setFunctionType("select");
        String requested = objectMapper.writeValueAsString(transactions);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        Request request = new Request.Builder()
                .url(authConfiguration.getPayment_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), HashMap.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Local Church Specific Account Summary -> %s", e.getLocalizedMessage()));
            return null;
        }
    }


    @Override
    public SMSResponse sendRegistrationMessage(SMS requested_sms) throws JsonProcessingException {
        System.out.println("SMS Request: "+ Objects.requireNonNull(HelperUtility.toJSON(requested_sms)));

        String requested = objectMapper.writeValueAsString(requested_sms);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getSms_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), SMSResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Send Registration Message -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public TrustFundDateToDateResponse getTrustFromDateToDate(TrustFundDateToDate dateToDate) throws JsonProcessingException {
        dateToDate.setFunction("getLocalChurchDateToDateTrustFundsSummary");
        System.out.println("Trust Fund Date to Date: "+ Objects.requireNonNull(HelperUtility.toJSON(dateToDate)));
        String requested = objectMapper.writeValueAsString(dateToDate);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getTrust_funds_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TrustFundDateToDateResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Generate Date to Date Trust Fund Summary -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public TrustFundsSummaryWithPaymentModeResponse getTrustFundSummaryWithPaymentMode(TrustFundsSummaryWithPaymentMode trustFundsPayment)
            throws JsonProcessingException {
        trustFundsPayment.setFunction("getLocalChurchTrustFundsSummaryWithPaymentModeDetails");
        System.out.println("Trust Fund With Payment Mode: "+ Objects.requireNonNull(HelperUtility.toJSON(trustFundsPayment)));
        String requested = objectMapper.writeValueAsString(trustFundsPayment);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getTrust_funds_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TrustFundsSummaryWithPaymentModeResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Generate Trust Fund Summary With Payment Mode -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public TrustFundSummaryDateToDatePaymentModeResponse getTrustFundSummaryDateByDateWithPaymentMode(TrustFundSummaryDateToDatePaymentMode dateToDatePaymentMode) throws JsonProcessingException {
        System.out.println("Trust Fund With Date to Date Payment Mode: "+ Objects.requireNonNull(HelperUtility.toJSON( dateToDatePaymentMode)));
        dateToDatePaymentMode.setFunction("getLocalChurchDateToDateTrustFundsSummaryWithPaymentModeDetails");
        String requested = objectMapper.writeValueAsString( dateToDatePaymentMode);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getTrust_funds_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TrustFundSummaryDateToDatePaymentModeResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Generate Trust Fund Summary Date To Date With Payment Mode -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public MobileReceiveFundsTransferResponse getFundsTransfertoConference(MobileReceiveFundsTransfer fundsTransfer) throws JsonProcessingException {
        fundsTransfer.setFunction("mobileReceiveFunds");
        System.out.println("Funds Transfer: "+ Objects.requireNonNull(HelperUtility.toJSON(fundsTransfer)));

        String requested = objectMapper.writeValueAsString(fundsTransfer);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), MobileReceiveFundsTransferResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Generate Funds Transfer to Conference -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public DeleteCashReceiptingResponse deleteCashReceipt(DeleteCashReceipting cashReceipting) throws JsonProcessingException {
        cashReceipting.setFunction("cashTransactionConfirmation");
        cashReceipting.setFunctionType("delete");

        System.out.println("Delete Cash Receipt: "+ Objects.requireNonNull(HelperUtility.toJSON(cashReceipting)));

        String requested = objectMapper.writeValueAsString(cashReceipting);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getPayment_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), DeleteCashReceiptingResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Delete the Cash Receipt -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public SelectNonTrustFundsResponse getNonTrustFunds(SelectNonTrustFunds nonTrustFunds) throws JsonProcessingException {
        nonTrustFunds.setFunction("getNonTrustFunds");
        System.out.println("Get Non Trust Funds: "+ HelperUtility.toJSON(nonTrustFunds));

        String requested = objectMapper.writeValueAsString(nonTrustFunds);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), SelectNonTrustFundsResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not get the Non Trust Funds for Transfer -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

}
