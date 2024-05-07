package com.example.tried.controller;

import com.example.tried.auth.dashboard.Dapayload;
import com.example.tried.auth.dashboard.ListMembers;
import com.example.tried.auth.dashboard.ListMembersResponse;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.financial.MemberOffering;
import com.example.tried.auth.financial.MemberOfferingResponse;
import com.example.tried.auth.financial.OfferingAuthentication;
import com.example.tried.auth.financial.OfferingPayload;
import com.example.tried.auth.funds.SelectTrustFunds;
import com.example.tried.auth.funds.SelectTrustFundsResponse;
import com.example.tried.auth.member.*;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.accounts.LocalChurchAccounts;
import com.example.tried.auth.personnel.accounts.LocalChurchAccountsSelectResponse;
import com.example.tried.auth.personnel.accounts.SelectLocalChurchPayload;
import com.example.tried.auth.personnel.accounts.new_account.CreateLocalChurchAccount;
import com.example.tried.auth.personnel.accounts.new_account.CreateLocalChurchAccountResponse;
import com.example.tried.auth.personnel.accounts.new_account.Payload;
import com.example.tried.auth.personnel.accounts.update_account.EditValues;
import com.example.tried.auth.personnel.accounts.update_account.UpdateLocalChurchAccount;
import com.example.tried.auth.personnel.accounts.update_account.UpdateLocalChurchAccountResponse;
import com.example.tried.auth.personnel.department.DepartmentRequest;
import com.example.tried.auth.personnel.department.DepartmentResponse;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccounts;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccountsResponse;
import com.example.tried.auth.personnel.receipting.*;
import com.example.tried.auth.personnel.receipting.select.SelectCashReceipting;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummary;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummaryResponse;
import com.example.tried.auth.personnel.reports.offering.LocalChurchPayload;
import com.example.tried.auth.personnel.reports.transcript.Authentication;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscript;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscriptResponse;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracing;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracingResponse;
import com.example.tried.auth.personnel.tracing.TracingPayload;
import com.example.tried.auth.personnel.transfer.AccountDetails;
import com.example.tried.auth.personnel.transfer.MobileReceiveFundsTransfer;
import com.example.tried.auth.personnel.transfer.MobileReceiveFundsTransferResponse;
import com.example.tried.auth.personnel.transfer.TransferDuration;
import com.example.tried.auth.reports.statements.LocalChurchOfferingStatement;
import com.example.tried.auth.reports.statements.LocalChurchOfferingStatementResponse;
import com.example.tried.auth.tracing.TransactionTracingMember;
import com.example.tried.auth.tracing.TransactionTracingMemberResponse;
import com.example.tried.auth.tracing.Tropayload;
import com.example.tried.auth.tracing.receipt.TransactionTracingMemberReceipt;
import com.example.tried.auth.tracing.receipt.TransactionTracingMemberReceiptResponse;
import com.example.tried.services.*;
import com.example.tried.services.reports.excel.LocalChurchOfferingReportExcel;
import com.example.tried.services.reports.pdf.LocalChurchOfferingReport;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;


@RestController
@RequestMapping("/personnel")
@Slf4j
public class PersonnelController {

    @Autowired
    AuthApi authApi;
    @Autowired
    PersonnelApi personnelApi;

    @Autowired
    LocalChurchOfferingStatementService localChurchStatementService;

    @Autowired
    MemberOfferingStatementService offeringStatementService;

    @Autowired
    OfferingStatementService statementService;

    @Autowired
    LocalChurchOfferingReport localChurchOfferingReport;

    private final ObjectMapper objectMapper;

    public PersonnelController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping(path="/profile")
    public HashMap<String, String> getMemberProfile(@RequestParam("phone_number")String phoneNumber) throws JsonProcessingException {

        if (phoneNumber.contains("+")) {
            phoneNumber = phoneNumber;
        }else{
            phoneNumber = String.format("%s%s", "+", phoneNumber);
        }
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber(phoneNumber);

        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);

        // Request Church Details
        RequestChurchDetails requestChurchDetails = new RequestChurchDetails();
        requestChurchDetails.setChurchCode(response1.getPayload().getChurchCode());
        requestChurchDetails.setMobileServiceProvider("Safaricom");
        requestChurchDetails.setAccessNumber(phoneNumber);

        // More Profile Info
        RequestChurchDetailsResponse requestCode = authApi.getMemberChurchDetails(requestChurchDetails);

        HashMap<String, String> jsonObject= new HashMap<String, String>();
        jsonObject.put("fullName", requestCode.getMemberName());
        jsonObject.put("churchCode", requestCode.getChurchNumber());
        jsonObject.put("language", response1.getPayload().getPreferredLanguage());
        jsonObject.put("otherPhone", requestCode.getOtherPhoneNumber());
        jsonObject.put("groups", requestCode.getGroups());

        System.out.println("Profile Information: "+ jsonObject);

