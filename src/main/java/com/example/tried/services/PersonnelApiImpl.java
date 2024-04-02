package com.example.tried.services;

import com.example.tried.auth.member.specific.SpecificOfferingStatement;
import com.example.tried.auth.member.specific.SpecificOfferingStatementResponse;
import com.example.tried.auth.personnel.accounts.LocalChurchAccounts;
import com.example.tried.auth.personnel.accounts.LocalChurchAccountsSelectResponse;
import com.example.tried.auth.personnel.accounts.new_account.Authentication;
import com.example.tried.auth.personnel.accounts.new_account.CreateLocalChurchAccount;
import com.example.tried.auth.personnel.accounts.new_account.CreateLocalChurchAccountResponse;
import com.example.tried.auth.personnel.accounts.update_account.UpdateLocalChurchAccount;
import com.example.tried.auth.personnel.accounts.update_account.UpdateLocalChurchAccountResponse;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccounts;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccountsResponse;
import com.example.tried.auth.personnel.receipting.CashReceipting;
import com.example.tried.auth.personnel.receipting.CashReceiptingResponse;
import com.example.tried.auth.personnel.receipting.traced.member.TransactionTracingMemberPhone;
import com.example.tried.auth.personnel.receipting.traced.member.TransactionTracingMemberPhoneResponse;
import com.example.tried.auth.personnel.receipting.traced.receipt.TransactionTracingMemberReceipt;
import com.example.tried.auth.personnel.receipting.traced.receipt.TransactionTracingMemberReceiptResponse;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummaryResponse;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscript;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscriptResponse;
import com.example.tried.auth.reports.specific.LocalChurchSpecificAccountSummary;
import com.example.tried.auth.reports.specific.LocalChurchSpecificAccountSummaryResponse;
import com.example.tried.config.AuthConfiguration;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

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
    public CashReceiptingResponse getCashReceipting(CashReceipting receipting) {
        receipting.setFunction("cashTransactionConfirmation");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(receipting)));

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


    // Create Local Church Accounts
    @Override
    public CreateLocalChurchAccountResponse createLocalChurchAccount(CreateLocalChurchAccount localChurchAccount) {
        Authentication authentication = new Authentication();
        authentication.setPassword("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setUserName("7Zu2pBUFgsaTOuMOvfqNpg==");

        localChurchAccount.setAuthentication(authentication);
        localChurchAccount.setFunction("createLocalChurchAccount");

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
    public UpdateLocalChurchAccountResponse updateLocalChurchAccount(UpdateLocalChurchAccount localChurchAccount) {
        com.example.tried.auth.personnel.accounts.update_account.Authentication authentication = new
                com.example.tried.auth.personnel.accounts.update_account.Authentication();


        authentication.setPassword("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setUserName("7Zu2pBUFgsaTOuMOvfqNpg==");

        localChurchAccount.setAuthentication(authentication);
        localChurchAccount.setFunction("createLocalChurchAccount");

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
            return objectMapper.readValue(response.body().string(), UpdateLocalChurchAccountResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not complete Create Local Church Account -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), UpdateLocalChurchAccountResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public TrustFundTranscriptResponse getTrustFundTranscript(TrustFundTranscript transcript) {
        transcript.setFunction("getLocalChurchTrustFundTranscript");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(transcript)));

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
    public LocalChurchAccountsSelectResponse selectLocalChurchAccounts(LocalChurchAccounts selectAccounts) {
        com.example.tried.auth.personnel.accounts.Authentication authentication =
                new com.example.tried.auth.personnel.accounts.Authentication();
        authentication.setUserName("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setPassword("7Zu2pBUFgsaTOuMOvfqNpg==");

        selectAccounts.setFunction("createLocalChurchAccount");
        selectAccounts.setAuthentication(authentication);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(selectAccounts)));

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
    public ListLocalChurchPaymentAccountsResponse selectChurchPaymentAccounts(ListLocalChurchPaymentAccounts selectAccounts) {
        selectAccounts.setFunction("getLocalChurchPaymentAccounts");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(selectAccounts)));

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
    public SpecificOfferingStatementResponse getSpecificMemberOfferingStatement(SpecificOfferingStatement statement) {
        statement.setFunction("getMemberSpecificOfferingStatement");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(statement)));

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
    public TransactionTracingMemberPhoneResponse getTransactionTracingMemberByPhoneNumber(TransactionTracingMemberPhone memberPhone) {
        memberPhone.setFunction("getMemberPaymentTraceReport");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(memberPhone)));

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(memberPhone));
        Request request = new Request.Builder()
                .url(authConfiguration.getReconciliation_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TransactionTracingMemberPhoneResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not perform Transaction Tracing by Phone -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), TransactionTracingMemberPhoneResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public TransactionTracingMemberReceiptResponse getTransactionTracingMemberByReceiptNumber(TransactionTracingMemberReceipt receipt) {
        receipt.setFunction("getReceiptPaymentTraceReport");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(receipt)));

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(receipt));
        Request request = new Request.Builder()
                .url(authConfiguration.getReconciliation_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), TransactionTracingMemberReceiptResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not perform Transaction Tracing by Receipt -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), TransactionTracingMemberReceiptResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchSpecificAccountSummaryResponse getLocalChurchSpecificAccountSummary(LocalChurchSpecificAccountSummary specificAccountSummary) {
        specificAccountSummary.setFunction("getLocalChurchSpecificAccountSummary");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(specificAccountSummary)));

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
    public HashMap<String, Object> getLocalChurchAccounts(HashMap<String, Object> jsonObject) {

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, HelperUtility.toJSON(Objects.requireNonNull(jsonObject)));

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


}
