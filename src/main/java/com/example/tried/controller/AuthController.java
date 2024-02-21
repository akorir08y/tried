package com.example.tried.controller;


import com.example.tried.auth.dashboard.ListMembers;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dashboard.trust_funds.TransactionsItem;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.financial.MemberOffering;
import com.example.tried.auth.financial.MemberOfferingResponse;
import com.example.tried.auth.financial.OfferingAuthentication;
import com.example.tried.auth.financial.OfferingPayload;
import com.example.tried.auth.member.Churchpayload;
import com.example.tried.auth.member.RequestChurchDetailsWithCode;
import com.example.tried.auth.member.RequestChurchDetailsWithCodeResponse;
import com.example.tried.auth.personnel.*;
import com.example.tried.dto.account.OfferStatement;
import com.example.tried.dto.c2b.RegisterUrlResponse;
import com.example.tried.services.AuthApi;
import com.example.tried.services.OfferingStatementService;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static com.example.tried.utils.Constants.BEARER_AUTH_STRING;
import static com.example.tried.utils.Constants.JSON_MEDIA_TYPE;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthApi authApi;

    @Autowired
    OfferingStatementService statementService;

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public AuthController(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    // Login to CFMS App
    // http://localhost:8080/mobile-money/token
    @PostMapping(path="/login-json",produces = "application/json")
    public ResponseEntity<AuthMemberResponse> LoginExistingMemberJson(@RequestBody LoginCredentials credentials){
        System.out.println("Login Credentials: " + credentials);
        return ResponseEntity.ok(authApi.getMemberCredentials(credentials));
    }

    // Member Login for Web
    @PostMapping(path="/login",produces = "application/json")
    public ResponseEntity<AuthMemberResponse> LoginExistingMember(@RequestParam("username") String username,
                                                                  @RequestParam("password") String password){
        LoginCredentials credentials = new LoginCredentials();
        credentials.setPin(password);
        credentials.setAccessNumber(username);
        return ResponseEntity.ok(authApi.getMemberCredentials(credentials));
    }

    // Member Registration
    @PostMapping(path="/register", produces = "application/json")
    public ResponseEntity<AuthMemberRegistrationResponse> RegisterNewMember(@RequestBody AuthMemberRegister registration){
        return ResponseEntity.ok(authApi.registerMember(registration));
    }


    // Member Registration Update
    @PostMapping(path="/register-update", produces = "application/json")
    public ResponseEntity<AuthMemberRegistrationResponse>UpdateRegisteredMember(@RequestBody AuthMemberRegister registration){
        return ResponseEntity.ok(authApi.registerMember(registration));
    }

    // Reset the Member Pin
    @PostMapping(path="/reset",produces = "application/json")
    public ResponseEntity<AuthMemberResetResponse> resetPin(@RequestParam("previousAccessNumber") String previousAccessNumber,
                                                            @RequestParam("currentAccessNumber") String currentAccessNumber,
                                                            @RequestParam("pin") String pin){
        Payload payload = new Payload();
        payload.setPreviousAccessNumber(previousAccessNumber);
        payload.setCurrentAccessNumber(currentAccessNumber);
        payload.setPin(pin);
        return ResponseEntity.ok(authApi.resetMemberPin(payload));
    }

    // Personnel Login
    @PostMapping(path="/personnel/login", produces = "application/json")
    public ResponseEntity<MemberPersonnelResponse> loginMemberPersonnel(@RequestParam("user") String user,
                                                                        @RequestParam("password") String password){
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(user);
        personnel.setPassword(password);
        return ResponseEntity.ok(authApi.loginMemberPersonnel(personnel));
    }

    // Personnel Password Reset
    @PostMapping(path="/personnel/reset", produces = "application/json")
    public ResponseEntity<PersonnelResetResponse> resetPersonnelPassword(@RequestParam("username") String username,
                                                                         @RequestParam("password") String password){
        MemberPersonnelReset personnelReset = new MemberPersonnelReset();

        ResetPayload resetPayload = new ResetPayload();
        resetPayload.setUserName(username);
        resetPayload.setNewPassword(password);
        personnelReset.setPayload(resetPayload);
        return ResponseEntity.ok(authApi.resetPersonnelPassword(personnelReset));
    }

    @PostMapping(path="/otp-pin")
    public String validateOTP(@RequestParam("phone_number") String phoneNumber, @RequestParam("otp") String otp){
        OtpValidationRequest otpValidationRequest = new OtpValidationRequest();
        otpValidationRequest.setUsername(phoneNumber);
        otpValidationRequest.setOtpNumber(otp);
        return authApi.validateOTPPassword(otpValidationRequest);
    }

    // Check if the Number Exists in the Database
    @PostMapping(path="/check-number")
    public String checkPhoneNumber(@RequestParam("phone_number")String phoneNumber){
        String response = "";

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);

        if(!response1.getPayload().equals(null)){
            response = "Your Phone Number is already Registered to a Church Code: "+ response1.getPayload().getChurchCode();
        }

        return response;
    }

    // Send the OTP
    @PostMapping(path="/otp",produces = "application/json")
    public SMSResponse sendOTP(@RequestParam("recipient") String recipient){
        System.out.println("Recipient: " + recipient);
        return authApi.VerifyPhoneNumber(recipient);
    }


    @PostMapping(path="/profile",produces = "application/json")
    public MemberProfileResponse getMemberDetails(@RequestParam("phone_number") String phone_number){
        System.out.println("Get the Phone Number: " + phone_number);
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(payload);
        return authApi.getMemberDetails(profile);
    }

    @PostMapping(path="/statement",produces = "application/json")
    public MemberOfferingResponse getMemberOfferingStatement(@RequestParam("phone_number") String phone_number,
                                                             @RequestParam("start_date") String start_date,
                                                             @RequestParam("end_date") String end_date,
                                                             @RequestParam("pin") String pin){
        System.out.println("Get the Phone Number: " + phone_number);

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        System.out.println("The Session Number is: " + session_number);

        // String session = String.valueOf(session_number);

        // Get the Member Authentication Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(payload);
        MemberProfileResponse profile2 = authApi.getMemberDetails(profile);

        // Authentication Information
        OfferingAuthentication authentication = new OfferingAuthentication();
        authentication.setPhoneNumber(phone_number);
        authentication.setInstitutionName(profile2.getPayload().getChurchName());
        authentication.setInstitutionLevel("LOCAL CHURCH");
        authentication.setPersonnelName(profile2.getPayload().getMemberName());
        authentication.setSessionNumber(session_number);
        authentication.setInstitutionNumber(profile2.getPayload().getChurchCode());
        authentication.setPin(pin);
        authentication.setUser("");
        authentication.setPassword("");

        // Offering Payload
        OfferingPayload payload2 = new OfferingPayload();
        payload2.setMemberNumber(profile2.getPayload().getMembershipNumber());
        payload2.setMemberName(profile2.getPayload().getMemberName());
        payload2.setNumberOfTries(1);
        payload2.setStartDate(start_date);
        payload2.setEndDate(end_date);

        // Get the Member Offering Payload
        MemberOffering offering = new MemberOffering();
        offering.setAuthentication(authentication);
        offering.setPayload(payload2);

        return authApi.getMemberOffering(offering);
    }


    @PostMapping(path="/member-transfer")
    public MemberTransferResponse startMemberTransfer(@RequestParam("phone_number")String phoneNumber,
                                                      @RequestParam("church_code")String churchCode){

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);

        // Generate the Session Number
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Transfer Payload
        Transferpayload transferpayload = new Transferpayload();
        transferpayload.setCurrentChurchCode(response1.getPayload().getChurchCode());
        transferpayload.setMemberNumber(response1.getPayload().getMembershipNumber());
        transferpayload.setSessionNumber(rand);
        transferpayload.setNewChurchCode(churchCode);


        // Member Transfer Payload
        MemberTransfer memberTransfer = new MemberTransfer();
        memberTransfer.setTransferpayload(transferpayload);

        return authApi.getMemberTransfer(memberTransfer);
    }


    // Transfer Church Code
    @PostMapping(path="/check-status")
    public String TransferChurch(@RequestParam("phone_number")String PhoneNumber,
                                      @RequestParam("church_code")String ChurchCode){
        // Get the Member Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + PhoneNumber);
        profile.setProfilepayload(payload);

        // Get the Member Details
        MemberProfileResponse response1 = authApi.getMemberDetails(profile);
        String church_code = response1.getPayload().getChurchCode();

        RequestChurchDetailsWithCode requestDetails = new RequestChurchDetailsWithCode();
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Church Payload
        Churchpayload churchpayload = new Churchpayload();
        churchpayload.setAccessNumber(PhoneNumber);
        churchpayload.setMobileServiceProvider("Safaricom");
        churchpayload.setChurchCode(ChurchCode);
        churchpayload.setSessionNumber(rand);
        requestDetails.setChurchpayload(churchpayload);
        RequestChurchDetailsWithCodeResponse request = authApi.getChurchCodeDetails(requestDetails);


        if(church_code.equals(ChurchCode)){
            return "You are already Registered to this Church Code";
        }else if(request.getChurchName() == null){
            return "Unable to Locate this Church";
        }else{
            return "Yes";
        }
    }

    // Get the Profile Items to Save Registration MemberRegisterUpdateResponse
    @PostMapping(path="/saveProfile")
    public MemberRegisterUpdateResponse setProfileDetails(@RequestParam("fullname") String fullname, @RequestParam("email") String email,
                                    @RequestParam("churchCode")String churchCode ,@RequestParam("phone") String phone,
                                    @RequestParam("phone_number_privacy")String phone_number_privacy, @RequestParam("language")String language,
                                    @RequestParam(value = "phoneOwner", required = false)Boolean phoneOwner, @RequestParam(value = "churchMember", required = false)String churchMember,
                                    @RequestParam("receipt_to") String receipt_to, @RequestParam(value = "otherPhoneNumber",required = false) String otherPhoneNumber,
                                    @RequestParam(value = "residence",required = false)String residence){

        // Register Profile
        MemberRegistrationUpdate authMemberRegister = new MemberRegistrationUpdate();

        // Generate Session Number
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Get Profile Information
        MemberProfile profiler = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone);
        profiler.setProfilepayload(payload);

        // Profile Info
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);


        // Update Payload
        Updatepayload updatepayload = new Updatepayload();
        updatepayload.setFullNames(fullname);
        updatepayload.setEmail(email);
        updatepayload.setMobileNumber(phone);
        updatepayload.setChurchCode(churchCode);
        updatepayload.setPreferredLanguage(language);
        updatepayload.setPhoneNumberPrivacy(phone_number_privacy);
        updatepayload.setResidence(residence);
        updatepayload.setPhoneOwner(phoneOwner);
        if (churchMember == null) {
            churchMember = "false";
            updatepayload.setIsMember(churchMember);
        }else{
            updatepayload.setIsMember(churchMember);
        }
        updatepayload.setGivingReceiptedTo(receipt_to);
        updatepayload.setMembershipNumber(profile.getPayload().getMembershipNumber());
        updatepayload.setOtherPhoneNumber(otherPhoneNumber);
        updatepayload.setSessionNumber(rand);

        authMemberRegister.setUpdatepayload(updatepayload);

        System.out.println("Update Payload: " + updatepayload.toString());
        System.out.println("Auth Member Registration: "+ HelperUtility.toJSON(authMemberRegister));
        MemberRegisterUpdateResponse responsed = authApi.getMemberRegistrationUpdate(authMemberRegister);
        return responsed;
    }

    @PostMapping("/off-statement")
    public String generateOfferingStatement(@RequestParam("phone_number") String phone_number,
                                            @RequestParam("start_date") String start_date,
                                            @RequestParam("end_date") String end_date,
                                            @RequestParam("pin") String pin, HttpServletResponse response) throws IOException {

        // Offering Statement Information
        OfferStatement statement = new OfferStatement();
        statement.setStart_date(start_date);
        statement.setEnd_date(end_date);
        statement.setPin(pin);
        statement.setPhone_number(phone_number);

        System.out.println("Statement to Download: "+ statement.toString());

        // Member Profile Information
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        // Member Payload
        MemberProfile profiler = new MemberProfile();
        profiler.setProfilepayload(profilepayload);


        // Get Membership Number
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);
        String membershipNumber = profile.getPayload().getMembershipNumber();

        System.out.println("Generated Profile: " + profile);
        System.out.println("Member Number: " + membershipNumber);

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users" + membershipNumber + ".pdf";
        response.setHeader(headerKey, headerValue);

        statementService.createOfferingStatement(statement,response);
        return "Generate Offering Statement";
    }

    @GetMapping("/time")
    public String getTime() {
        Map<String, String> otpMap = new HashMap<>();
        otpMap.put("time","time2");
        Set<String> keys = otpMap.keySet();
        String username = "";
        for(String key : keys)
            username = key;

        System.out.println("key: " + username);
        System.out.println("keys: " + otpMap.get(username));
        return "Now Returned";
    }


    @GetMapping("/timed")
    public String getPreviousMonth() {
        LocalDate localDate = LocalDate.now();

        System.out.println("Get the Month Value: " + localDate.getMonthValue());
        System.out.println("Get the Previous Month Value: "+String.valueOf(localDate.getMonthValue() - 1));
        return String.valueOf(localDate.getMonthValue() - 1);
    }


    @GetMapping("/base")
    public String getBase32String(){
        Base32 base32 = new Base32();
        String output = base32.encodeAsString("test".getBytes());
        String input = Arrays.toString(base32.decode(output));
        System.out.println("Output: "   + output);

        byte[] decodedBytes = base32.decode(output);
        String decodedString = new String(decodedBytes);
        System.out.println("Input: " + decodedString);
        return "Base32 Successfully";
    }

    @PostMapping(path="/check-church")
    public RequestChurchDetailsWithCodeResponse checkChurchPresence(@RequestParam("phone_number")String PhoneNumber,
                                                                    @RequestParam("church_code")String ChurchCode){
        RequestChurchDetailsWithCode requestDetails = new RequestChurchDetailsWithCode();

        // Generate the Session Number
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Church Payload
        Churchpayload churchpayload = new Churchpayload();
        churchpayload.setAccessNumber(PhoneNumber);
        churchpayload.setMobileServiceProvider("Safaricom");
        churchpayload.setChurchCode(ChurchCode);
        churchpayload.setSessionNumber(rand);

        requestDetails.setChurchpayload(churchpayload);

        RequestChurchDetailsWithCodeResponse request = authApi.getChurchCodeDetails(requestDetails);

        //List<DepartmentalAccountsItem> list = request.getDepartmentalAccounts().split(",");
        System.out.println("Request Code With Response: "+request.getChurchName());

        return request;
    }


    // Additional Request to Save the PDF
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportPdf(@RequestParam("phone_number") String phone_number,
                                                         @RequestParam("start_date") String start_date,
                                                         @RequestParam("end_date") String end_date,
                                                         @RequestParam("pin") String pin,HttpServletResponse response) throws IOException {

        // Offering Statement Information
        OfferStatement statement = new OfferStatement();
        statement.setStart_date(start_date);
        statement.setEnd_date(end_date);
        statement.setPin(pin);
        statement.setPhone_number(phone_number);

        // Member Profile Information
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        // Member Payload
        MemberProfile profiler = new MemberProfile();
        profiler.setProfilepayload(profilepayload);

        // Get Membership Number
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);
        String membershipNumber = profile.getPayload().getMembershipNumber();

        // HTTP Headers
        HttpHeaders headers = new HttpHeaders();


        ByteArrayInputStream bis = statementService.generatePdfStream(statement);
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


    @GetMapping("/church-trust-funds")
    public List<TransactionsItem> getUSSDandCashSummary(){
        String username = "mwakesho";
        String password = "0389";

        // Session Numbers
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);

        // Get the Personnel Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);

        // Personnel Details
        String church_code = response.getPayload().getOrganisationNumber();
        String church_name = response.getPayload().getOrganisationName();
        String church_level = response.getPayload().getOrganisationLevel();
        String personnel_name = response.getPayload().getPersonnelName();
        String member_no = response.getPayload().getPersonnelCfmsNumber();

        // Get the Previous Month Trust Fund Summary
        LocalChurchTrustFundSummary trustFundSummary = new LocalChurchTrustFundSummary();
        com.example.tried.auth.dashboard.trust_funds.Payload payload = new com.example.tried.auth.dashboard.trust_funds.Payload();

        // Get the Current Year
        LocalDate localdate = LocalDate.now();

        // Trust Fund Summary Payload
        payload.setYear(localdate.getYear());
        payload.setMonth(localdate.getMonthValue() - 1);
        payload.setChurchName(church_name);
        payload.setLocalChurchNumber(church_code);
        trustFundSummary.setPayload(payload);

        //
        com.example.tried.auth.dashboard.trust_funds.Authentication authentication1 = new com.example.tried.auth.dashboard.
                trust_funds.Authentication();

        authentication1.setInstututionLevel(church_level);
        authentication1.setUser(username);
        authentication1.setPersonnelName(personnel_name);
        authentication1.setPassword(password);
        authentication1.setSessionNumber(rand);
        authentication1.setInstututionName(church_name);
        authentication1.setInstututionNumber(church_code);
        trustFundSummary.setAuthentication(authentication1);

        System.out.println("Local Church Trust Fund Summary: " + HelperUtility.toJSON(trustFundSummary));

        LocalChurchTrustFundSummaryResponse localChurchTrustFund = authApi.getLocalChurchTrustFundSummary(trustFundSummary);

        System.out.println("Local Church Trust Fund Summary Response: " + HelperUtility.toJSON(localChurchTrustFund));

        List<TransactionsItem> transactions = localChurchTrustFund.getTrupayload().getTransactions();
        List<TransactionsItem> totals = new ArrayList<TransactionsItem>();
        for(TransactionsItem transaction : transactions){
            if (transaction.getReceiptNumber() == null){
                totals.add(transaction);
            }
        }

        Integer total_transactions = transactions.size();
        Integer total_zone = totals.size();
        Integer total_month_transactions = total_transactions - total_zone;
        System.out.println("Transaction Size: "+ total_month_transactions);
        return totals;
        // System.out.println("Transaction Results:")
    }
}
