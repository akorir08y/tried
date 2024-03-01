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
    Map<String, Integer> otpMap = new HashMap<>();


    private final AuthConfiguration authConfiguration;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public AuthApiImpl(AuthConfiguration authConfiguration, OkHttpClient client, ObjectMapper objectMapper) {
        this.authConfiguration = authConfiguration;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public AuthMemberResponse getMemberCredentials(LoginCredentials credentials) {

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

        System.out.println("OfferingAuthentication Request: "+ Objects.requireNonNull(HelperUtility.toJSON(memberRequest)));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(memberRequest)));
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
    public SMSResponse VerifyPhoneNumber(String recipient) {
        // Generate Random OTP

        int rand = getOTPPin();
        otpMap.put(recipient, rand);
        // OTP Message
        String message = "<#>Your CFMS Authorization Code is " + rand + " n   nCR3mHWdawrw    n";


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

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(sms)));
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
    public AuthMemberResetResponse resetMemberPin(Payload payload) {
        AuthMemberReset memberReset = new AuthMemberReset();

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        System.out.println("The Session Number is: " + session_number);

        String session = String.valueOf(session_number);

        memberReset.setFunction("mobileAuthenticationUpdate");

        payload.setSessionNumber(session);
        memberReset.setPayload(payload);

        System.out.println("Reset PIN Request: "+ Objects.requireNonNull(HelperUtility.toJSON(memberReset)));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(memberReset)));
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
    public AuthMemberRegistrationResponse registerMember(MemberRegister register) {
        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        System.out.println("The Session Number is: " + session_number);

        register.setSessionNumber(session_number);

        MemberRegistration registration = new MemberRegistration();
        registration.setFunction("mobileRegistration");
        registration.setPayload(register);


        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(registration)));
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
    public AuthMemberRegistrationResponse updateRegisterMember(AuthMemberRegister register) {

        MemberRegistrationUpdate registration = new MemberRegistrationUpdate();
        registration.setFunction("mobileRegistrationUpdates");
        registration.setUpdatepayload(register);

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(registration)));
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
    public MemberPersonnelResponse loginMemberPersonnel(MemberPersonnel personnel) {
        // Function Name
        personnel.setFunction("login");
        personnel.setConnectionPurpose("Web Administration");
        System.out.println("Login Personnel Credentials: "+personnel.toString());

        System.out.println("Personnel JSON Request: "+Objects.requireNonNull(HelperUtility.toJSON(personnel)));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(personnel)));
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
    public PersonnelResetResponse resetPersonnelPassword(MemberPersonnelReset reset) {
        reset.setFunction("resetPassword");

        System.out.println("Member Personnel Reset JSON: "+
                Objects.requireNonNull(HelperUtility.toJSON(reset)));
        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(reset)));
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
    public String validateOTPPassword(OtpValidationRequest otpValidationRequest) {
        // System.out.println("Otp Validation Request: "+otpValidationRequest.toString());
        int rand = otpMap.get(otpValidationRequest.getUsername());
        System.out.println("Rand: "+rand);

        Set<String> keys = otpMap.keySet();
        int otp = Integer.parseInt(otpValidationRequest.getOtpNumber());
        System.out.println("Otp: "+otp);
        // System.out.println("Input from the Form: "+otpValidationRequest.getOtpNumber());
        // System.out.println("Keys :"+ keys);
        // otpMap.get()
        String username = null;
        for(String key : keys)
            username = key;
        if (otpValidationRequest.getUsername().equals(username)) {
            if(rand == otp) {
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
       System.out.println("The Authorization Code is: " + rand);
       return rand;
    }

    // Get the Member Details
    @Override
    public MemberProfileResponse getMemberDetails(MemberProfile profile) {
        profile.setFunction("mobileFetchProfile");

        System.out.println("Member Profile Request JSON: "+
                Objects.requireNonNull(HelperUtility.toJSON(profile)));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(profile)));
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
    public MemberOfferingResponse getMemberOffering(MemberOffering offering) {
        // Profile
        offering.setFunction("getMemberOfferingStatement");

        System.out.println("Member Offering Request JSON: "+
                Objects.requireNonNull(HelperUtility.toJSON(offering)));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(offering)));
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

        System.out.println("Member Church Details Request JSON: "+
                Objects.requireNonNull(HelperUtility.toJSON(requestChurchDetails)));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(requestChurchDetails)));
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
    public MemberTransferResponse getMemberTransfer(MemberTransfer transfer) {
        // Member Transfer Payload
        transfer.setFunction("memberTransfer");

        TransferAuthentication authentication = new TransferAuthentication();
        authentication.setUserName("Dn0guQtrJc9j0nbF7y8evw==");
        authentication.setPassword("7Zu2pBUFgsaTOuMOvfqNpg==");

        transfer.setTransferauthentication(authentication);

        System.out.println("Member Transfer Details: "+
                Objects.requireNonNull(HelperUtility.toJSON(transfer)));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(transfer)));
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
    public RequestMemberDetailsResponse getMemberFullDetails(RequestMemberDetails requestMember) {
        requestMember.setFunction("mobileRequestMemberDetails");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(requestMember)));
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
    public RequestChurchDetailsWithCodeResponse getChurchCodeDetails(RequestChurchDetailsWithCode requestChurchDetails) {
        requestChurchDetails.setFunction("mobileRequestChurchDetailsWithChurchCode");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(requestChurchDetails)));
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
    public MemberRegisterUpdateResponse getMemberRegistrationUpdate(MemberRegistrationUpdate registrationUpdate) {
        registrationUpdate.setFunction("mobileRegistrationUpdates");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(registrationUpdate)));
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
    public ListMembersResponse getChurchMembers(ListMembers members) {
        members.setFunction("getListOfChurchMembers");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(members)));
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
    public ListDeactivatedMembersResponse getDeactivatedMembers(ListDeactivatedMembers members) {
        members.setFunction("mobileRequestDeactivatedMemberDetails");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(members)));
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
    public LocalChurchPaymentAccountsResponse getPaymentAccounts(LocalChurchPaymentAccounts paymentAccount) {
        paymentAccount.setFunction("getLocalChurchPaymentAccounts");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(paymentAccount)));
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
    public LocalChurchTrustFundSummaryResponse getLocalChurchTrustFundSummary(LocalChurchTrustFundSummary trustFundSummary) {
        trustFundSummary.setFunction("getLocalChurchTrustFundsSummary");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(trustFundSummary)));
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
    public ChurchPaymentResponse getHomeChurchPayment(HomeChurchPayment homePayment) {
        homePayment.setFunction("");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(homePayment)));
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
    public ChurchPaymentResponse getHostChurchPayment(HostChurchPayment hostChurchPayment) {
        hostChurchPayment.setFunction("mobileInitiateHostChurchPayment");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJSON(hostChurchPayment)));
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
    public MobileReceiveFundsResponse receiveMemberFunds(MobileReceiveFundsGiving giving) throws JsonParseException {
        giving.setFunction("mobileReceiveFunds");

        System.out.println("Getting Trust Funds: " + giving.getPayload().getFundDistribution().getTrustFunds());
        System.out.println("Getting Trust Funds: " + giving.getPayload().getFundDistribution().getNonTrustFunds());
        System.out.println("Getting Trust Funds: " + giving.getPayload().getFundDistribution().getSpecialTrustFunds());
        System.out.println("Mobile Giving JSON: "+ HelperUtility.toJSON(giving));

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(giving)));

        System.out.println("Request Body: " + body.toString());
        Request request = new Request.Builder()
                .url(authConfiguration.getAuth_login_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        System.out.println("Request: " + request);
        System.out.println("Request: " + HelperUtility.toJSON(request));

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
    public MpesaSTKRequestResponse getMPESASTKResponse(MpesaSTKRequest stkRequest) {
        stkRequest.setChannel("M-PESA");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(stkRequest)));

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
    public LocalChurchTransactionTracingResponse getTransactionTracingSummary(LocalChurchTransactionTracing transactionTracing) {
        transactionTracing.setFunction("getLocalChurchPaymentTraceReport");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(transactionTracing)));

        System.out.println("Request Body: " + body.toString());
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
    public LocalChurchNonTrustSummaryResponse getLocalChurchNonTrustFund(LocalChurchNonTrustSummary nonTrustSummary) {
        nonTrustSummary.setFunction("getLocalChurchNonTrustFundsOfferingsReport");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(nonTrustSummary)));

        System.out.println("Request Body: " + body);
        Request request = new Request.Builder()
                .url(authConfiguration.getDashboard_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(), LocalChurchNonTrustSummaryResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Transaction Tracing Report -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(), LocalChurchNonTrustSummaryResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public LocalChurchOfferingSummaryResponse getLocalChurchOfferingReports(LocalChurchOfferingSummary churchOfferingSummary) {
        churchOfferingSummary.setFunction("getLocalChurchNonTrustFundsOfferingsReport");

        //Request Body
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJSON(churchOfferingSummary)));

        System.out.println("Request Body: " + body);
        Request request = new Request.Builder()
                .url(authConfiguration.getDashboard_url())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();


        try {
            Response response = client.newCall(request).execute();
            return objectMapper.readValue(response.body().string(),LocalChurchOfferingSummaryResponse.class);
        } catch (Exception e) {
            log.error(String.format("Could not get Transaction Tracing Report -> %s", e.getLocalizedMessage()));
            try {
                return objectMapper.readValue(e.getLocalizedMessage().toString(),LocalChurchOfferingSummaryResponse.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


}
