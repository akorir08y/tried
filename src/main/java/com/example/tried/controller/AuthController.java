package com.example.tried.controller;


import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dashboard.trust_funds.TransactionsItem;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.dto.Payload;
import com.example.tried.auth.financial.*;
import com.example.tried.auth.member.*;
import com.example.tried.auth.member.giving.*;
import com.example.tried.auth.member.giving.FundDistribution;
import com.example.tried.auth.member.giving.Member;
import com.example.tried.auth.member.specific.SpecificOfferingStatement;
import com.example.tried.auth.member.specific.SpecificOfferingStatementResponse;
import com.example.tried.auth.personnel.*;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummary;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummaryResponse;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummary;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummaryResponse;
import com.example.tried.auth.personnel.reports.offering.LocalChurchPayload;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracing;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracingResponse;
import com.example.tried.dto.account.OfferStatement;
import com.example.tried.services.AuthApi;
import com.example.tried.services.OfferingSpecificStatementService;
import com.example.tried.services.OfferingStatementService;
import com.example.tried.services.reports.excel.LocalNonTrustFundReportExcel;
import com.example.tried.services.reports.excel.TestExcelForm;
import com.example.tried.services.reports.pdf.LocalNonTrustFundReport;
import com.example.tried.services.reports.pdf.TransactionTracingSummary;
import com.example.tried.services.reports.pdf.TrustFundSummary;
import com.example.tried.services.reports.excel.TransactionTracingExcel;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.codec.binary.Base32;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthApi authApi;

    @Autowired
    OfferingStatementService statementService;

    @Autowired
    OfferingSpecificStatementService specificStatementService;

    @Autowired
    TrustFundSummary trustFundSummary;

    @Autowired
    LocalNonTrustFundReport testPDFSummary;

    @Autowired
    TransactionTracingSummary transactionTracingSummary;


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
    public ResponseEntity<AuthMemberRegistrationResponse> RegisterNewMember(@RequestBody MemberRegister registration){
        return ResponseEntity.ok(authApi.registerMember(registration));
    }


    // Member Registration Update
    @PostMapping(path="/register-update", produces = "application/json")
    public ResponseEntity<AuthMemberRegistrationResponse>UpdateRegisteredMember(@RequestBody AuthMemberRegister registration){
        return ResponseEntity.ok(authApi.updateRegisterMember(registration));
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
        // System.out.println("The Session Number is: " + session_number);
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


    @PostMapping(path="/statement-specific",produces = "application/json")
    public SpecificOfferingStatementResponse getMemberOfferingStatementSpecific(@RequestParam("phone_number") String phone_number,
                                                                                @RequestParam("start_date") String start_date,
                                                                                @RequestParam("end_date") String end_date,
                                                                                @RequestParam("pin") String pin,
                                                                                @RequestParam("account_name")String account_name,
                                                                                @RequestParam("account_number")String account_number){
        System.out.println("Get the Phone Number: " + phone_number);

        // Generate Session Number
        final long session_number = (long) ((Math.random() * 900000000) + 100000000);
        // System.out.println("The Session Number is: " + session_number);
        // String session = String.valueOf(session_number);

        // Get the Member Authentication Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(payload);
        MemberProfileResponse profile2 = authApi.getMemberDetails(profile);

        // Authentication Information
        com.example.tried.auth.member.specific.Authentication authentication = new
                com.example.tried.auth.member.specific.Authentication();
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
        com.example.tried.auth.member.specific.Payload payload2 = new com.example.tried.auth.member.specific.Payload();
        payload2.setMemberNumber(profile2.getPayload().getMembershipNumber());
        payload2.setMemberName(profile2.getPayload().getMemberName());
        payload2.setNumberOfTries(1);
        payload2.setStartDate(start_date);
        payload2.setEndDate(end_date);
        payload2.setAccountName(account_name);
        payload2.setAccountNumber(account_number);

        // Get the Member Offering Payload
        SpecificOfferingStatement statement = new SpecificOfferingStatement();
        statement.setAuthentication(authentication);
        statement.setPayload(payload2);

        return authApi.getSpecificOfferingStatement(statement);
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
        if(phone.contains("+")) {
            payload.setMobileNumber(phone);
        }else{
            payload.setMobileNumber("+" + phone);
        }
        profiler.setProfilepayload(payload);

        // Profile Info
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);

        System.out.println("churchMember: "+ churchMember);
        System.out.println("Phone Owner: "+ phoneOwner);

        // Update Payload
        AuthMemberRegister updatepayload = new AuthMemberRegister();
        updatepayload.setFullNames(fullname);
        updatepayload.setEmail(email);
        if(phone.contains("+")){
            phone = phone.substring(1,phone.length());
            updatepayload.setMobileNumber(phone);
        }else{
            updatepayload.setMobileNumber(phone);
        }
        updatepayload.setChurchCode(churchCode);
        updatepayload.setPreferredLanguage(language);
        updatepayload.setPhoneNumberPrivacy(phone_number_privacy);
        updatepayload.setResidence(residence);
        if (phoneOwner == null) {
            phoneOwner = false;
            updatepayload.setPhoneOwner(phoneOwner);
        }else{
            updatepayload.setPhoneOwner(phoneOwner);
        }
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

    @GetMapping("/off-statement")
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
        String headerValue = "attachment; filename=" + membershipNumber + ".pdf";
        response.setHeader(headerKey, headerValue);

        statementService.createOfferingStatement(statement,response);
        return "Generate Offering Statement";
    }


    @GetMapping("/off-statement-specific")
    public String generateSpecificOfferingStatement(@RequestParam("phone_number_specific") String phone_number,
                                            @RequestParam("start_date_specific") String start_date,
                                            @RequestParam("end_date_specific") String end_date,
                                            @RequestParam("pin_specific") String pin,
                                            @RequestParam("account_name") String account_info,
                                            HttpServletResponse response) throws IOException {

        // Final Session Number
        final long session_number = (long) ((Math.random() * 900000000) + 100000000);

        // Offering Statement Information
        SpecificOfferingStatement statement = new SpecificOfferingStatement();


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

        // Authentication
        com.example.tried.auth.member.specific.Authentication authentication = new
                com.example.tried.auth.member.specific.Authentication();
        authentication.setPin(pin);
        authentication.setPhoneNumber(phone_number);
        authentication.setSessionNumber(session_number);
        authentication.setPersonnelName(profile.getPayload().getMemberName());
        authentication.setInstitutionName(profile.getPayload().getChurchName());
        authentication.setInstitutionLevel("LOCAL CHURCH");
        authentication.setInstitutionNumber(profile.getPayload().getChurchCode());
        authentication.setUser("");
        authentication.setPassword("");

        String [] account = account_info.split("#");
        String account_name = account[0];
        String account_number = account[1];


        com.example.tried.auth.member.specific.Payload payload = new
                com.example.tried.auth.member.specific.Payload();
        payload.setAccountName(account_name);
        payload.setAccountNumber(account_number);
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);
        payload.setMemberNumber(membershipNumber);
        payload.setMemberName(profile.getPayload().getMemberName());
        payload.setNumberOfTries(1);

        statement.setPayload(payload);
        statement.setAuthentication(authentication);


        System.out.println("Generated Profile: " + profile);
        System.out.println("Member Number: " + membershipNumber);

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + membershipNumber + "-specific.pdf";
        response.setHeader(headerKey, headerValue);

        specificStatementService.createOfferingStatement(statement,response);
        return "Generate Specific Offering Statement";
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
    public LocalChurchTrustFundSummaryResponse getUSSDandCashSummary(@RequestParam("username")String username,
                                                                     @RequestParam("password")String password,
                                                                     @RequestParam("phone_number")String phone_number){

        // Session Numbers
        final int rand = (int) ((Math.random() * 9000000) + 1000000);


        //Member Profile Information
        MemberProfile memberProfile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);

        memberProfile.setProfilepayload(profilepayload);

        MemberProfileResponse details = authApi.getMemberDetails(memberProfile);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(details.getPayload().getChurchCode());

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
        return localChurchTrustFund;
    }

    @PostMapping("/home_church")
    public ChurchPaymentResponse homeChurchPayment(@RequestParam("phone_number") String phone_number,
                                                   @RequestParam("home_church") String home_church,
                                                   @RequestParam("self") String self){
        // Member Profile Information
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        // Member Payload
        MemberProfile profiler = new MemberProfile();
        profiler.setProfilepayload(profilepayload);

        // Get Membership Number
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);

        // Home Church Payment
        HomeChurchPayment homeChurchPayment = new HomeChurchPayment();

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Payload
        com.example.tried.auth.member.giving.HPayload payload = new com.example.tried.auth.member.giving.HPayload();
        payload.setChurchCode(profile.getPayload().getChurchCode());
        payload.setMembershipNumber(profile.getPayload().getMembershipNumber());
        payload.setWhereContributing(home_church);
        payload.setWhomToContribute(self);
        payload.setMobileNumber(phone_number);
        payload.setSessionNumber(String.valueOf(session_number));

        homeChurchPayment.setPayload(payload);

        ChurchPaymentResponse response = authApi.getHomeChurchPayment(homeChurchPayment);
        return response;
    }


    @PostMapping("/host_church")
    public ChurchPaymentResponse hostChurchPayment(@RequestParam("phone_number") String phone_number,
                                                   @RequestParam("host_church") String host_church,
                                                   @RequestParam("contribute") String contribute,
                                                   @RequestParam("host_church_code") String host_church_code){
        // Member Profile Information
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        // Member Payload
        MemberProfile profiler = new MemberProfile();
        profiler.setProfilepayload(profilepayload);

        // Get Membership Number
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);

        // Home Church Payment
        HostChurchPayment hostChurchPayment = new HostChurchPayment();

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Payload
        Gpayload payload = new Gpayload();
        payload.setChurchCode(profile.getPayload().getChurchCode());
        payload.setMembershipNumber(profile.getPayload().getMembershipNumber());
        payload.setWhereContributing(host_church);
        payload.setWhomToContribute(contribute);
        payload.setMobileNumber(phone_number);
        payload.setSessionNumber(String.valueOf(session_number));
        payload.setHostChurchCode(host_church_code);

        hostChurchPayment.setGpayload(payload);

        ChurchPaymentResponse response = authApi.getHostChurchPayment(hostChurchPayment);
        return response;
    }


    @PostMapping("/member_receive_funds")
    public String receiveFunds(@RequestParam("phone_number") String phone_number,
                                              @RequestParam("amount") int amount,
                                              @RequestParam("church_code") String church_code,
                                              @RequestParam("contribute") String contribute,
                               @RequestParam("receiver_id") String receiver_id,
                               @RequestParam("receiver_name") String receiver_name,
                               @RequestParam(value = "trust_funds[]", required = false) String[] trust_funds,
                               @RequestParam(value = "fund_amount[]", required = false) int[] fund_amount,
                               @RequestParam(value = "non_trust_funds[]", required = false) String[] non_trust_funds,
                               @RequestParam(value = "fund_amount1[]", required = false) int[] fund_amount1,
                               @RequestParam(value = "special_trust_funds[]", required = false) String[] special_trust_funds,
                               @RequestParam(value = "fund_amount2[]", required = false) int[] fund_amount2
                               ) throws JsonParseException {
        // Member Profile Information
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        final int session_number1 = (int) ((Math.random() * 9000000) + 1000000);

        // Member Payload
        MemberProfile profiler = new MemberProfile();
        profiler.setProfilepayload(profilepayload);

        // Get Membership Number
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);

        // Request Mobile Church Details with Code
        RequestChurchDetailsWithCode requestChurchDetailsWithCode = new RequestChurchDetailsWithCode();

        Churchpayload churchpayload = new Churchpayload();
        churchpayload.setSessionNumber(session_number);
        churchpayload.setChurchCode(church_code);
        churchpayload.setAccessNumber(phone_number);
        churchpayload.setMobileServiceProvider("Safaricom");
        requestChurchDetailsWithCode.setChurchpayload(churchpayload);

        RequestChurchDetailsWithCodeResponse churchCodeResponse =
                authApi.getChurchCodeDetails(requestChurchDetailsWithCode);

        // Get the Current Date Time
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);

        MobileReceiveFundsGiving giving = new MobileReceiveFundsGiving();

        RmPayload payload = new RmPayload();
        payload.setSessionNumber(session_number);
        payload.setChurchCode(profile.getPayload().getChurchCode());
        payload.setContributingFor(contribute);
        payload.setContributorType("Member");
        payload.setReceiverId(receiver_id);
        payload.setReceiverName(receiver_name);
        payload.setTotalAmount(amount);
        payload.setMeansOfPayment("M-PESA");
        payload.setCollectingParty("M-PESA");
        payload.setContributorContact(phone_number);
        payload.setContributorContactType("Phone Number");
        payload.setContributorName(profile.getPayload().getMemberName());
        payload.setContributingAs("Member");

        HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        HashMap<String, Integer> map2 = new HashMap<String, Integer>();
        HashMap<String, Integer> map3 = new HashMap<String, Integer>();

        FundDistribution fundDistribution = new FundDistribution();

        if(trust_funds != null) {
            for(int i = 0; i < trust_funds.length;i++){
                map1.put(trust_funds[i], fund_amount[i]);
            }
            fundDistribution.setTrustFunds(map1);
        }else{
            fundDistribution.setTrustFunds(map1);
        }

        if(non_trust_funds != null) {
            for(int i = 0; i < non_trust_funds.length;i++){
                map2.put(non_trust_funds[i], fund_amount1[i]);
            }
            fundDistribution.setNonTrustFunds(map2);
        }else {
            fundDistribution.setNonTrustFunds(map2);
        }

        if(special_trust_funds != null){
            for(int i = 0; i < special_trust_funds.length;i++){
                map3.put(special_trust_funds[i], fund_amount2[i]);
            }
            fundDistribution.setSpecialTrustFunds(map3);
        }else {
            fundDistribution.setSpecialTrustFunds(map3);
        }

        payload.setFundDistribution(fundDistribution);
        giving.setPayload(payload);

        System.out.println("Member Giving Object: "+ HelperUtility.toJSON(giving));

        MobileReceiveFundsResponse response = authApi.receiveMemberFunds(giving);
        String cfmsTransactionId = response.getCfmsTransactionId();
        String accountNumber = response.getAccountNumber();

        MpesaSTKRequest request = new MpesaSTKRequest();
        request.setAmount(amount);
        request.setPhoneNumber(phone_number);
        request.setCfmsTransactionId(cfmsTransactionId);
        request.setAccountNumber(accountNumber);
        request.setSessionNumber(String.valueOf(session_number1));

        MpesaSTKRequestResponse responser = authApi.getMPESASTKResponse(request);
        System.out.println("MPESA STK Request: "+ request);
        System.out.println("Mpesa Request Processing Right Now");
        return "Now";
    }

    @GetMapping("/check-numbers")
    public String checkNumbersSaved() throws JsonProcessingException {
        JSONObject object = new JSONObject();
        object.put("function", "mobileReceiveFunds");

        // Payload
        JSONObject payload = new JSONObject();
        payload.put("sessionNumber", 9279849);
        payload.put("churchCode", "29999");
        payload.put("contributorType", "Member");
        payload.put("contributorName", "Andrew Keitany");
        payload.put("contributingAs", "Member");
        payload.put("contributingFor", "Self");
        payload.put("receiverId", "CN3196");
        payload.put("receiverName", "Andrew Keitany");
        payload.put("totalAmount", 5);
        payload.put("meansOfPayment", "M-PESA");
        payload.put("contributorContactType", "Phone Number");
        payload.put("contributorContact", "254707981971");
        payload.put("collectingParty", "M-PESA");

        // HashMap
        HashMap<String, Integer> map = new HashMap<String, Integer>();


        // Trust Funds
        JSONObject trustFunds = new JSONObject();
        JSONObject nonTrustFunds = new JSONObject();
        JSONObject specialTrustFunds = new JSONObject();

        JSONObject fundsDistribution = new JSONObject();
        fundsDistribution.put("trustFunds", trustFunds);
        fundsDistribution.put("nonTrustFunds", nonTrustFunds);
        fundsDistribution.put("specialTrustFunds", specialTrustFunds);
        payload.put("fundDistribution", fundsDistribution);
        object.put("payload", payload);
        System.out.println("System Printed: "+object);
        return "Saved";
    }


    @GetMapping("/trust_fund_summary")
    public String getTrustFundSummary(HttpServletResponse response) throws IOException {
        String phone_number = "254786439659";
        trustFundSummary.trustFundSummaryReport(phone_number,response);
        return "Trust Fund Summary Generated";
    }

    @GetMapping("/transactions_tracing_summary")
    public String getTransactionTracingSummary(HttpServletResponse response,String start_date,
                                               String end_date,String username, String password, String phone_number) throws IOException {
        transactionTracingSummary.transactionSummaryReport(phone_number,response,start_date,end_date,username,password);
        return "Trust Fund Summary Generated";
    }


    @GetMapping("/non_trust_fund_summary")
    public LocalChurchNonTrustSummaryResponse getNonTrustFundSummary(@RequestParam("username")String username,
                                                                     @RequestParam("password")String password,
                                                                     @RequestParam("phone_number")String phone_number) throws IOException {

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);

        // Get the Personnel Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);


        // Non Trust Fund Authentication
        com.example.tried.auth.personnel.reports.non_trust_funds.Authentication authentication =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Authentication();
        authentication.setSessionNumber(session_number);
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setInstitutionNumber(response.getPayload().getOrganisationNumber());
        authentication.setInstitutionLevel(response.getPayload().getOrganisationLevel());
        authentication.setInstitutionName(response.getPayload().getOrganisationName());

        // Dates
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String start_date = now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(format);
        String end_date = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).format(format);

        // Non Trust Fund Payload
        com.example.tried.auth.personnel.reports.non_trust_funds.Payload payload =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Payload();
        payload.setChurchCode(response.getPayload().getOrganisationNumber());
        payload.setGroup("Not Applicable");
        payload.setChurchName(response.getPayload().getOrganisationName());
        payload.setMeansOfPayment("Cash");
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        LocalChurchNonTrustSummary nonTrustFundSummary = new LocalChurchNonTrustSummary();
        nonTrustFundSummary.setAuthentication(authentication);
        nonTrustFundSummary.setPayload(payload);

        LocalChurchNonTrustSummaryResponse response1 = authApi.getLocalChurchNonTrustFund(nonTrustFundSummary);

        String total_amount = response1.getTotalAmount();
        Integer local_combined_offerings = response1.getNonTrpayload().getLocalChurchFunds()
                .getLocalCombinedOfferings();
        return response1;
    }


    @GetMapping("/timers")
    public String getTimers() throws JsonProcessingException {
        Map<String, Integer> map = new HashMap<>();
        map.put("trustFunds", 1);
        map.put("specialTrustFunds", 2);
        JSONObject new_object = new JSONObject(map);
        System.out.println("Object: " + new_object);
        String jsonResult = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        System.out.println(jsonResult);
        return "Get the Timers";
    }


    // Testing the Effectiveness of List and Set
    @GetMapping("/timered")
    public String getTimed(){
        // Creating a list of strings
        List<String> aList = Arrays.asList("Geeks", "for", "GeeksQuiz", "Geeks", "for",
                "GeeksQuiz", "GFG", "GeeksforGeeks", "GFG");

        // Creating a hash set using constructor
        Set<String> hSet = new HashSet<String>(aList);

        System.out.println("Created HashSet is");
        for (String x : hSet)
            System.out.println(x);


        System.out.println("Created List is");
        for (String x : aList)
            System.out.println(x);
        return "Set of Information";
    }


    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        // trustFundSummaryExcel.export(response);
        TestExcelForm testExcelForm = new TestExcelForm();
        testExcelForm.export(response);
    }


    @GetMapping("/export/transaction-tracing")
    public void exportTransactionTracingDocument(HttpServletResponse outResponse,String start_date,String end_date,String username,
                                                 String password, String phone_number) throws IOException {



        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Member Profile
        MemberProfile memberProfile = new MemberProfile();

        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        memberProfile.setProfilepayload(profilepayload);

        MemberProfileResponse profiler = authApi.getMemberDetails(memberProfile);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(profiler.getPayload().getChurchCode());

        // Get the Personnel Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);

        // Authentication
        com.example.tried.auth.personnel.tracing.Authentication authenticate = new com.example.
                tried.auth.personnel.tracing.Authentication();
        authenticate.setInstitutionLevel(response.getPayload().getOrganisationLevel());
        authenticate.setInstitutionNumber(response.getPayload().getOrganisationNumber());
        authenticate.setInstitutionName(response.getPayload().getOrganisationName());
        authenticate.setUser(username);
        authenticate.setPassword(password);
        authenticate.setSessionNumber(String.valueOf(rand));
        authenticate.setPersonnelName(response.getPayload().getPersonnelName());

        com.example.tried.auth.personnel.tracing.TracingPayload payload = new com.example.tried.
                auth.personnel.tracing.TracingPayload();
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        LocalChurchTransactionTracing tracing = new LocalChurchTransactionTracing();
        tracing.setPayload(payload);
        tracing.setAuthentication(authenticate);

        LocalChurchTransactionTracingResponse response1 = authApi.getTransactionTracingSummary(tracing);
        List<com.example.tried.auth.personnel.tracing.TransactionsItem> transaction = response1.getPayload().getTransactions();

        System.out.println("Transaction Tracing: " + transaction);

        //outResponse.setContentType("application/pdf");
        outResponse.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transaction_tracing-" + start_date +" - "+ end_date  + ".xlsx";
        outResponse.setHeader(headerKey, headerValue);

        TransactionTracingExcel transactionTracingExcel = new TransactionTracingExcel();
        transactionTracingExcel.export(outResponse,transaction);
    }


    @GetMapping("/export/non_trust_fund")
    public void exportNonTrustFundDocument(HttpServletResponse response, String start_date, String end_date, String account_name,
                                           String username,String password, String phone_number) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";


        MemberProfile memberProfile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        memberProfile.setProfilepayload(profilepayload);

        MemberProfileResponse responsed = authApi.getMemberDetails(memberProfile);

        // Non Trust Fund Payload
        com.example.tried.auth.personnel.reports.non_trust_funds.Payload payload =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Payload();
        payload.setChurchCode(responsed.getPayload().getChurchCode());
        payload.setGroup("Not Applicable");
        payload.setChurchName(responsed.getPayload().getChurchName());
        payload.setMeansOfPayment(account_name);
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(responsed.getPayload().getChurchCode());

        // Get the Personnel Response
        MemberPersonnelResponse responsed2 = authApi.loginMemberPersonnel(personnel);


        // Non Trust Fund Authentication
        com.example.tried.auth.personnel.reports.non_trust_funds.Authentication authentication =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Authentication();
        authentication.setSessionNumber(session_number);
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setPersonnelName(responsed2.getPayload().getPersonnelName());
        authentication.setInstitutionNumber(responsed2.getPayload().getOrganisationNumber());
        authentication.setInstitutionLevel(responsed2.getPayload().getOrganisationLevel());
        authentication.setInstitutionName(responsed2.getPayload().getOrganisationName());

        LocalChurchNonTrustSummary nonTrustFundSummary = new LocalChurchNonTrustSummary();
        nonTrustFundSummary.setAuthentication(authentication);
        nonTrustFundSummary.setPayload(payload);
        System.out.println("Local Non Trust Fund Summary: "+nonTrustFundSummary);

        String headerValue = "attachment; filename=" + username + ".xlsx";
        response.setHeader(headerKey, headerValue);


        LocalChurchNonTrustSummaryResponse response1 = authApi.getLocalChurchNonTrustFund(nonTrustFundSummary);
        System.out.println("Local Non Trust Fund Summary: "+ response1);

        List<com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem> membersItems = response1.getNonTrpayload().getMembers();
        LocalNonTrustFundReportExcel nonTrustFundReportExcel = new LocalNonTrustFundReportExcel();
        nonTrustFundReportExcel.export(response, membersItems);
    }


    @GetMapping("/pdf/non_trust_fund")
    public String generateNonTrustFund(HttpServletResponse response,String start_date,String end_date,String account_name,
                                       String username,String password, String phone_number) throws IOException {

        MemberProfile memberProfile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        memberProfile.setProfilepayload(profilepayload);

        MemberProfileResponse responsed = authApi.getMemberDetails(memberProfile);

        // Non Trust Fund Payload
        com.example.tried.auth.personnel.reports.non_trust_funds.Payload payload =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Payload();
        payload.setChurchCode(responsed.getPayload().getChurchCode());
        payload.setGroup("Not Applicable");
        payload.setChurchName(responsed.getPayload().getChurchName());
        payload.setMeansOfPayment(account_name);
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(responsed.getPayload().getChurchCode());

        // Get the Personnel Response
        MemberPersonnelResponse responsed2 = authApi.loginMemberPersonnel(personnel);


        // Non Trust Fund Authentication
        com.example.tried.auth.personnel.reports.non_trust_funds.Authentication authentication =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Authentication();
        authentication.setSessionNumber(session_number);
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setPersonnelName(responsed2.getPayload().getPersonnelName());
        authentication.setInstitutionNumber(responsed2.getPayload().getOrganisationNumber());
        authentication.setInstitutionLevel(responsed2.getPayload().getOrganisationLevel());
        authentication.setInstitutionName(responsed2.getPayload().getOrganisationName());

        LocalChurchNonTrustSummary nonTrustFundSummary = new LocalChurchNonTrustSummary();
        nonTrustFundSummary.setAuthentication(authentication);
        nonTrustFundSummary.setPayload(payload);
        System.out.println("Local Non Trust Fund Summary: "+nonTrustFundSummary);


        LocalChurchNonTrustSummaryResponse response1 = authApi.getLocalChurchNonTrustFund(nonTrustFundSummary);
        System.out.println("Local Non Trust Fund Summary: "+ response1);

        List<com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem> membersItems = response1.getNonTrpayload().getMembers();

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=local_non_trust_fund " + start_date +" - "+ end_date  + ".pdf";
        response.setHeader(headerKey, headerValue);

        testPDFSummary.nonTrustFundSummaryReport(response, phone_number, start_date, end_date, membersItems);
        return "Generate Local Non Trust Fund";
    }

    // Check if the Number Exists in the Database
    @PostMapping(path="/check-name")
    public MemberProfileResponse getMemberName(@RequestParam("phone_number")String phoneNumber){
        String response = "";

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);
        return response1;
    }

    @PostMapping(path="/check-account")
    public RequestChurchDetailsResponse getMemberChurchAccounts(@RequestParam("phone_number")String phoneNumber){

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);

        RequestChurchDetails requestCode = new RequestChurchDetails();
        requestCode.setChurchCode(response1.getPayload().getChurchCode());
        requestCode.setAccessPoint("Web App");
        requestCode.setConnectionPurpose("Registration");
        requestCode.setAccessNumber(phoneNumber);
        requestCode.setMobileServiceProvider("Safaricom");


        RequestChurchDetailsResponse churchDetails = authApi.getMemberChurchDetails(requestCode);
        return churchDetails;
    }

    @PostMapping(path="/check-phone")
    public MemberProfileResponse checkPhoneNumberExist(@RequestParam("phone_number")String phoneNumber){

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profile.setProfilepayload(payload);

        MemberProfileResponse response = authApi.getMemberDetails(profile);

        return response;
    }

    // Check Member Id
    @PostMapping(path="/check-member-id")
    public RequestMemberDetailsResponse checkCfmsMemberNumber(@RequestParam("cfms_member_id")String cfms_member_id){
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Request Member Information
        RequestMemberDetails details = new RequestMemberDetails();
        Mempayload mempayload = new Mempayload();
        mempayload.setMembershipNumber(cfms_member_id);
        mempayload.setMemberDescription("Member");
        mempayload.setSessionNumber(String.valueOf(session_number));

        details.setMempayload(mempayload);

        RequestMemberDetailsResponse requestMemberDetails = authApi.getFullMemberDetails(details);

        return requestMemberDetails;
    }


    // Check Member Id
    @PostMapping(path="/check-member")
    public RequestMemberDetailsResponse checkCfmsMember(@RequestParam("phone_number")String phone_number){

        // Member Profile Information
        MemberProfile profile = new MemberProfile();

        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);
        profile.setProfilepayload(profilepayload);

        // Member Profile Response
        MemberProfileResponse responsed = authApi.getMemberDetails(profile);


        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Request Member Information
        RequestMemberDetails details = new RequestMemberDetails();
        Mempayload mempayload = new Mempayload();
        mempayload.setMembershipNumber(responsed.getPayload().getMembershipNumber());
        mempayload.setMemberDescription("Member");
        mempayload.setSessionNumber(String.valueOf(session_number));

        details.setMempayload(mempayload);

        RequestMemberDetailsResponse requestMemberDetails = authApi.getFullMemberDetails(details);
        return requestMemberDetails;
    }


    @GetMapping(path="/check-json")
    public HashMap<String, Integer> getCheckJson() throws JSONException {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Total Funds", 256000);
        hashMap.put("Offering", 35000);
        hashMap.put("USSD", 25000);

        return hashMap;
    }

    // Function to Get Non Trust Fund Dashboard Information
    @GetMapping("/report/non_trust_fund")
    public HashMap<String, Double> generateNonTrustFundReport(@RequestParam("username")String username,
                                                              @RequestParam("password")String password,
                                                              @RequestParam("phone_number") String phone_number) throws IOException {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String start_date = now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(format);
        String end_date = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).format(format);

        String Cash = "Cash";
        String USSD = "USSD";
        String All = "Not Applicable";

        MemberProfile memberProfile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        memberProfile.setProfilepayload(profilepayload);

        MemberProfileResponse responsed = authApi.getMemberDetails(memberProfile);

        // Non Trust Fund Payload(USSD)
        com.example.tried.auth.personnel.reports.non_trust_funds.Payload payload =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Payload();
        payload.setChurchCode(responsed.getPayload().getChurchCode());
        payload.setGroup("Not Applicable");
        payload.setChurchName(responsed.getPayload().getChurchName());
        payload.setMeansOfPayment("USSD");
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        // Non Trust Fund Payload(All)
        com.example.tried.auth.personnel.reports.non_trust_funds.Payload payload1 =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Payload();
        payload1.setChurchCode(responsed.getPayload().getChurchCode());
        payload1.setGroup("Not Applicable");
        payload1.setChurchName(responsed.getPayload().getChurchName());
        payload1.setMeansOfPayment("Cash");
        payload1.setStartDate(start_date);
        payload1.setEndDate(end_date);

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        // final int session_number1 = (int) ((Math.random() * 9000000) + 1000000);

        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(responsed.getPayload().getChurchCode());

        // Get the Personnel Response
        MemberPersonnelResponse responsed2 = authApi.loginMemberPersonnel(personnel);

        String name = responsed.getPayload().getMemberName();

        System.out.println("Member Name: "+ responsed.getPayload().getMemberName());
        System.out.println("Member Name 2: "+ responsed2.getPayload().getPersonnelName());

        // Non Trust Fund Authentication
        com.example.tried.auth.personnel.reports.non_trust_funds.Authentication authentication =
                new com.example.tried.auth.personnel.reports.non_trust_funds.Authentication();
        authentication.setSessionNumber(session_number);
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setPersonnelName(name.trim());
        authentication.setInstitutionNumber(responsed2.getPayload().getOrganisationNumber());
        authentication.setInstitutionLevel(responsed2.getPayload().getOrganisationLevel());
        authentication.setInstitutionName(responsed2.getPayload().getOrganisationName());

        // USSD Non Trust Fund
        LocalChurchNonTrustSummary nonTrustFundSummary = new LocalChurchNonTrustSummary();
        nonTrustFundSummary.setAuthentication(authentication);
        nonTrustFundSummary.setPayload(payload);

        // Total Non Trust Fund
        LocalChurchNonTrustSummary nonTrustFundSummary1 = new LocalChurchNonTrustSummary();
        nonTrustFundSummary1.setAuthentication(authentication);
        nonTrustFundSummary1.setPayload(payload1);

        LocalChurchNonTrustSummaryResponse response1 = authApi.getLocalChurchNonTrustFund(nonTrustFundSummary);
        LocalChurchNonTrustSummaryResponse response2 = authApi.getLocalChurchNonTrustFund(nonTrustFundSummary1);

        Double Total_Amount = Double.parseDouble(response2.getTotalAmount()) + Double.parseDouble(response1.getTotalAmount());
        List<com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem> transactions =  response2.getNonTrpayload().getMembers();


        // Total Combined Offerings
        Double Total_Combined_Offerings = Double.parseDouble(String.valueOf(response1.getNonTrpayload().getLocalChurchFunds().getLocalCombinedOfferings())) +
                Double.parseDouble(String.valueOf(response2.getNonTrpayload().getLocalChurchFunds().getLocalCombinedOfferings()));

        List<com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem> valid_transactions =
                new ArrayList<com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem>();

        // Valid Transactions
        for(com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem said_transaction : transactions) {
            if(said_transaction.getReceiptNumber() != null){
                valid_transactions.add(said_transaction);
            }
        }

        // Non Trust Fund HashMap to Add Information to the Non Trust Fund JSON File
        HashMap<String, Double> nonTrustFundSummaryReport = new HashMap<String, Double>();
        nonTrustFundSummaryReport.put("USSD", Double.parseDouble(response1.getTotalAmount()));
        nonTrustFundSummaryReport.put("totalAmount",Total_Amount);
        nonTrustFundSummaryReport.put("cashAmount", Double.parseDouble(response2.getTotalAmount()));
        nonTrustFundSummaryReport.put("transactions", Double.parseDouble(String.valueOf(valid_transactions.size())));
        nonTrustFundSummaryReport.put("localCombinedOfferingsUSSD", Double.parseDouble(String.valueOf(response1.getNonTrpayload().getLocalChurchFunds().getLocalCombinedOfferings())));
        nonTrustFundSummaryReport.put("localCombinedOfferingsCash", Double.parseDouble(String.valueOf(response2.getNonTrpayload().getLocalChurchFunds().getLocalCombinedOfferings())));
        nonTrustFundSummaryReport.put("localCombinedOfferings", Total_Combined_Offerings);
        return nonTrustFundSummaryReport;
    }

    @GetMapping("/report/local_church_offering")
    public HashMap<String, Object> generateLocalChurchOfferingReport(@RequestParam("phone_number")String phone_number,
                                                                     @RequestParam("username")String username,
                                                                     @RequestParam("password")String password) throws IOException {


        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String start_date = now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(format);
        String end_date = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).format(format);

        // Local Church Authentication
        com.example.tried.auth.personnel.reports.offering.Authentication authentication =
                new com.example.tried.auth.personnel.reports.offering.Authentication();

        // Session Number
        final int session_number = (int) ((Math.random() * 900000000) + 100000000);

        // Member Profile Information
        MemberProfile profile = new MemberProfile();

        // Profile Payload
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(profilepayload);

        // Get Member Profile Information
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Personnel Details
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(response.getPayload().getChurchCode());

        MemberPersonnelResponse personnelResponse = authApi.loginMemberPersonnel(personnel);


        // Authentication Details
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setSessionNumber(session_number);
        authentication.setInstututionName(personnelResponse.getPayload().getOrganisationName());
        authentication.setInstututionNumber(personnelResponse.getPayload().getOrganisationNumber());
        authentication.setInstututionLevel(personnelResponse.getPayload().getOrganisationLevel());
        authentication.setPersonnelName(personnelResponse.getPayload().getPersonnelName());

        // Payload Information
        LocalChurchPayload localChurchPayload = new LocalChurchPayload();
        localChurchPayload.setChurchCode(response.getPayload().getChurchCode());
        localChurchPayload.setGroup("Not Applicable");
        localChurchPayload.setChurchName(response.getPayload().getChurchName());
        localChurchPayload.setMeansOfPayment("Not Applicable");
        localChurchPayload.setStartDate(start_date);
        localChurchPayload.setEndDate(end_date);

        // Payload Information
        LocalChurchPayload localChurchPayload2 = new LocalChurchPayload();
        localChurchPayload2.setChurchCode(response.getPayload().getChurchCode());
        localChurchPayload2.setGroup("Not Applicable");
        localChurchPayload2.setChurchName(response.getPayload().getChurchName());
        localChurchPayload2.setMeansOfPayment("USSD");
        localChurchPayload2.setStartDate(start_date);
        localChurchPayload2.setEndDate(end_date);


        // Local Church Offering Reports
        LocalChurchOfferingSummary summary = new LocalChurchOfferingSummary();
        summary.setPayload(localChurchPayload);
        summary.setAuthentication(authentication);

        // Local Church Offering Reports
        LocalChurchOfferingSummary summary1 = new LocalChurchOfferingSummary();
        summary1.setPayload(localChurchPayload2);
        summary1.setAuthentication(authentication);

        LocalChurchOfferingSummaryResponse localChurch = authApi.getLocalChurchOfferingReports(summary);
        LocalChurchOfferingSummaryResponse localChurch2 = authApi.getLocalChurchOfferingReports(summary1);

        Double Cash_Amount = Double.valueOf(localChurch.getTotalAmount()) -
                Double.valueOf(localChurch2.getTotalAmount());

        HashMap<String, Object> LocalChurchOffering = new HashMap<String, Object>();
        LocalChurchOffering.put("localChurchFunds", localChurch.getPayload().getLocalChurchFunds());
        LocalChurchOffering.put("totalAmount", localChurch.getTotalAmount());
        LocalChurchOffering.put("localTrustFunds", localChurch.getPayload().getTrustFunds());
        LocalChurchOffering.put("transactions", localChurch.getPayload().getMembers().size());
        LocalChurchOffering.put("USSD", localChurch2.getTotalAmount());
        LocalChurchOffering.put("Cash", Cash_Amount);

        return LocalChurchOffering;
    }

    @GetMapping("/datetime")
    public String getFirstdayOftheMonth(){
        HashMap<String, String> theMap4 = new HashMap<String, String>();
        theMap4.put("Avenue", "Hospital");
        theMap4.put("Avenue2", "Hospital2");
        theMap4.put("Avenue3", "Hospital3");

        HashMap<String, Object> theMap3 = new HashMap<String, Object>();
        theMap3.put("organisationName", "Hospital");
        theMap3.put("priority_number", "Hospital2");
        theMap3.put("accountName", theMap4);


        HashMap<String, Object> theMap2 = new HashMap<String, Object>();
        theMap2.put("Avenue",  theMap3);
        theMap2.put("Avenue2", theMap3);
        theMap2.put("Avenue3", theMap3);

        HashMap<String, Object> theMap = new HashMap<String, Object>();
        theMap.put("Now", "Then");
        theMap.put("Here", 2);
        theMap.put("HashMapped", theMap2);
        System.out.println("Now: "+ theMap);
        System.out.println("Now: "+ theMap.get("HashMapped"));
        System.out.println("Now JSON: "+ HelperUtility.toJSON(theMap));

        /*
        com.example.tried.auth.personnel.accounts.Payload payload = new
                com.example.tried.auth.personnel.accounts.Payload();
        payload.setAccountList();
        */
        return "Date of the Month";
    }


    @GetMapping("/get-keys")
    public String getKeysOfAHashMap(){
        HashMap<String, Integer> accounts = new HashMap<String, Integer>();
        accounts.put("Prison_Visitation", 250);
        accounts.put("Pathfinders", 40);
        accounts.put("Singles_Water_Project", 600);
        accounts.put("Prayer_And_Retention", 3680);
        accounts.put("Church_Budget", 7985);
        accounts.put("Sabbath_School", 160);
        accounts.put("Youths", 450);
        accounts.put("Local_Church", 9350);
        accounts.put("Welfare", 9200);
        accounts.put("Elders", 1400);
        accounts.put("Adventurers", 1040);
        accounts.put("Development", 250);
        accounts.put("Camp_Meeting_Expenses", 1050);
        accounts.put("Children", 940);
        accounts.put("Singles", 960);
        accounts.put("Communication", 200);
        accounts.put("Msamaria", 800);
        accounts.put("Chaplaincy", 100);
        accounts.put("Amo", 140);
        accounts.put("local_combined_offerings", 16664);
        accounts.put("Crusade", 70000);
        accounts.put("Special_Needs", 700);
        accounts.put("Evangelism", 50);
        accounts.put("Ambassadors", 40);
        accounts.put("Camporee", 40);
        accounts.put("Health_Hiv_And_Aids", 50);
        accounts.put("Master_Guide", 40);
        accounts.put("Church_Choir", 100);
        accounts.put("Deaconry", 3535);
        accounts.put("Vocational_Bible_School", 40);


        for (Map.Entry<String,Integer> mapElement : accounts.entrySet()) {
            String key = mapElement.getKey();
            System.out.println("Key"+ key);
        }
        return "Generated Hash Maps";
    }


    @GetMapping("/trust_fund_reporting")
    public String generateTrustFundReport(HttpServletResponse response) throws IOException {
        String input = "Excel";
        if(input == "Excel"){
            getTrustFundSummary(response);
        }
        return "Excel Successfully Downloaded";
    }


    @GetMapping("/exporting-non-trust-fund")
    public String generateNonTrustFundReport(HttpServletResponse response,
                                             @RequestParam("start_date") String start_date,
                                             @RequestParam("end_date") String end_date,
                                             @RequestParam("account_name") String account_name,
                                             @RequestParam("input") String input,
                                             String username,String password, String phone_number) throws IOException{


        if(input.contains("Excel")){
            exportNonTrustFundDocument(response,start_date,end_date,account_name,username,password,phone_number);
        }else if(input.contains("PDF")){
            generateNonTrustFund(response,start_date,end_date,account_name,username,password,phone_number);
        }
        return "Non Trust Fund Report Downloaded Successfully";
    }


    @GetMapping("/exporting-transaction-tracing")
    public String generateTransactionTracing(HttpServletResponse response,
                                             @RequestParam("start_date") String start_date,
                                             @RequestParam("end_date") String end_date,
                                             @RequestParam("account_name") String account_name,
                                             @RequestParam("input") String input,
                                             @RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("phone_number") String phone_number) throws IOException{


        if(input.contains("Excel")){
            exportTransactionTracingDocument(response,start_date,end_date,username,password,phone_number);
        }else if(input.contains("PDF")){
            getTransactionTracingSummary(response,start_date,end_date,username,password,phone_number);
        }
        return "Transaction Tracing Report Report Downloaded Successfully";
    }
}
