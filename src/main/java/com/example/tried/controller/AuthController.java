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
import com.example.tried.auth.personnel.*;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummary;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummaryResponse;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracing;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracingResponse;
import com.example.tried.dto.account.OfferStatement;
import com.example.tried.services.AuthApi;
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
        AuthMemberRegister updatepayload = new AuthMemberRegister();
        updatepayload.setFullNames(fullname);
        updatepayload.setEmail(email);
        updatepayload.setMobileNumber(phone);
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
        String phone_number = "254786439659";

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

        RequestChurchDetailsWithCodeResponse churchCodeResponse = authApi.getChurchCodeDetails(requestChurchDetailsWithCode);

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
        payload.setReceiverId(profile.getPayload().getMembershipNumber());
        payload.setReceiverName(profile.getPayload().getMemberName());
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

        MobileReceiveFundsResponse response = authApi.receiveMemberFunds(giving);
        String cfmsTransactionId = response.getCfmsTransactionId();
        String accountNumber = response.getAccountNumber();

        MpesaSTKRequest request = new MpesaSTKRequest();
        request.setAmount(amount);
        request.setPhoneNumber(phone_number);
        request.setCfmsTransactionId(cfmsTransactionId);
        request.setAccountNumber(accountNumber);
        request.setSessionNumber(String.valueOf(session_number1));



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
        trustFundSummary.trustFundSummaryReport(response);
        return "Trust Fund Summary Generated";
    }


    @GetMapping("/transactions_tracing_summary")
    public String getTransactionTracingSummary(HttpServletResponse response) throws IOException {
        transactionTracingSummary.transactionSummaryReport(response);
        return "Trust Fund Summary Generated";
    }


    @GetMapping("/non_trust_fund_summary")
    public LocalChurchNonTrustSummaryResponse getNonTrustFundSummary() throws IOException {
        String username = "mwakesho";
        String password = "0389";
        String phone_number = "254786439659";

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
        String start_date = "2024-1-1";
        String end_date = "2024-1-31";

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
    public void exportTransactionTracingDocument(HttpServletResponse Outesponse) throws IOException {
        // Profile Information
        String username = "mwakesho";
        String password = "0389";
        String phone_number = "254786439659";

        String start_date = "2024-1-1";
        String end_date = "2024-1-31";

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
        authenticate.setInstututionLevel(response.getPayload().getOrganisationLevel());
        authenticate.setInstututionNumber(response.getPayload().getOrganisationNumber());
        authenticate.setInstututionName(response.getPayload().getOrganisationName());
        authenticate.setUser(username);
        authenticate.setPassword(password);
        authenticate.setSessionNumber(rand);

        com.example.tried.auth.personnel.tracing.TracingPayload payload = new com.example.tried.
                auth.personnel.tracing.TracingPayload();
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        LocalChurchTransactionTracing tracing = new LocalChurchTransactionTracing();
        tracing.setPayload(payload);
        tracing.setAuthentication(authenticate);

        LocalChurchTransactionTracingResponse response1 = authApi.getTransactionTracingSummary(tracing);
        List<com.example.tried.auth.personnel.tracing.TransactionsItem> transaction = response1.getPayload().getTransactions();

        TransactionTracingExcel transactionTracingExcel = new TransactionTracingExcel();
        transactionTracingExcel.export(Outesponse,transaction);
    }


    @GetMapping("/export/non_trust_fund")
    public void exportNonTrustFundDocument(HttpServletResponse response) throws IOException {
        // Profile Information
        String username = "mwakesho";
        String password = "0389";
        String phone_number = "254786439659";

        String start_date = "2024-1-1";
        String end_date = "2024-1-31";

        String Cash = "Cash";
        String USSD = "USSD";
        String All = "Not Applicable";
        String Means = "";


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

        /*
        if(Means == "All"){
            payload.setMeansOfPayment("Not Applicable");
        }else if(Means == "USSD"){
            payload.setMeansOfPayment("USSD");
        }else{
            payload.setMeansOfPayment("Cash");
        }
         */
        payload.setMeansOfPayment("Not Applicable");
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
    public String generateNonTrustFund(HttpServletResponse response) throws IOException {

        // Profile Information
        String username = "mwakesho";
        String password = "0389";
        String phone_number = "254786439659";

        String start_date = "2024-1-1";
        String end_date = "2024-1-31";

        String Cash = "Cash";
        String USSD = "USSD";
        String All = "Not Applicable";
        String Means = "";

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

        /*
        if(Means == "All"){
            payload.setMeansOfPayment("Not Applicable");
        }else if(Means == "USSD"){
            payload.setMeansOfPayment("USSD");
        }else{
            payload.setMeansOfPayment("Cash");
        }
         */
        payload.setMeansOfPayment("Not Applicable");
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
    public RequestChurchDetailsWithCodeResponse getMemberChurchAccounts(@RequestParam("phone_number")String phoneNumber){

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);

        RequestChurchDetailsWithCode requestCode = new RequestChurchDetailsWithCode();

        Churchpayload churchpayload = new Churchpayload();
        churchpayload.setChurchCode(response1.getPayload().getChurchCode());
        churchpayload.setSessionNumber(session_number);
        churchpayload.setAccessNumber(phoneNumber);
        churchpayload.setMobileServiceProvider("Safaricom");
        requestCode.setChurchpayload(churchpayload);
        RequestChurchDetailsWithCodeResponse churchDetails = authApi.getChurchCodeDetails(requestCode);
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
}
