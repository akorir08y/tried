package com.example.tried.services;


import com.example.tried.auth.dashboard.ListMembers;
import com.example.tried.auth.dashboard.ListMembersResponse;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembers;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembersResponse;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccounts;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccountsResponse;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.dto.Payload;
import com.example.tried.auth.financial.MemberOffering;
import com.example.tried.auth.financial.MemberOfferingResponse;
import com.example.tried.auth.member.*;
import com.example.tried.auth.member.giving.*;
import com.example.tried.auth.member.specific.SpecificOfferingStatement;
import com.example.tried.auth.member.specific.SpecificOfferingStatementResponse;
import com.example.tried.auth.personnel.*;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummary;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummaryResponse;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummary;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummaryResponse;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracing;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracingResponse;
import com.example.tried.config.AuthConfiguration;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.tried.utils.Constants.JSON_MEDIA_TYPE;

@Service
@Slf4j
public class AuthApiImpl implements AuthApi{

    // Prepare a Hash Map
    HashMap<String, String> otpMap = new HashMap<String, String>();


    private final AuthConfiguration authConfiguration;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public AuthApiImpl(AuthConfiguration authConfiguration, OkHttpClient client, ObjectMapper objectMapper) {
        this.authConfiguration = authConfiguration;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    // Login As a Member
    @Override
    public AuthMemberResponse getMemberCredentials(LoginCredentials credentials) throws JsonProcessingException {

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        System.out.println("The Session Number is: " + session_number);

        String session = String.valueOf(session_number);

        // Auth Member Request Function
        AuthMemberRequest memberRequest = new AuthMemberRequest();
        memberRequest.setFunction("mobileAuthenticateMember");

        // ResetPayload Information
        AuthPayload payload = new AuthPayload();
        payload.setVersion("2.0");
        payload.setNumberOfTries("1");
        payload.setSessionNumber(session);
        payload.setPin(credentials.getPin());
        payload.setAccessNumber(credentials.getAccessNumber());

        memberRequest.setPayload(payload);

        String requested = objectMapper.writeValueAsString(memberRequest);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),AuthMemberResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Login into CFMS App -> %s", e.getLocalizedMessage()));
            return null;
        }
    }




    @Override
    public SMSResponse VerifyPhoneNumber(String recipient) throws JsonProcessingException {
        // Generate Random OTP

        int rand = getOTPPin();

        otpMap.put("recipient", recipient);
        otpMap.put("otp", String.valueOf(rand));
        // OTP Message
        String message = "<#>Your CFMS Authorization Code is " + rand + "   \n   nCR3mHWdawrw    \n";

        if(recipient.startsWith("+254")){
            recipient = recipient.substring(1,recipient.length());
        }

        // OfferingAuthentication Details
        Authentication authentication = new Authentication();
        authentication.setUserName("");
        authentication.setPassword("");

        //SMS Request
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setMessageGroup("Individual");


        // Receives a List of Phone Number
        List<String> Number = new ArrayList<>();
        System.out.println("Recipient Phone Number: "+ recipient);
        Number.add(recipient);
        System.out.println("Number: "+Number);
        smsRequest.setPhoneNumber(Number);

        // Additional Information
        smsRequest.setClientAccount("");
        smsRequest.setClientName("");
        smsRequest.setClintLevel("");
        smsRequest.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).format(new Date()));
        smsRequest.setMessages(message);

        // The Function Name
        String function = "lakeattsSMSGateway";

        // Combine All Collected Data
        SMS sms = new SMS();
        sms.setSmsRequest(smsRequest);
        sms.setAuthentication(authentication);
        sms.setFunction(function);

        System.out.println("SMS Request: "+ Objects.requireNonNull(HelperUtility.toJSON(sms)));
        String requested = objectMapper.writeValueAsString(sms);

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
            log.error(String.format("Could not Send OTP Message -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public AuthMemberResetResponse resetMemberPin(Payload payload) throws JsonProcessingException {
        AuthMemberReset memberReset = new AuthMemberReset();

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        System.out.println("The Session Number is: " + session_number);

        String session = String.valueOf(session_number);

        memberReset.setFunction("mobileAuthenticationUpdate");

        payload.setSessionNumber(session);
        memberReset.setPayload(payload);

        String requested = objectMapper.writeValueAsString(memberReset);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),AuthMemberResetResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not reset the Pin -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public AuthMemberRegistrationResponse registerMember(MemberRegister register) throws JsonProcessingException {
        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        System.out.println("The Session Number is: " + session_number);

        register.setSessionNumber(session_number);

        MemberRegistration registration = new MemberRegistration();
        registration.setFunction("mobileRegistration");
        registration.setPayload(register);

        System.out.println("Registration Payload: "+ HelperUtility.toJSON(registration));

        String requested = objectMapper.writeValueAsString(registration);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),AuthMemberRegistrationResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not register the Member-> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public AuthMemberRegistrationResponse updateRegisterMember(AuthMemberRegister register) throws JsonProcessingException {

        MemberRegistrationUpdate registration = new MemberRegistrationUpdate();
        registration.setFunction("mobileRegistrationUpdates");
        registration.setUpdatepayload(register);

        System.out.println("Update Profile Info: "+ registration);

        String requested = objectMapper.writeValueAsString(registration);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),AuthMemberRegistrationResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not update Registered Member -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public MemberPersonnelResponse loginMemberPersonnel(MemberPersonnel personnel) throws JsonProcessingException {
        // Function Name
        personnel.setFunction("login");
        personnel.setConnectionPurpose("Web Administration");
        System.out.println("Login Personnel Credentials: "+personnel.toString());

        String requested = objectMapper.writeValueAsString(personnel);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),MemberPersonnelResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not Login the Personnel -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public PersonnelResetResponse resetPersonnelPassword(MemberPersonnelReset reset) throws JsonProcessingException {
        reset.setFunction("resetPassword");

        String requested = objectMapper.writeValueAsString(reset);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),PersonnelResetResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not reset the Personnel Password -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public String validateOTPPassword(OtpValidationRequest otpValidationRequest)  {

        String rand = String.valueOf(otpMap.get("otp"));
        System.out.println("Rand: "+rand);

        String otp = otpValidationRequest.getOtpNumber();
        System.out.println("Otp: "+otp);
        String username = otpMap.get("recipient");
        String obtained = otpValidationRequest.getUsername();

        System.out.println("Phone Number: "+ username);
        System.out.println("Obtained Phone Number: "+ obtained);

        if (obtained.contains(username)) {
            if(rand.contains(otp)) {
                return "OTP is valid!";
            }else{
                return "OTP is invalid!";
            }
        } else {
            return "OTP is invalid!";
        }
    }

    @Override
    public int getOTPPin() {
        // Generate Random OTP
       final int rand = (int) ((Math.random() * 9000) + 1000);
       return rand;
    }

    // Get the Member Details
    @Override
    public MemberProfileResponse getMemberDetails(MemberProfile profile) throws JsonProcessingException {
        profile.setFunction("mobileFetchProfile");

        String requested = objectMapper.writeValueAsString(profile);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getCfms_data())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),MemberProfileResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not fetch the Member Details -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public MemberOfferingResponse getMemberOffering(MemberOffering offering) throws JsonProcessingException {
        // Profile
        offering.setFunction("getMemberOfferingStatement");

        System.out.println("Member Offering Request JSON: "+
                Objects.requireNonNull(HelperUtility.toJSON(offering)));

        String requested = objectMapper.writeValueAsString(offering);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getFinancial_data())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),MemberOfferingResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not fetch the Member Offering Details -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @SneakyThrows
    @Override
    public RequestChurchDetailsResponse getMemberChurchDetails(RequestChurchDetails requestChurchDetails) {
        // Profile
        requestChurchDetails.setFunction("mobileRequestChurchDetails");
        requestChurchDetails.setAccessPoint("Web App");
        requestChurchDetails.setConnectionPurpose("Registration");

        String requested = objectMapper.writeValueAsString(requestChurchDetails);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),RequestChurchDetailsResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not retrieve the Member Church Details -> %s", e.getLocalizedMessage()));
            return objectMapper.readValue(e.getLocalizedMessage().toString(), RequestChurchDetailsResponse.class);
        }
    }

    @Override
    public MemberTransferResponse getMemberTransfer(MemberTransfer transfer) throws JsonProcessingException {
        // Member Transfer RPayload
        transfer.setFunction("memberTransfer");

        TransferAuthentication authentication = new TransferAuthentication();
        authentication.setUserName("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setPassword("7Zu2pBUFgsaTOuMOvfqNpg==");

        transfer.setTransferauthentication(authentication);

        String requested = objectMapper.writeValueAsString(transfer);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),MemberTransferResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not facilitate Member Transfer Details -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), MemberTransferResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public RequestMemberDetailsResponse getFullMemberDetails(RequestMemberDetails requestMember) throws JsonProcessingException {
        requestMember.setFunction("mobileRequestMemberDetails");

        String requested = objectMapper.writeValueAsString(requestMember);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),RequestMemberDetailsResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not retrieve Member Details -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), RequestMemberDetailsResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public RequestChurchDetailsWithCodeResponse getChurchCodeDetails(RequestChurchDetailsWithCode requestChurchDetails) throws JsonProcessingException {
        requestChurchDetails.setFunction("mobileRequestChurchDetailsWithChurchCode");

        System.out.println("RequestChurchDetailsWithCode:"+ requestChurchDetails);

        String requested = objectMapper.writeValueAsString(requestChurchDetails);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),RequestChurchDetailsWithCodeResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not retrieve the Member Church Details with Code -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), RequestChurchDetailsWithCodeResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public MemberRegisterUpdateResponse getMemberRegistrationUpdate(MemberRegistrationUpdate registrationUpdate) throws JsonProcessingException {
        registrationUpdate.setFunction("mobileRegistrationUpdates");

        System.out.println("Registration Update: "+ HelperUtility.toJSON(registrationUpdate));

        String register = objectMapper.writeValueAsString(registrationUpdate);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, register);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),MemberRegisterUpdateResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not update the Member Details -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), MemberRegisterUpdateResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public ListMembersResponse getChurchMembers(ListMembers members) throws JsonProcessingException {
        members.setFunction("getListOfChurchMembers");

        String register = objectMapper.writeValueAsString(members);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, register);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),ListMembersResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Active Members -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), ListMembersResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public ListDeactivatedMembersResponse getDeactivatedMembers(ListDeactivatedMembers members) throws JsonProcessingException {
        members.setFunction("mobileRequestDeactivatedMemberDetails");

        String register = objectMapper.writeValueAsString(members);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, register);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),ListDeactivatedMembersResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Deactivated Members -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), ListDeactivatedMembersResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchPaymentAccountsResponse getPaymentAccounts(LocalChurchPaymentAccounts paymentAccount) throws JsonProcessingException {
        paymentAccount.setFunction("getLocalChurchPaymentAccounts");

        String requested = objectMapper.writeValueAsString(paymentAccount);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),LocalChurchPaymentAccountsResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Deactivated Members -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), LocalChurchPaymentAccountsResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchTrustFundSummaryResponse getLocalChurchTrustFundSummary(LocalChurchTrustFundSummary trustFundSummary) throws JsonProcessingException {
        trustFundSummary.setFunction("getLocalChurchTrustFundsSummary");

        String requested = objectMapper.writeValueAsString(trustFundSummary);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getTrust_funds_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),LocalChurchTrustFundSummaryResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get the Local Church Trust Fund Summary -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), LocalChurchTrustFundSummaryResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public ChurchPaymentResponse getHomeChurchPayment(HomeChurchPayment homePayment) throws JsonProcessingException {
        homePayment.setFunction("");

        String requested = objectMapper.writeValueAsString(homePayment);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),ChurchPaymentResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not complete Home Church Payment -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(),ChurchPaymentResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public ChurchPaymentResponse getHostChurchPayment(HostChurchPayment hostChurchPayment) throws JsonProcessingException {
        hostChurchPayment.setFunction("mobileInitiateHostChurchPayment");

        String requested = objectMapper.writeValueAsString(hostChurchPayment);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),ChurchPaymentResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not complete Host Church Payment -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), ChurchPaymentResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public MobileReceiveFundsResponse receiveMemberFunds(MobileReceiveFundsGiving giving) throws JsonProcessingException {
        giving.setFunction("mobileReceiveFunds");
        String requested = objectMapper.writeValueAsString(giving);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Member Giving: " + HelperUtility.toJSON(giving));

        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), MobileReceiveFundsResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not Send Mobile Receive Funds -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage(), MobileReceiveFundsResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public MpesaSTKRequestResponse getMPESASTKResponse(MpesaSTKRequest stkRequest) throws JsonProcessingException {
        stkRequest.setChannel("M-PESA");

        String requested = objectMapper.writeValueAsString(stkRequest);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body: " + body.toString());
        Request request = new Request.Builder()
                .url(authConfiguration.getMpesa_stk_request_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), MpesaSTKRequestResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not Send Mobile Receive Funds -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), MpesaSTKRequestResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchTransactionTracingResponse getTransactionTracingSummary(LocalChurchTransactionTracing transactionTracing) throws JsonProcessingException {
        transactionTracing.setFunction("getLocalChurchPaymentTraceReport");
        String requested = objectMapper.writeValueAsString(transactionTracing);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body: " + HelperUtility.toJSON(transactionTracing));
        Request request = new Request.Builder()
                .url(authConfiguration.getReconciliation_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), LocalChurchTransactionTracingResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Transaction Tracing Report -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), LocalChurchTransactionTracingResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchNonTrustSummaryResponse getLocalChurchNonTrustFund(LocalChurchNonTrustSummary nonTrustSummary) throws JsonProcessingException {
        nonTrustSummary.setFunction("getLocalChurchNonTrustFundsOfferingsReport");
        String requested = objectMapper.writeValueAsString(nonTrustSummary);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Request Body JSON: " + HelperUtility.toJSON(nonTrustSummary));
        Request request = new Request.Builder()
                .url(authConfiguration.getDashboard_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), LocalChurchNonTrustSummaryResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Local Non Trust Fund Report -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), LocalChurchNonTrustSummaryResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchOfferingSummaryResponse getLocalChurchOfferingReports(LocalChurchOfferingSummary churchOfferingSummary) throws JsonProcessingException {
        churchOfferingSummary.setFunction("getLocalChurchOfferingsReport");
        String requested = objectMapper.writeValueAsString(churchOfferingSummary);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);

        System.out.println("Offering JSON: "+ HelperUtility.toJSON(churchOfferingSummary));

        Request request = new Request.Builder()
                .url(authConfiguration.getDashboard_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),LocalChurchOfferingSummaryResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Local Church Offering Report -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public SpecificOfferingStatementResponse getSpecificOfferingStatement(SpecificOfferingStatement statement) throws JsonProcessingException {
        statement.setFunction("getMemberSpecificOfferingStatement");
        String requested = objectMapper.writeValueAsString(statement);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requested);
        Request request = new Request.Builder()
                .url(authConfiguration.getFinancial_data())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),SpecificOfferingStatementResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Specific Offering Statement -> %s", e.getLocalizedMessage()));
            return null;
        }
    }


}
