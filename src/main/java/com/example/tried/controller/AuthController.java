package com.example.tried.controller;


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
import com.example.tried.services.AuthApi;
import com.example.tried.services.OfferingStatementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthApi authApi;

    @Autowired
    OfferingStatementService statementService;

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

    @GetMapping("/off-statement")
    public String generateOfferingStatement(@RequestParam("phone_number") String phone_number,
                                            @RequestParam("start_date") String start_date,
                                            @RequestParam("end_date") String end_date,
                                            @RequestParam("pin") String pin) throws FileNotFoundException {

        // Offering Statement Information
        OfferStatement statement = new OfferStatement();
        statement.setStart_date(start_date);
        statement.setEnd_date(end_date);
        statement.setPin(pin);
        statement.setPhone_number(phone_number);

        statementService.createOfferingStatement(statement);
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
        System.out.println("Request Code With Response: "+request.getChurchName());

        return request;
    }
}