        return jsonObject;
    }


    // Activate Deactivated Member
    // Get the Profile Items to Save Registration MemberRegisterUpdateResponse
    @PostMapping(path="/activate-member")
    public MemberRegisterUpdateResponse ActivateMemberDetails(@RequestParam("phone") String phone) throws JsonProcessingException {

        if (phone.contains("+")) {
            phone = phone;
        }else{
            phone = String.format("%s%s", "+",phone);
        }

        // Register Profile
        MemberRegistrationUpdate authMemberRegister = new MemberRegistrationUpdate();

        // Generate Session Number
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Get Profile Information
        MemberProfile profiler = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        if (phone.contains("+")) {
            payload.setMobileNumber(phone);
        }else{
            payload.setMobileNumber("+" + phone);
        }
        profiler.setProfilepayload(payload);

        // Profile Info
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);


        // Update RPayload
        AuthMemberRegister updatepayload = new AuthMemberRegister();
        updatepayload.setFullNames(profile.getPayload().getMemberName());
        updatepayload.setEmail("any@gmail.com");
        if(phone.contains("+")){
            phone = phone.substring(1,phone.length());
            updatepayload.setMobileNumber(phone);
        }else{
            updatepayload.setMobileNumber(phone);
        }
        updatepayload.setChurchCode(profile.getPayload().getMemberName());
        updatepayload.setPreferredLanguage(profile.getPayload().getPreferredLanguage());
        updatepayload.setPhoneNumberPrivacy("Normal");
        updatepayload.setResidence("Any");
        updatepayload.setPhoneOwner(true);
        updatepayload.setIsMember("true");
        updatepayload.setGivingReceiptedTo("Self");
        updatepayload.setMembershipNumber(profile.getPayload().getMembershipNumber());
        updatepayload.setOtherPhoneNumber("+254");
        updatepayload.setSessionNumber(rand);

        authMemberRegister.setUpdatepayload(updatepayload);


        MemberRegisterUpdateResponse responsed = authApi.getMemberRegistrationUpdate(authMemberRegister);
        return responsed;
    }


    // Activate Deactivated Member
    // Get the Profile Items to Save Registration MemberRegisterUpdateResponse
    @PostMapping(path="/deactivate-member")
    public MemberRegisterUpdateResponse DeactivateMemberDetails(@RequestParam("phone") String phone) throws JsonProcessingException {

        // Register Profile
        MemberRegistrationUpdate authMemberRegister = new MemberRegistrationUpdate();

        // Generate Session Number
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Get Profile Information
        MemberProfile profiler = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        if (phone.contains("+")) {
            payload.setMobileNumber(phone);
        }else{
            payload.setMobileNumber("+" + phone);
        }
        profiler.setProfilepayload(payload);

        // Profile Info
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);


        // Update RPayload
        AuthMemberRegister updatepayload = new AuthMemberRegister();
        updatepayload.setFullNames(profile.getPayload().getMemberName());
        updatepayload.setEmail("any@gmail.com");
        updatepayload.setMobileNumber(phone);
        updatepayload.setChurchCode(profile.getPayload().getMemberName());
        updatepayload.setPreferredLanguage(profile.getPayload().getPreferredLanguage());
        updatepayload.setPhoneNumberPrivacy("Normal");
        updatepayload.setResidence("Any");
        updatepayload.setPhoneOwner(true);
        updatepayload.setIsMember("false");
        updatepayload.setGivingReceiptedTo("Self");
        updatepayload.setMembershipNumber(profile.getPayload().getMembershipNumber());
        updatepayload.setOtherPhoneNumber("+254");
        updatepayload.setSessionNumber(rand);

        authMemberRegister.setUpdatepayload(updatepayload);

        System.out.println("Update RPayload: " + updatepayload.toString());
        System.out.println("Auth Member Registration: "+ HelperUtility.toJSON(authMemberRegister));
        MemberRegisterUpdateResponse responsed = authApi.getMemberRegistrationUpdate(authMemberRegister);
        return responsed;
    }


    @GetMapping(path="/church-members")
    public ListMembersResponse getMembers(@RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("phone_number") String phone_number) throws JsonProcessingException{


        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

        // Get Profile Information
        MemberProfile profiler = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profiler.setProfilepayload(payload);

        MemberProfileResponse responser = authApi.getMemberDetails(profiler);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(responser.getPayload().getChurchCode());

        // Get the Personnel Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);

        // Get the Number of Members in a Church
        ListMembers members = new ListMembers();

        // Personnel Details
        String church_code = response.getPayload().getOrganisationNumber();
        String church_name = response.getPayload().getOrganisationName();
        String church_level = response.getPayload().getOrganisationLevel();
        String personnel_name = response.getPayload().getPersonnelName();
        String member_no = response.getPayload().getPersonnelCfmsNumber();

        Dapayload dashboard = new Dapayload();
        dashboard.setChurchName(church_name);
        dashboard.setPassword(password);
        dashboard.setUser(username);
        dashboard.setChurchCode(church_code);

        // Get Dashboard Information
        members.setDapayload(dashboard);
        ListMembersResponse dashboardResponse = authApi.getChurchMembers(members);

        return dashboardResponse;
    }

    // Create Local Church Account
    @PostMapping("/create-account")
    public CreateLocalChurchAccountResponse createLocalChurchAccount(@RequestParam("account_name")String account_name,
                                                                     @RequestParam("department")String department,
                                                                     @RequestParam("priority")String priority,
                                                                     @RequestParam("duration")String duration,
                                                                     @RequestParam("status")String status,
                                                                     @RequestParam("username")String username,
                                                                     @RequestParam("password")String password,
                                                                     @RequestParam("phone_number")String phone_number)  throws JsonProcessingException{

        // Get the Member Profile
        MemberProfile profile = new MemberProfile();

        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(profilepayload);

        //Get the Member Profile Response
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Get the Personnel Details
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(response.getPayload().getChurchCode());

        MemberPersonnelResponse response1 = authApi.loginMemberPersonnel(personnel);

        if(account_name.endsWith("Account")){
            account_name = account_name.substring(0, account_name.lastIndexOf("Account"));
        }

        if(account_name.toLowerCase(Locale.ROOT).contains("account")){
            log.error("Account names should not contain the word 'Account'");
        }

        /*
            if(account_name.contains("Account")){
                account_name = account_name.replace(" Account","");
            }else if(account_name.contains("account")){
                account_name = account_name.replace(" account","");
            }else{
                account_name = account_name;
            }
        */

        CreateLocalChurchAccount localChurchAccount = new CreateLocalChurchAccount();

        // Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Church RPayload
        Payload payload = new Payload();
        payload.setAccountName(account_name);
        payload.setDepartment(department);
        payload.setDuration(duration);
        payload.setPriorityNumber(priority);
        payload.setStatus(status);
        payload.setEditMode(false);
        payload.setNewEntryMode(true);
        payload.setSelectMode(false);
        payload.setExpiryDate("");
        payload.setOrganisationLevel(response1.getPayload().getOrganisationLevel());
        payload.setOrganisationName(response1.getPayload().getOrganisationName());
        payload.setSessionNumber(session_number);
        payload.setOrganisationNumber(response1.getPayload().getOrganisationNumber());
        localChurchAccount.setPayload(payload);

        CreateLocalChurchAccountResponse localChurchAccountResponse = personnelApi.createLocalChurchAccount(localChurchAccount);
        return localChurchAccountResponse;
    }


    // Create Local Church Account
    @GetMapping("/select-account")
    public LocalChurchAccountsSelectResponse selectLocalChurchAccount(@RequestParam("username")String username,
                                                                      @RequestParam("password")String password,
                                                                      @RequestParam("phone_number")String phone_number)  throws JsonProcessingException{


        // Get the Member Profile
        MemberProfile profile = new MemberProfile();

        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(profilepayload);

        //Get the Member Profile Response
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Get the Personnel Details
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(response.getPayload().getChurchCode());

        MemberPersonnelResponse response1 = authApi.loginMemberPersonnel(personnel);
        LocalChurchAccounts localChurchAccount = new LocalChurchAccounts();

        // Church RPayload
        final int session_number1 = (int) ((Math.random() * 9000000) + 1000000);

        com.example.tried.auth.personnel.accounts.EditValues editValues = new
                com.example.tried.auth.personnel.accounts.EditValues();

        editValues.setEditOrganisationLevel(response1.getPayload().getOrganisationLevel());
        editValues.setEditOrganisationName(response1.getPayload().getOrganisationName());
        editValues.setEditOrganisationNumber(response1.getPayload().getOrganisationNumber());
        editValues.setSessionNumber(String.valueOf(session_number1));


        SelectLocalChurchPayload payload = new SelectLocalChurchPayload();
        payload.setSelectMode(true);
        payload.setNewEntryMode(false);
        payload.setEditMode(false);

        localChurchAccount.setPayload(payload);
        localChurchAccount.setEditValues(editValues);

        LocalChurchAccountsSelectResponse selectResponse =
                personnelApi.selectLocalChurchAccounts(localChurchAccount);
        return selectResponse;
    }

    // Update Local Church Account
    @PostMapping("/update-account")
    public UpdateLocalChurchAccountResponse updateLocalChurchAccount(@RequestParam("previous_account_name")String previous_account_name,
                                                                     @RequestParam("account_name")String account_name,
                                                                     @RequestParam("department")String department,
                                                                     @RequestParam("priority")String priority,
                                                                     @RequestParam("duration")String duration,
                                                                     @RequestParam("status")String status,
                                                                     @RequestParam("username")String username,
                                                                     @RequestParam("password")String password,
                                                                     @RequestParam("phone_number")String phone_number)  throws JsonProcessingException{

        // Get the Member Profile
        MemberProfile profile = new MemberProfile();

        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(profilepayload);

        //Get the Member Profile Response
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Get the Personnel Details
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(response.getPayload().getChurchCode());

        MemberPersonnelResponse response1 = authApi.loginMemberPersonnel(personnel);

        UpdateLocalChurchAccount localChurchAccount = new UpdateLocalChurchAccount();

        // Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Church RPayload
        com.example.tried.auth.personnel.accounts.update_account.Payload payload =
                new com.example.tried.auth.personnel.accounts.update_account.Payload();

        EditValues editValues = new EditValues();
        editValues.setEditAccountName(previous_account_name);
        editValues.setEditOrganisationName(response1.getPayload().getOrganisationName());
        editValues.setEditOrganisationLevel(response1.getPayload().getOrganisationLevel());
        editValues.setEditOrganisationNumber(response1.getPayload().getOrganisationNumber());
        localChurchAccount.setEditValues(editValues);

        payload.setAccountName(account_name);
        payload.setDepartment(department);
        payload.setDuration(duration);
        payload.setPriorityNumber(priority);
        payload.setStatus(status);
        payload.setEditMode(true);
        payload.setNewEntryMode(false);
        payload.setSelectMode(false);
        payload.setExpiryDate("");
        payload.setOrganisationLevel(response1.getPayload().getOrganisationLevel());
        payload.setOrganisationName(response1.getPayload().getOrganisationName());
        payload.setSessionNumber(session_number);
        payload.setOrganisationNumber(response1.getPayload().getOrganisationNumber());
        localChurchAccount.setPayload(payload);

        UpdateLocalChurchAccountResponse localChurchAccountResponse = personnelApi.updateLocalChurchAccount(localChurchAccount);
        return localChurchAccountResponse;
    }

    @GetMapping("/trust_fund_transcript")
    public TrustFundTranscriptResponse getTrustFundTranscript() throws JsonProcessingException {
        // Credentials
        String username = "mwakesho";
        String password = "0389";
        String phone_number = "254786439659";

        // Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Profile Information
        MemberProfile memberProfile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);
        memberProfile.setProfilepayload(profilepayload);

        MemberProfileResponse profileResponse = authApi.getMemberDetails(memberProfile);

        System.out.println("Profile Response: " + profileResponse);
        String church_code = profileResponse.getPayload().getChurchCode();

        // Personnel Information
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setChurchCode(church_code);
        personnel.setUser(username);
        personnel.setPassword(password);

        System.out.println("Personnel Data Object: "+ personnel);

        MemberPersonnelResponse responsed = authApi.loginMemberPersonnel(personnel);
        System.out.println("Member Personnel Response: "+ responsed);
        // Authentication
        Authentication authentication = new Authentication();
        authentication.setPersonnelName(responsed.getPayload().getPersonnelName());
        authentication.setInstitutionNumber(responsed.getPayload().getOrganisationNumber());
        authentication.setInstitutionName(responsed.getPayload().getOrganisationName());
        authentication.setInstitutionLevel(responsed.getPayload().getOrganisationLevel());
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setSessionNumber(String.valueOf(session_number));

        // RPayload
        com.example.tried.auth.personnel.reports.transcript.Payload payload =
                new com.example.tried.auth.personnel.reports.transcript.Payload();

        payload.setStartDate("2024-2-1");
        payload.setEndDate("2024-2-29");
        payload.setMeansOfPayment("USSD");
        payload.setChurchCode(responsed.getPayload().getOrganisationNumber());

        // Trust Fund Transcript
        TrustFundTranscript transcript = new TrustFundTranscript();
        transcript.setPayload(payload);
        transcript.setAuthentication(authentication);

        TrustFundTranscriptResponse transcriptResponse = personnelApi.getTrustFundTranscript(transcript);
        System.out.println("Trust Fund Transcript Response: "+ transcriptResponse);
        return transcriptResponse;
    }

    @GetMapping("/tracing")
    public LocalChurchTransactionTracingResponse getLocalTransactionTracing()  throws JsonProcessingException{
        // Credentials
        String username = "mwakesho";
        String password = "0389";
        String phone_number = "254786439659";
        String start_date = "2024-3-1";
        String end_date = "2024-3-31";

        // Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // Profile Information
        MemberProfile memberProfile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);
        memberProfile.setProfilepayload(profilepayload);

        MemberProfileResponse profileResponse = authApi.getMemberDetails(memberProfile);

        System.out.println("Profile Response: " + profileResponse);
        String church_code = profileResponse.getPayload().getChurchCode();

        // Personnel Information
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setChurchCode(church_code);
        personnel.setUser(username);
        personnel.setPassword(password);

        System.out.println("Personnel Data Object: "+ personnel);

        MemberPersonnelResponse responsed = authApi.loginMemberPersonnel(personnel);
        System.out.println("Member Personnel Response: "+ responsed);

        com.example.tried.auth.personnel.tracing.Authentication authentication = new
                com.example.tried.auth.personnel.tracing.Authentication();
        authentication.setSessionNumber(String.valueOf(session_number));
        authentication.setPassword(password);
        authentication.setUser(username);
        authentication.setInstitutionName(responsed.getPayload().getOrganisationName());
        authentication.setInstitutionNumber(responsed.getPayload().getOrganisationNumber());
        authentication.setInstitutionLevel(responsed.getPayload().getOrganisationLevel());
        authentication.setPersonnelName(responsed.getPayload().getPersonnelName());

        TracingPayload payload = new TracingPayload();
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        LocalChurchTransactionTracing tracing = new LocalChurchTransactionTracing();
        tracing.setAuthentication(authentication);
        tracing.setPayload(payload);

        LocalChurchTransactionTracingResponse tracingResponse = authApi.getTransactionTracingSummary(tracing);
        return tracingResponse;
    }


    @PostMapping("/member_receipt_funds")
    public CashReceiptingResponse receiveFunds(@RequestParam("phone_number") String phone_number,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("amount") int amount,
                               @RequestParam("contribute") String contribute,
                               @RequestParam("contribute_type") String contribute_type,
                               @RequestParam("receiver_id") String receiver_id,
                               @RequestParam("receiver_number") String receiver_number,
                               @RequestParam("receiver_name") String receiver_name,
                               @RequestParam(value = "trust_funds[]", required = false) String[] trust_funds,
                               @RequestParam(value = "fund_amount[]", required = false) int[] fund_amount,
                               @RequestParam(value = "non_trust_funds[]", required = false) String[] non_trust_funds,
                               @RequestParam(value = "fund_amount1[]", required = false) int[] fund_amount1,
                               @RequestParam(value = "special_trust_funds[]", required = false) String[] special_trust_funds,
                               @RequestParam(value = "fund_amount2[]", required = false) int[] fund_amount2) throws JsonParseException, JsonProcessingException {

        if(phone_number.contains("+")){
            phone_number = phone_number;
        }else{
            phone_number = String.format("%s%s" ,"+", phone_number);
        }


        if(receiver_number.contains("+")){
            receiver_number = receiver_number;
        }else{
            receiver_number = String.format("%s%s" ,"+", receiver_number);
        }

        // Member Profile Information (Treasure Information)
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber(phone_number);

        // Receipted Member RPayload
        MemberProfile profiler = new MemberProfile();
        profiler.setProfilepayload(profilepayload);

        // Receipted Person Information
        Profilepayload profilepayload1 = new Profilepayload();
        profilepayload1.setFromWithin(true);
        profilepayload1.setMobileNumber(receiver_number);

        // Receipted Member RPayload
        MemberProfile profiler1 = new MemberProfile();
        profiler1.setProfilepayload(profilepayload1);

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        final long session_number1 = (long) ((Math.random() * 900000000) + 100000000);

        // Get Membership Number
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);


        // Request Church Details
        ListLocalChurchPaymentAccounts accounts = new ListLocalChurchPaymentAccounts();
        accounts.setSessionNumber(session_number1);
        accounts.setUser(username);
        accounts.setPassword(password);
        accounts.setChurchCode(profile.getPayload().getChurchCode());
        accounts.setChurchName(profile.getPayload().getChurchName());


        ListLocalChurchPaymentAccountsResponse response = personnelApi.selectChurchPaymentAccounts(accounts);
        String cash = response.getPayload().getCash();
        cash = cash.substring(1, cash.length() - 1);
        String [] cashArray = cash.split(",");
        cashArray[0] = cashArray[0].substring(1,cashArray[0].length() - 1);
        String [] accountInfo = cashArray[0].split("::");
        String Account_Name =  accountInfo[0];
        String [] accountInfo2 = accountInfo[1].split("\\(");
        String Account_Number = accountInfo2[0];

        CashReceipting receipting = new CashReceipting();

        // Receipting Object
        com.example.tried.auth.personnel.receipting.Payload payload =
                new com.example.tried.auth.personnel.receipting.Payload();
        payload.setAccessPoint("Web app");
        payload.setAmount(amount);
        payload.setMSISDN(phone_number);
        payload.setCountryCode("KE");
        payload.setNarration("Cash Received Through Web at "+ profile.getPayload().getChurchName());
        payload.setPaymentMode("Cash");
        payload.setAccountName(Account_Name);
        payload.setAccountNumber(Account_Number);
        payload.setSessionNumber(session_number);
        payload.setCustomerNames(profile.getPayload().getMemberName());
        payload.setDatePaymentReceived(String.valueOf(timestamp));

        ExtraData extraData = new ExtraData();
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

        extraData.setFundDistribution(fundDistribution);

        // Member Details
        Member member = new Member();
        member.setReceiverContactType("Phone Number");
        member.setReceiverId(receiver_id);
        member.setReceiverName(receiver_name);
        member.setReceiverContact(receiver_number);
        member.setChurchCode(profile.getPayload().getChurchCode());
        member.setChurchName(profile.getPayload().getChurchName());
        member.setContributorContact(phone_number);
        member.setContributorType(contribute_type);
        member.setContributorContactType("Phone Number");
        member.setContributorName(profile.getPayload().getMemberName());
        member.setCollectingParty("Cfms Web");
        member.setContributingFor(contribute);
        member.setAccountName(Account_Name);
        member.setAccountNumber(Account_Number);
        member.setContributingAs(contribute_type);

        extraData.setMember(member);
        payload.setExtraData(extraData);
        receipting.setPayload(payload);

        CashReceiptingResponse receiptingResponse = personnelApi.getCashReceipting(receipting);
        return receiptingResponse;
    }


    @PostMapping("/member_receipt_funds_guest")
    public CashReceiptingResponse receiveFundsGuest(@RequestParam("phone_number") String phone_number,
                                               @RequestParam("username") String username,
                                               @RequestParam("password") String password,
                                               @RequestParam("amount") int amount,
                                               @RequestParam("contribute") String contribute,
                                               @RequestParam("contribute_type") String contribute_type,
                                               @RequestParam("receiver_id") String receiver_id,
                                               @RequestParam("receiver_number") String receiver_number,
                                               @RequestParam("receiver_name") String receiver_name,
                                               @RequestParam(value = "trust_funds[]", required = false) String[] trust_funds,
                                               @RequestParam(value = "fund_amount[]", required = false) int[] fund_amount,
                                               @RequestParam(value = "non_trust_funds[]", required = false) String[] non_trust_funds,
                                               @RequestParam(value = "fund_amount1[]", required = false) int[] fund_amount1,
                                               @RequestParam(value = "special_trust_funds[]", required = false) String[] special_trust_funds,
                                               @RequestParam(value = "fund_amount2[]", required = false) int[] fund_amount2) throws JsonParseException, JsonProcessingException {
        if(phone_number.contains("+")){
            phone_number = phone_number;
        }else{
            phone_number = String.format("%s%s" ,"+", phone_number);
        }


        if(receiver_number.contains("+")){
            receiver_number = receiver_number;
        }else{
            receiver_number = String.format("%s%s" ,"+", receiver_number);
        }


        // Member Profile Information (Treasure Information)
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber(phone_number);

        // Receipted Member RPayload
        MemberProfile profiler = new MemberProfile();
        profiler.setProfilepayload(profilepayload);

        // Receipted Person Information
        Profilepayload profilepayload1 = new Profilepayload();
        profilepayload1.setFromWithin(true);
        profilepayload1.setMobileNumber(receiver_number);

        // Receipted Member RPayload
        MemberProfile profiler1 = new MemberProfile();
        profiler1.setProfilepayload(profilepayload1);

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        final long session_number1 = (long) ((Math.random() * 900000000) + 100000000);

        // Get Membership Number
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);


        // Request Church Details
        ListLocalChurchPaymentAccounts accounts = new ListLocalChurchPaymentAccounts();
        accounts.setSessionNumber(session_number1);
        accounts.setUser(username);
        accounts.setPassword(password);
        accounts.setChurchCode(profile.getPayload().getChurchCode());
        accounts.setChurchName(profile.getPayload().getChurchName());


        ListLocalChurchPaymentAccountsResponse response = personnelApi.selectChurchPaymentAccounts(accounts);
        String cash = response.getPayload().getCash();
        cash = cash.substring(1, cash.length() - 1);
        String [] cashArray = cash.split(",");
        cashArray[0] = cashArray[0].substring(1,cashArray[0].length() - 1);
        String [] accountInfo = cashArray[0].split("::");
        String Account_Name =  accountInfo[0];
        String [] accountInfo2 = accountInfo[1].split("\\(");
        String Account_Number = accountInfo2[0];

        CashReceipting receipting = new CashReceipting();

        // Receipting Object
        com.example.tried.auth.personnel.receipting.Payload payload =
                new com.example.tried.auth.personnel.receipting.Payload();
        payload.setAccessPoint("Web app");
        payload.setAmount(amount);
        payload.setMSISDN(phone_number);
        payload.setCountryCode("KE");
        payload.setNarration("Cash Received Through Web at "+ profile.getPayload().getChurchName());
        payload.setPaymentMode("Cash");
        payload.setAccountName(Account_Name);
        payload.setAccountNumber(Account_Number);
        payload.setSessionNumber(session_number);
        payload.setCustomerNames(profile.getPayload().getMemberName());
        payload.setDatePaymentReceived(String.valueOf(timestamp));

        ExtraData extraData = new ExtraData();
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

        extraData.setFundDistribution(fundDistribution);

        // Member Details
        Member member = new Member();
        member.setReceiverContactType("Phone Number");
        member.setReceiverId(receiver_id);
        member.setReceiverName(receiver_name);
        member.setReceiverContact(receiver_number);
        member.setChurchCode(profile.getPayload().getChurchCode());
        member.setChurchName(profile.getPayload().getChurchName());
        member.setContributorContact(phone_number);
        member.setContributorType(contribute_type);
        member.setContributorContactType("Phone Number");
        member.setContributorName(profile.getPayload().getMemberName());
        member.setCollectingParty("Cfms Web");
        member.setContributingFor(contribute);
        member.setAccountName(Account_Name);
        member.setAccountNumber(Account_Number);
        member.setContributingAs(contribute_type);

        extraData.setMember(member);
        payload.setExtraData(extraData);
        receipting.setPayload(payload);

        CashReceiptingResponse receiptingResponse = personnelApi.getCashReceipting(receipting);
        return receiptingResponse;
    }

    @GetMapping("/accounts")
    public ListLocalChurchPaymentAccountsResponse getPaymentAccounts() throws JsonProcessingException {
        String phone_number = "254797705390";
        String username = "cmk";
        String password = "cfms2024";

        final long session_number = (long) ((Math.random() * 900000000) + 100000000);

        // Profile RPayload
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);

        // Member Profile
        MemberProfile profile = new MemberProfile();
        profile.setProfilepayload(profilepayload);
        MemberProfileResponse profileResponse = authApi.getMemberDetails(profile);

        ListLocalChurchPaymentAccounts accounts = new ListLocalChurchPaymentAccounts();
        accounts.setSessionNumber(session_number);
        accounts.setUser(username);
        accounts.setPassword(password);
        accounts.setChurchCode(profileResponse.getPayload().getChurchCode());
        accounts.setChurchName(profileResponse.getPayload().getChurchName());


        ListLocalChurchPaymentAccountsResponse response = personnelApi.selectChurchPaymentAccounts(accounts);
        System.out.println("Response: "+ response);
        String cash = response.getPayload().getCash();
        System.out.println("Raw: "+ cash);
        cash = cash.substring(1, cash.length()-1);
        System.out.println("With Out Curly Brackets: "+cash);
        String [] cashArray = cash.split(",");
        System.out.println("Split array:"+ cashArray);
        System.out.println("Account Details:"+ cashArray[0]);
        cashArray[0] = cashArray[0].substring(1,cashArray[0].length()-1);
        System.out.println("Account Details Stripped:"+ cashArray[0]);
        String [] accountInfo = cashArray[0].split("::");
        System.out.println("Account Name:"+ accountInfo[0]);
        System.out.println("Account Number:"+ accountInfo[1]);
        String [] accountInfo2 = accountInfo[1].split("\\(");
        System.out.println("Account Number Alone:"+ accountInfo2[0]);
        return response;
    }


    @PostMapping(path="/check-accounts")
    public RequestChurchDetailsResponse getMemberChurchAccounts(@RequestParam("phone_number")String phoneNumber) throws JsonProcessingException {

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

        List<String> account_names = new ArrayList<String>();
        List<String> account_number = new ArrayList<String>();

        RequestChurchDetailsResponse churchDetails = authApi.getMemberChurchDetails(requestCode);
        System.out.println("Response: "+ churchDetails.getDepartmentalAccounts());
        String cash = churchDetails.getDepartmentalAccounts();
        String cash1 = churchDetails.getTrustFundAccounts();
        String cash2 = churchDetails.getSpecialTrustFundAccounts();
        System.out.println("Raw: "+ cash);
        cash = cash.substring(1, cash.length()-1);
        cash1 = cash1.substring(1, cash1.length()-1);
        cash2 = cash2.substring(1, cash2.length()-1);
        System.out.println("With Out Curly Brackets: "+cash);
        String[] cashArray = cash.split(",");
        String[] cashArray1 = cash1.split(",");
        String[] cashArray2 = cash2.split(",");
        System.out.println("Split array:"+ cashArray);

        // Non Trust Fund Accounts
        for (int i = 0; i < cashArray.length; i++){
            cashArray[i] = cashArray[i].substring(1,cashArray[i].length()-1);
            String[]  accountInfo = cashArray[i].split("::");
            account_names.add(accountInfo[0]);
            account_number.add(accountInfo[1]);
        }

        // Trust Fund Accounts
        for (int i = 0; i < cashArray1.length; i++){
            cashArray1[i] = cashArray1[i].substring(1,cashArray1[i].length()-1);
            String[]  accountInfo1 = cashArray1[i].split("::");
            account_names.add(accountInfo1[0]);
            account_number.add(accountInfo1[1]);
        }

        // Special Trust Fund Accounts
        for (int i = 0; i < cashArray2.length; i++){
            cashArray2[i] = cashArray2[i].substring(1,cashArray2[i].length()-1);
            String[]  accountInfo2 = cashArray2[i].split("::");
            account_names.add(accountInfo2[0]);
            account_number.add(accountInfo2[1]);
        }

        System.out.println("Account Names: "+ account_names);
        System.out.println("Account Details:"+ account_number);

        System.out.println("Account Names List Size: "+ account_names.size());
        System.out.println("Account Details List Size:"+ account_number.size());


        for(int i=0; i<account_names.size(); i++){
            System.out.println("Account: "+ account_names.get(i) + " - " + account_number.get(i));
        }

        return churchDetails;
    }

    @GetMapping("/export/local_church_offering_report/excel")
    public void getLocalChurchOfferingReport(@RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("phone_number") String phone_number,
                                             @RequestParam("start_date") String start_date,
                                             @RequestParam("end_date") String end_date,
                                             @RequestParam("means") String means_of_payment,
                                             HttpServletResponse Outresponse) throws IOException {

        // Member Profile
        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);
        profile.setProfilepayload(profilepayload);

        // Member Profile Response
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Member Personnel RPayload
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setChurchCode(response.getPayload().getChurchCode());
        personnel.setUser(username);
        personnel.setPassword(password);

        // Member Personnel Response
        MemberPersonnelResponse response1 = authApi.loginMemberPersonnel(personnel);

        LocalChurchOfferingSummary localChurchOfferingSummary = new LocalChurchOfferingSummary();

        // Session Number
        final int session_number = (int) ((Math.random() * 900000000) + 100000000);

        com.example.tried.auth.personnel.reports.offering.Authentication authentication = new
                com.example.tried.auth.personnel.reports.offering.Authentication();

        // Authentication
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setPersonnelName(response1.getPayload().getPersonnelName());
        authentication.setInstututionName(response1.getPayload().getOrganisationName());
        authentication.setInstututionLevel(response1.getPayload().getOrganisationLevel());
        authentication.setInstututionNumber(response1.getPayload().getOrganisationNumber());
        authentication.setSessionNumber(session_number);

        // Local Church RPayload
        LocalChurchPayload localChurchPayload = new LocalChurchPayload();
        localChurchPayload.setChurchName(response1.getPayload().getOrganisationName());
        localChurchPayload.setEndDate(end_date);
        localChurchPayload.setStartDate(start_date);
        localChurchPayload.setMeansOfPayment(means_of_payment);
        localChurchPayload.setChurchCode(response1.getPayload().getOrganisationNumber());
        localChurchPayload.setGroup("Not Applicable");

        // Initialization RPayload
        localChurchOfferingSummary.setAuthentication(authentication);
        localChurchOfferingSummary.setPayload(localChurchPayload);

        Outresponse.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=local_church_offering_report-" + start_date +" - "+ end_date  + ".xlsx";
        Outresponse.setHeader(headerKey, headerValue);

        LocalChurchOfferingSummaryResponse responsed = authApi.getLocalChurchOfferingReports(localChurchOfferingSummary);
        LocalChurchOfferingReportExcel localChurchOfferingResponseExcel = new LocalChurchOfferingReportExcel();
        localChurchOfferingResponseExcel.export(Outresponse, responsed);
    }


    @GetMapping("/export/local_church_offering_report/pdf")
    public void getLocalChurchOfferingReportPDF(@RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("phone_number") String phone_number,
                                             @RequestParam("start_date") String start_date,
                                             @RequestParam("end_date") String end_date,
                                             @RequestParam("means") String means_of_payment,
                                             HttpServletResponse Outresponse) throws IOException {

        // Member Profile
        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);
        profile.setProfilepayload(profilepayload);

        // Member Profile Response
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Member Personnel RPayload
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setChurchCode(response.getPayload().getChurchCode());
        personnel.setUser(username);
        personnel.setPassword(password);

        // Member Personnel Response
        MemberPersonnelResponse response1 = authApi.loginMemberPersonnel(personnel);

        LocalChurchOfferingSummary localChurchOfferingSummary = new LocalChurchOfferingSummary();

        // Session Number
        final int session_number = (int) ((Math.random() * 900000000) + 100000000);

        com.example.tried.auth.personnel.reports.offering.Authentication authentication = new
                com.example.tried.auth.personnel.reports.offering.Authentication();

        // Authentication
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setPersonnelName(response1.getPayload().getPersonnelName());
        authentication.setInstututionName(response1.getPayload().getOrganisationName());
        authentication.setInstututionLevel(response1.getPayload().getOrganisationLevel());
        authentication.setInstututionNumber(response1.getPayload().getOrganisationNumber());
        authentication.setSessionNumber(session_number);

        // Local Church RPayload
        LocalChurchPayload localChurchPayload = new LocalChurchPayload();
        localChurchPayload.setChurchName(response1.getPayload().getOrganisationName());
        localChurchPayload.setEndDate(end_date);
        localChurchPayload.setStartDate(start_date);
        localChurchPayload.setMeansOfPayment(means_of_payment);
        localChurchPayload.setChurchCode(response1.getPayload().getOrganisationNumber());
        localChurchPayload.setGroup("Not Applicable");

        // Initialization RPayload
        localChurchOfferingSummary.setAuthentication(authentication);
        localChurchOfferingSummary.setPayload(localChurchPayload);

        Outresponse.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=local_church_offering_report-" + start_date +" - "+ end_date  + ".pdf";
        Outresponse.setHeader(headerKey, headerValue);

        LocalChurchOfferingSummaryResponse responsed = authApi.getLocalChurchOfferingReports(localChurchOfferingSummary);
        localChurchOfferingReport.LocalChurchSummaryReport(Outresponse,phone_number,start_date,end_date,responsed);
    }


    @GetMapping("/select-account-json")
    public HashMap<String, Object> selectLocalChurchAccounts() throws IOException {

        String phone_number = "254786439659";
        String username = "mwakesho";
        String password = "0389";

        // Get the Member Profile
        MemberProfile profile = new MemberProfile();

        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(profilepayload);

        //Get the Member Profile Response
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Get the Personnel Details
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(response.getPayload().getChurchCode());

        MemberPersonnelResponse response1 = authApi.loginMemberPersonnel(personnel);
        LocalChurchAccounts localChurchAccount = new LocalChurchAccounts();

        // Church RPayload
        final int session_number1 = (int) ((Math.random() * 9000000) + 1000000);


        HashMap<String,String> authentication = new HashMap<String,String>();
        // Authentication
        authentication.put("userName","Dn0guQtrJc9j0nbF7y8evw==");
        authentication.put("password","7Zu2pBUFgsaTOuMOvfqNpg==");

        // Edit Values
        HashMap<String,String> editValues = new HashMap<String,String>();
        editValues.put("editOrganisationLevel",response1.getPayload().getOrganisationLevel());
        editValues.put("editOrganisationName",response1.getPayload().getOrganisationName());
        editValues.put("editOrganisationNumber",response1.getPayload().getOrganisationNumber());
        editValues.put("sessionNumber",String.valueOf(session_number1));

        // RPayload
        HashMap<String,Boolean> payload = new HashMap<String,Boolean>();
        payload.put("newEntryMode",false);
        payload.put("editMode",false);
        payload.put("selectMode",true);

        // Main Object
        HashMap<String,Object> mainObject = new HashMap<String,Object>();
        mainObject.put("payload", payload);
        mainObject.put("editValues",editValues);
        mainObject.put("authentication",authentication);
        mainObject.put("function", "createLocalChurchAccount");


        HashMap<String, Object> result = personnelApi.getLocalChurchAccounts(mainObject);
        HashMap<String, Object> result1 = (HashMap<String, Object>) result.get("payload");
        HashMap<String, Object> result2 = (HashMap<String, Object>) result1.get("accountList");
        HashMap<String, Object> result4 = new HashMap<String, Object>();

        List<HashMap<String, Object>> accounts = new ArrayList<HashMap<String, Object>>();
        for ( Map.Entry<String,Object> mapElement : result2.entrySet() ) {
            String key = mapElement.getKey();
            result4 = (HashMap<String, Object>) mapElement.getValue();
            result4.put("account_number", key);
            accounts.add(result4);
        }


        for(HashMap<String, Object> mapElement : accounts){
            String organisationName = String.valueOf(mapElement.get("accountName"));
            String priority = String.valueOf(mapElement.get("priority_number"));
            String account_number = String.valueOf(mapElement.get("account_number"));

            System.out.println("Account Number: "+ account_number);
            System.out.println("Organization Name: " + organisationName);
            System.out.println("Priority Level: "+ priority);
        }

        return result;
    }

    @PostMapping("/department-accounts")
    public DepartmentResponse getDepartments(@RequestParam("phone_number") String phone_number,
                                             @RequestParam("username") String username,
                                             @RequestParam("password") String password) throws JsonProcessingException {

        // Member Profile
        MemberProfile profile = new MemberProfile();

        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(profilepayload);

        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        MemberProfileResponse profiler = authApi.getMemberDetails(profile);
        // Member Personnel Response

        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setChurchCode(profiler.getPayload().getChurchCode());
        personnel.setPassword(password);
        personnel.setUser(username);

        // Personnel Response
        MemberPersonnelResponse personnelResponse = authApi.loginMemberPersonnel(personnel);

        DepartmentRequest request = new DepartmentRequest();
        com.example.tried.auth.personnel.department.Authentication authentication = new
                com.example.tried.auth.personnel.department.Authentication();

        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setSessionNumber(rand);
        authentication.setInstututionName(personnelResponse.getPayload().getOrganisationName());
        authentication.setInstututionNumber(personnelResponse.getPayload().getOrganisationNumber());
        authentication.setInstututionLevel(personnelResponse.getPayload().getOrganisationLevel());
        authentication.setPersonnelName(personnelResponse.getPayload().getPersonnelName());
        request.setAuthentication(authentication);

        DepartmentResponse departmentResponse = personnelApi.getDepartmentAccounts(request);
        return departmentResponse;
    }

    @GetMapping("/local_church_off_statement")
    public String getLocalChurchOfferingStatement(@RequestParam("phone_number") String phone_number,
                                                                               @RequestParam("start_date") String start_date,
                                                                               @RequestParam("end_date") String end_date,
                                                                               @RequestParam("username") String username,
                                                                               @RequestParam("password") String password,
                                                                               @RequestParam("pin") String pin, HttpServletResponse response) throws IOException, JsonProcessingException {
        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        if(phone_number.startsWith("+")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

        // Get the Member Authentication Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(payload);

        MemberProfileResponse profile2 = authApi.getMemberDetails(profile);

        // Authentication Information
        com.example.tried.auth.reports.statements.Authentication authentication = new
                com.example.tried.auth.reports.statements.Authentication();
        authentication.setPhoneNumber(phone_number);
        authentication.setInstitutionName(profile2.getPayload().getChurchName());
        authentication.setInstitutionLevel("LOCAL CHURCH");
        authentication.setPersonnelName(profile2.getPayload().getMemberName());
        authentication.setSessionNumber(String.valueOf(session_number));
        authentication.setInstitutionNumber(profile2.getPayload().getChurchCode());
        authentication.setPin(pin);
        authentication.setUser(username);
        authentication.setPassword(password);

        // Offering RPayload
        com.example.tried.auth.reports.statements.Payload payload2 = new com.example.tried.auth.reports.statements.Payload();
        payload2.setChurchName(profile2.getPayload().getChurchName());
        payload2.setChurchCode(profile2.getPayload().getChurchCode());
        payload2.setStartDate(start_date);
        payload2.setEndDate(end_date);

        // Get the Member Offering RPayload
        LocalChurchOfferingStatement statement = new LocalChurchOfferingStatement();
        statement.setAuthentication(authentication);
        statement.setPayload(payload2);

        localChurchStatementService.createOfferingStatement(statement,response);
        return "Generated Local Church Statement";
    }


    @PostMapping("/local_church_offering_statement")
    public LocalChurchOfferingStatementResponse getLocalChurchOfferingResponse(@RequestParam("phone_number") String phone_number,
                                                                               @RequestParam("start_date") String start_date,
                                                                               @RequestParam("end_date") String end_date,
                                                                               @RequestParam("username") String username,
                                                                               @RequestParam("password") String password,
                                                                               @RequestParam("pin") String pin) throws JsonProcessingException{
        System.out.println("Get the Phone Number: " + phone_number);

        if(phone_number.startsWith("+")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

        System.out.println("Get the Phone Number: " + phone_number);

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);


        // Get the Member Authentication Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(payload);
        MemberProfileResponse profile2 = authApi.getMemberDetails(profile);

        // Authentication Information
        com.example.tried.auth.reports.statements.Authentication authentication = new
                com.example.tried.auth.reports.statements.Authentication();
        authentication.setPhoneNumber(phone_number);
        authentication.setInstitutionName(profile2.getPayload().getChurchName());
        authentication.setInstitutionLevel("LOCAL CHURCH");
        authentication.setPersonnelName(profile2.getPayload().getMemberName());
        authentication.setSessionNumber(String.valueOf(session_number));
        authentication.setInstitutionNumber(profile2.getPayload().getChurchCode());
        authentication.setPin(pin);
        authentication.setUser(username);
        authentication.setPassword(password);

        // Offering RPayload
        com.example.tried.auth.reports.statements.Payload payload2 = new com.example.tried.auth.reports.statements.Payload();
        payload2.setChurchName(profile2.getPayload().getChurchName());
        payload2.setChurchCode(profile2.getPayload().getChurchCode());
        payload2.setStartDate(start_date);
        payload2.setEndDate(end_date);

        // Get the Member Offering RPayload
        LocalChurchOfferingStatement statement = new LocalChurchOfferingStatement();
        statement.setAuthentication(authentication);
        statement.setPayload(payload2);

        return personnelApi.getLocalChurchOfferingStatement(statement);
    }

    @PostMapping("/select-trust-funds")
    public SelectTrustFundsResponse selectTrustFundsforTransfer(@RequestParam("start_date") String start_date,
                                                                @RequestParam("end_date") String end_date,
                                                                @RequestParam("account_name") String account_name,
                                                                @RequestParam("account_number") String account_number,
                                                                @RequestParam("username") String username,
                                                                @RequestParam("password") String password,
                                                                @RequestParam("phone_number") String phone_number) throws JsonProcessingException{

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);


        // Get the Member Authentication Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(payload);
        MemberProfileResponse profile2 = authApi.getMemberDetails(profile);

        com.example.tried.auth.funds.Authentication authentication = new
                com.example.tried.auth.funds.Authentication();
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setSessionNumber(session_number);
        authentication.setPersonnelName(profile2.getPayload().getMemberName());
        authentication.setInstututionNumber(profile2.getPayload().getChurchCode());
        authentication.setInstututionName(profile2.getPayload().getChurchName());
        authentication.setInstututionLevel("LOCAL CHURCH");

        com.example.tried.auth.funds.Payload payload1 = new com.example.tried.auth.funds.Payload();
        payload1.setAccountNumber(account_number);
        payload1.setAccountName(account_name);
        payload1.setStartDate(start_date);
        payload1.setEndDate(end_date);
        payload1.setChurchName(profile2.getPayload().getChurchName());
        payload1.setChurchCode(profile2.getPayload().getChurchCode());

        SelectTrustFunds selectTrustFunds = new SelectTrustFunds();
        selectTrustFunds.setAuthentication(authentication);
        selectTrustFunds.setPayload(payload1);

        SelectTrustFundsResponse selectTrustFundsResponse = personnelApi.getTrustFundsforTransfer(selectTrustFunds);
        return selectTrustFundsResponse;
    }


    @PostMapping("/transaction_tracing_phone")
    public TransactionTracingMemberResponse getTransactionTracingByPhone(@RequestParam("start_date") String start_date,
                                                                              @RequestParam("end_date") String end_date,
                                                                              @RequestParam("username") String username,
                                                                              @RequestParam("password") String password,
                                                                              @RequestParam("phone_number") String phone_number,
                                                                         @RequestParam("associated_phone_number") String associated_phone_number
    ) throws JsonProcessingException {
    if(phone_number.startsWith("+254")){
        phone_number = phone_number.substring(1, phone_number.length());
    }


    // Generate Session Number
    final int session_number = (int) ((Math.random() * 9000000) + 1000000);


    // Get the Member Authentication Details
    MemberProfile profile = new MemberProfile();
    Profilepayload payload = new Profilepayload();
    payload.setFromWithin(true);
    payload.setMobileNumber("+" + phone_number);
    profile.setProfilepayload(payload);
    MemberProfileResponse profile2 = authApi.getMemberDetails(profile);

    // Member Personnel Response
    MemberPersonnel personnel = new MemberPersonnel();
    personnel.setUser(username);
    personnel.setPassword(password);
    personnel.setChurchCode(profile2.getPayload().getChurchCode());

    MemberPersonnelResponse personnelResponse = authApi.loginMemberPersonnel(personnel);

    TransactionTracingMember tracingMemberPhone = new TransactionTracingMember();

    com.example.tried.auth.tracing.Authentication authentication = new com.example.tried.auth.tracing.Authentication();

    authentication.setPersonnelName(personnelResponse.getPayload().getPersonnelName());
    authentication.setInstututionName(personnelResponse.getPayload().getOrganisationName());
    authentication.setInstututionLevel("LOCAL CHURCH");
    authentication.setUser(username);
    authentication.setPassword(password);
    authentication.setInstututionNumber(personnelResponse.getPayload().getOrganisationNumber());
    authentication.setSessionNumber(session_number);

    com.example.tried.auth.tracing.Payload payload1 = new com.example.tried.auth.tracing.Payload();
    payload1.setPhoneNumber(associated_phone_number);
    payload1.setStartDate(start_date);
    payload1.setEndDate(end_date);


    tracingMemberPhone.setAuthentication(authentication);
    tracingMemberPhone.setPayload(payload1);

    System.out.println("Tracing Member Phone Number: "+ tracingMemberPhone);

    TransactionTracingMemberResponse response = personnelApi.getTransactionTracingByPhone(tracingMemberPhone);
    System.out.println("Response:"+ response);
    return response;
    }


    @PostMapping("/transaction_tracing_receipt")
    public TransactionTracingMemberReceiptResponse getTransactionTracingByReceipt(@RequestParam("start_date") String start_date,
                                                                                  @RequestParam("end_date") String end_date,
                                                                                  @RequestParam("username") String username,
                                                                                  @RequestParam("password") String password,
                                                                                  @RequestParam("phone_number") String phone_number,
                                                                                  @RequestParam("associated_receipt_number") String associated_receipt_number) throws JsonProcessingException {

        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1, phone_number.length());
        }

        // Generate Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);


        // Get the Member Authentication Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(payload);
        MemberProfileResponse profile2 = authApi.getMemberDetails(profile);

        // Member Personnel Response
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(profile2.getPayload().getChurchCode());

        MemberPersonnelResponse personnelResponse = authApi.loginMemberPersonnel(personnel);

        TransactionTracingMemberReceipt tracingMemberReceipt = new TransactionTracingMemberReceipt();

        com.example.tried.auth.tracing.receipt.Authentication authentication = new com.example.tried.auth.tracing.receipt.Authentication();

        authentication.setPersonnelName(personnelResponse.getPayload().getPersonnelName());
        authentication.setInstitutionName(personnelResponse.getPayload().getOrganisationName());
        authentication.setInstitutionLevel("LOCAL CHURCH");
        authentication.setUser(username);
        authentication.setPassword(password);
        authentication.setInstitutionNumber(personnelResponse.getPayload().getOrganisationNumber());
        authentication.setSessionNumber(session_number);
        com.example.tried.auth.tracing.receipt.Payload payload1 = new com.example.tried.auth.tracing.receipt.Payload();
        payload1.setTransactionId(associated_receipt_number);
        payload1.setStartDate(start_date);
        payload1.setEndDate(end_date);


        tracingMemberReceipt.setAuthentication(authentication);
        tracingMemberReceipt.setPayload(payload1);

        TransactionTracingMemberReceiptResponse response = personnelApi.getTransactionTracingByReceipt(tracingMemberReceipt);
        return response;
    }

    @GetMapping("/testing")
    public String TestedPhoneNumber(){
        String receiver_number = "+254707981971";
        if(receiver_number.contains("+")){
            receiver_number = receiver_number;
        }else{
            receiver_number = String.format("%s%s" ,"+", receiver_number);
        }
        return receiver_number;
    }


    @GetMapping("/cash-receipts")
    public HashMap<String, Object> getReceiptedTransactions(@RequestParam("phone_number") String phone_number,
                                                            @RequestParam("start_date") String start_date,
                                                            @RequestParam("end_date") String end_date) throws JsonProcessingException {
        if(phone_number.contains("+")){
            phone_number = phone_number;
        }else{
            phone_number = String.format("%s%s", "+", phone_number);
        }

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber(phone_number);

        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);

        SelectCashReceipting transaction = new SelectCashReceipting();
        transaction.setStartDate(start_date);
        transaction.setEndDate(end_date);
        transaction.setReceiverId(response1.getPayload().getMembershipNumber());
        transaction.setChurchCode(response1.getPayload().getChurchCode());
        transaction.setTypeOfPayment("Cash");

        HashMap<String, Object> hashMap = null;
        try {
            hashMap = personnelApi.getCashReceiptingTransactions(transaction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(hashMap.isEmpty()){
            System.out.println("HashMap is Empty");
        }else{
            System.out.println("HashMapped: " + hashMap);
        }
        return hashMap;
    }

    @PostMapping("/get-member-statement")
    public MemberOfferingResponse getSpecificOffering(@RequestParam("start_date")String start_date,@RequestParam("end_date") String end_date,
                                                      @RequestParam("member_name") String member_name, @RequestParam("member_number") String member_number,
                                                      @RequestParam("treasurer_pin") String treasurer_pin, @RequestParam("phone_number") String phone_number,
                                                      @RequestParam("username") String username, @RequestParam("password") String password) throws JsonProcessingException {

        if(phone_number.startsWith("+")){
            phone_number = phone_number.substring(1,phone_number.length());
        }
        // Member Profile Details
        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);
        profile.setProfilepayload(profilepayload);

        MemberProfileResponse responser = authApi.getMemberDetails(profile);

        if(member_number.startsWith("+")) {
            member_number = member_number.substring(1, member_number.length());
        }

        // Church Member Profile Details
        MemberProfile profile_church = new MemberProfile();
        Profilepayload profilepayload_church = new Profilepayload();
        profilepayload_church.setMobileNumber("+" + member_number);
        profilepayload_church.setFromWithin(true);
        profile_church.setProfilepayload(profilepayload_church);

        MemberProfileResponse response_church = authApi.getMemberDetails(profile_church);

        // Member Personnel Details
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setChurchCode(responser.getPayload().getChurchCode());
        personnel.setUser(username);
        personnel.setPassword(password);

        MemberPersonnelResponse responsed = authApi.loginMemberPersonnel(personnel);

        // Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberOffering offering = new MemberOffering();
        OfferingAuthentication authentication = new OfferingAuthentication();
        authentication.setPassword(password);
        authentication.setUser(username);
        authentication.setPin(treasurer_pin);
        authentication.setPersonnelName(responsed.getPayload().getPersonnelName());
        authentication.setPhoneNumber(phone_number);
        authentication.setSessionNumber(session_number);
        authentication.setInstitutionName(responser.getPayload().getChurchName());
        authentication.setInstitutionNumber(responser.getPayload().getChurchCode());
        authentication.setInstitutionLevel("LOCAL CHURCH");

        OfferingPayload payload = new OfferingPayload();
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);
        payload.setNumberOfTries(1);
        payload.setMemberName(member_name);
        payload.setMemberNumber(response_church.getPayload().getMembershipNumber());

        offering.setAuthentication(authentication);
        offering.setPayload(payload);

        MemberOfferingResponse response = authApi.getMemberOffering(offering);
        return response;
    }



    @GetMapping("/get-member-statement-pdf")
    public String getSpecificOfferingPDF(@RequestParam("start_date_member")String start_date,@RequestParam("end_date_member") String end_date,
                                                      @RequestParam("member_name") String member_name, @RequestParam("member_number") String member_number,
                                                      @RequestParam("treasurer_pin") String treasurer_pin, @RequestParam("phone_number") String phone_number,
                                                      @RequestParam("username") String username, @RequestParam("password") String password,HttpServletResponse response) throws IOException {

        if(phone_number.startsWith("+")){
            phone_number = phone_number.substring(1,phone_number.length());
        }


        // Member Profile Details
        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);
        profile.setProfilepayload(profilepayload);

        MemberProfileResponse responser = authApi.getMemberDetails(profile);

        if(member_number.startsWith("+")) {
            member_number = member_number.substring(1, member_number.length());
        }

        // Church Member Profile Details
        MemberProfile profile_church = new MemberProfile();
        Profilepayload profilepayload_church = new Profilepayload();
        profilepayload_church.setMobileNumber("+" + member_number);
        profilepayload_church.setFromWithin(true);
        profile_church.setProfilepayload(profilepayload_church);

        MemberProfileResponse response_church = authApi.getMemberDetails(profile_church);

        // Personnel Details
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(response_church.getPayload().getChurchCode());

        MemberPersonnelResponse personnelResponse = authApi.loginMemberPersonnel(personnel);

        // Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberOffering offering = new MemberOffering();
        OfferingAuthentication authentication = new OfferingAuthentication();
        authentication.setPassword(password);
        authentication.setUser(username);
        authentication.setPin(treasurer_pin);
        authentication.setPersonnelName(personnelResponse.getPayload().getPersonnelName());
        authentication.setPhoneNumber(phone_number);
        authentication.setSessionNumber(session_number);
        authentication.setInstitutionName(responser.getPayload().getChurchName());
        authentication.setInstitutionNumber(responser.getPayload().getChurchCode());
        authentication.setInstitutionLevel("LOCAL CHURCH");

        String membershipNumber = response_church.getPayload().getMembershipNumber();

        OfferingPayload payload = new OfferingPayload();
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);
        payload.setNumberOfTries(1);
        payload.setMemberName(member_name);
        payload.setMemberNumber(response_church.getPayload().getMembershipNumber());

        offering.setAuthentication(authentication);
        offering.setPayload(payload);

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ membershipNumber + " -" + start_date +" - "+ end_date + ".pdf";
        response.setHeader(headerKey, headerValue);

        offeringStatementService.createOfferingStatement(offering, response);
        return "Get Member Specific Statement";
    }

    @GetMapping("/local_church_accounts")
    public String getLocalChurchPaymentAccounts(@RequestParam("username") String username, @RequestParam("password") String password,
                                                @RequestParam("phone_number") String phone_number) throws JsonProcessingException {
        final long session_number1 = (long) ((Math.random() * 900000000) + 100000000);

        if(phone_number.startsWith("+")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(profilepayload);

        // Get Membership Number
        MemberProfileResponse profiler = authApi.getMemberDetails(profile);

        // Request Church Details
        ListLocalChurchPaymentAccounts accounts = new ListLocalChurchPaymentAccounts();
        accounts.setSessionNumber(session_number1);
        accounts.setUser(username);
        accounts.setPassword(password);
        accounts.setChurchCode(profiler.getPayload().getChurchCode());
        accounts.setChurchName(profiler.getPayload().getChurchName());


        ListLocalChurchPaymentAccountsResponse response = personnelApi.selectChurchPaymentAccounts(accounts);

        // Cash
        String cash = response.getPayload().getCash();
        System.out.println("Cash: "+ cash);
        cash = cash.substring(1, cash.length() - 1);
        System.out.println("Cash 1: "+ cash);
        String [] cashArray = cash.split(",");
        for(int i = 0;i < cashArray.length;i++) {
            cashArray[i] = cashArray[i].substring(1, cashArray[i].length() - 1);
            System.out.println("Cash Array "+i+": "+ cashArray[i]);
        }

        // Bank Accounts
        String banks = response.getPayload().getBankDeposits();
        System.out.println("Banks: "+ banks);
        banks = banks.substring(1, banks.length() - 1);
        System.out.println("Banks 1: "+ banks);
        String [] BankArray = banks.split(",");
        for(int i = 0;i < BankArray.length;i++) {
            BankArray[i] = BankArray[i].substring(1, BankArray[i].length() - 1);
            System.out.println("Bank Array "+i+": "+ BankArray[i]);
        }

        List<String> cashList = Arrays.asList(cashArray);
        List<String> bankList = Arrays.asList(BankArray);
        return "Payment Accounts Gotten";
    }

    @PostMapping("/member_receive_funds_conference")
    public MobileReceiveFundsTransferResponse getFundsTransferToConference(
            @RequestParam("start_date") String start_date, @RequestParam("account_name") String account_name, @RequestParam("account_number") String account_number,
            @RequestParam("end_date") String end_date,@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phone_number") String phone_number,
            @RequestParam("total_amount") String total_amount,@RequestParam(value = "trust_funds[]", required = false) String[] trust_funds,
            @RequestParam(value = "fund_amount[]", required = false) int[] fund_amount) throws JsonProcessingException {

        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        if(phone_number.startsWith("+254")) {
            phone_number = phone_number.substring(1, phone_number.length());
        }
        profilepayload.setMobileNumber(String.format("%s%s", "+", phone_number));

        MemberProfileResponse responser = authApi.getMemberDetails(profile);

        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(responser.getPayload().getChurchCode());

        MemberPersonnelResponse personnelResponse = authApi.loginMemberPersonnel(personnel);
        // Session Number
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        String church_code = personnelResponse.getPayload().getOrganisationNumber();
        String church_name = personnelResponse.getPayload().getOrganisationName();

        // Member Request Details With Code
        RequestChurchDetailsWithCode churchDetailsWithCode = new RequestChurchDetailsWithCode();
        Churchpayload churchpayload = new Churchpayload();
        churchpayload.setChurchCode(church_code);
        churchpayload.setAccessNumber(phone_number);
        churchpayload.setMobileServiceProvider("Safaricom");
        churchpayload.setSessionNumber(session_number);
        churchDetailsWithCode.setChurchpayload(churchpayload);

        RequestChurchDetailsWithCodeResponse CodeResponse = authApi.getChurchCodeDetails(churchDetailsWithCode);

        String conference_id = CodeResponse.getConferenceNumber();
        String conference_name = CodeResponse.getConferenceName();

        // Session Number 2
        final int session_number2 = (int) ((Math.random() * 9000000) + 1000000);

        MobileReceiveFundsTransfer transfer = new MobileReceiveFundsTransfer();

        com.example.tried.auth.personnel.transfer.Payload payload = new
                com.example.tried.auth.personnel.transfer.Payload();
        payload.setChurchCode(personnel.getChurchCode());
        payload.setSessionNumber(String.valueOf(session_number2));
        payload.setMeansOfPayment("M-PESA");
        payload.setCollectingParty("M-PESA");
        payload.setContributingAs("Institution");
        payload.setTotalAmount(total_amount);
        payload.setContributorContactType("Institution");
        payload.setReceiverId(conference_id);
        payload.setReceiverName(conference_name);
        payload.setContributorContact(phone_number);
        payload.setContributorType("Conference");
        payload.setContributingFor("Conference");
        payload.setContributorName(church_name);

        AccountDetails details = new AccountDetails();
        details.setSourceAccount(account_name);
        details.setSourceAccountNumber(account_number);
        payload.setAccountDetails(details);

        TransferDuration transferDuration = new TransferDuration();
        transferDuration.setStartDate(start_date);
        transferDuration.setEndDate(end_date);

        payload.setTransferDuration(transferDuration);

        HashMap<String, Integer> map1 = new HashMap<String, Integer>();

        com.example.tried.auth.personnel.transfer.FundDistribution fundDistribution =
                new com.example.tried.auth.personnel.transfer.FundDistribution();

        if(trust_funds != null) {
            for(int i = 0; i < trust_funds.length;i++){
                map1.put(trust_funds[i], fund_amount[i]);
            }
            fundDistribution.setTrustFunds(map1);
        }else{
            fundDistribution.setTrustFunds(map1);
        }

        payload.setFundDistribution(fundDistribution);
        transfer.setPayload(payload);

        MobileReceiveFundsTransferResponse responsed = personnelApi.getFundsTransfertoConference(transfer);
        return responsed;
    }


}
