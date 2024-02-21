package com.example.tried.controller;

import com.example.tried.auth.dashboard.Dapayload;
import com.example.tried.auth.dashboard.ListMembers;
import com.example.tried.auth.dashboard.ListMembersResponse;
import com.example.tried.auth.dashboard.MembersItem;
import com.example.tried.auth.dashboard.deactivated.Authentication;
import com.example.tried.auth.dashboard.deactivated.Deapayload;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembers;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembersResponse;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccounts;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccountsResponse;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dashboard.trust_funds.Payload;
import com.example.tried.auth.dashboard.trust_funds.TransactionsItem;
import com.example.tried.auth.dates.DateRange;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.enums.Receipt;
import com.example.tried.auth.enums.SelectedLanguage;
import com.example.tried.auth.enums.Telco;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.services.AuthApi;
import com.example.tried.services.TrustFundSummary;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.codec.binary.Base32;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/authenticate")
public class AuthControl {

    @Autowired
    AuthApi authApi;

    private ObjectMapper objectMapper;

    public AuthControl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Get the Authenticator Login Page
    @GetMapping(path="/login")
    public String authLogin(){
        return "login";
    }

    // Get the About Page
    @GetMapping(path="/about")
    public String getAbout(){
        return "about";
    }

    // Get the Profile Page
    @GetMapping(path="/profile")
    public String getProfile(Model model, Model model2,
                             @RequestParam(value="p")String p,
                             @RequestParam(value="q")String q){

        // String phoneNumber = "254707981971";
        // Model Attributes in Base32 Format
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        String phoneNumber = new String(decodedBytes);
        String pin = new String(decodedBytes1);

        System.out.println("Phone Number Retrieved using Post Request: "+phoneNumber);

        // Get Profile Information
        MemberProfile profiler = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profiler.setProfilepayload(payload);

        // Profile Info
        MemberProfileResponse profile = authApi.getMemberDetails(profiler);
        String Fullname = profile.getPayload().getMemberName();
        String ChurchCode = profile.getPayload().getChurchCode();
        String Residence = profile.getPayload().getContributesAs();

        // Request Church Details
        RequestChurchDetails requestChurchDetails = new RequestChurchDetails();
        requestChurchDetails.setChurchCode(profile.getPayload().getChurchCode());
        requestChurchDetails.setMobileServiceProvider("Safaricom");
        requestChurchDetails.setAccessNumber(phoneNumber);

        // More Profile Info
        RequestChurchDetailsResponse requestCode = authApi.getMemberChurchDetails(requestChurchDetails);
        String group = requestCode.getGroups();
        String otherPhoneNumber = requestCode.getOtherPhoneNumber();
        String language = profile.getPayload().getPreferredLanguage();
        //String phoneOwner = requestCode.get

        // Member Details
        model.addAttribute("fullName", Fullname);
        model.addAttribute("mobileNumber", phoneNumber);
        model.addAttribute("pin",pin);
        model.addAttribute("churchCode", ChurchCode);
        model.addAttribute("groupName", group);
        model.addAttribute("otherPhoneNumber", otherPhoneNumber);
        model.addAttribute("Selected", SelectedLanguage.values());
        model.addAttribute("Telco", Telco.values());
        model.addAttribute("Receipt", Receipt.values());
        model.addAttribute("Language", language);


        // Save the Details
        return "profile";
    }

    // Get the Statement Page
    @GetMapping(path="/statement")
    public String getStatement(Model model,@RequestParam("p")String p, @RequestParam("q")String q){

        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        String phone = new String(decodedBytes);
        String pin = new String(decodedBytes1);
        model.addAttribute("phone",phone);
        model.addAttribute("pin",pin);
        model.addAttribute("Phone", p);
        model.addAttribute("Pin", q);
        return "statement";
    }

    // Server Side OfferingAuthentication
    @GetMapping(path="/auth")
    public String serverLogin(){
        return "auth";
    }

    @GetMapping(path="/personnel")
    public String loadPersonnelPage(@RequestParam("p")String p, @RequestParam("q")String q,Model model){
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        String phone = new String(decodedBytes);
        String pin = new String(decodedBytes1);
        model.addAttribute("phone",phone);
        model.addAttribute("pin",pin);
        model.addAttribute("Phone", p);
        model.addAttribute("Password", q);
        return "personnel";
    }

    @GetMapping(path="/personnel2")
    public String loadPersonnelPages(Model model){
        String username = "254707981971";
        String password = "1226";

        Base32 base32 = new Base32();
        String phone_encode = base32.encodeAsString(username.getBytes());
        String pass_encode = base32.encodeAsString(password.getBytes());
        model.addAttribute("Phone", phone_encode);
        model.addAttribute("Pin", pass_encode);
        return "personnel2";
    }

    // Get the Server Side Offering Authentication Information
    @PostMapping(path="/auth-login")
    public String passCredentials(@RequestParam("username")String username,
                                  @RequestParam("password") String password, Model model,
                                  Model model2){
        System.out.println("Username Received: "+ username);
        System.out.println("Password Received: "+ password);

        // Login Credentials
        LoginCredentials credentials = new LoginCredentials();
        credentials.setAccessNumber(username);
        credentials.setPin(password);

        // Login to the Users API
        AuthMemberResponse response = authApi.getMemberCredentials(credentials);

        // Member Profile Loading
        MemberProfile profile = new MemberProfile();

        Profilepayload profilePayload = new Profilepayload();
        profilePayload.setMobileNumber("+" + username);
        profilePayload.setFromWithin(true);
        profile.setProfilepayload(profilePayload);

        MemberProfileResponse profiler = authApi.getMemberDetails(profile);

        // Get the Member Church Details
        RequestChurchDetails requestChurchDetails = new RequestChurchDetails();
        requestChurchDetails.setAccessNumber(username);
        requestChurchDetails.setChurchCode(profiler.getPayload().getChurchCode());
        if(username.contains("254")){
            requestChurchDetails.setMobileServiceProvider("Safaricom");
        }else{
            requestChurchDetails.setMobileServiceProvider("Airtel");
        }


        //Base 32 Encode Phone

        Base32 base32 = new Base32();
        String phone_encode = base32.encodeAsString(username.getBytes());
        String pass_encode = base32.encodeAsString(password.getBytes());


        if(!(response.getState() == null)){
            model.addAttribute("Username", username);
            model.addAttribute("State", response.getState());
            model.addAttribute("Phone", phone_encode);
            model2.addAttribute("Password", pass_encode);
            model2.addAttribute("ChurchName", profiler.getPayload().getChurchName());
            model2.addAttribute("ConferenceNo", profiler.getPayload().getConferenceName());
            return "personnel";
        }else{
            model.addAttribute("Error", response.getError());
            return "auth";
        }
    }

    // Get the Server Side OfferingAuthentication Information
    @PostMapping(path="/auth-register")
    public String registerCredentials(@RequestParam("fullname")String fullname,
                                      @RequestParam("phone") String phone,
                                      @RequestParam("email") String email,
                                      @RequestParam("pin") String pin,
                                      @RequestParam("church_code") String church_code,
                                      Model model, Model model2){

        AuthMemberRegister register = new AuthMemberRegister();

        // Further Registration of Files
        model.addAttribute("fullname", fullname);
        model.addAttribute("phone", phone);
        model.addAttribute("email", email);
        model.addAttribute("pin", pin);
        model.addAttribute("church_code", church_code);

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone);
        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);

        if(response1.getPayload().getMemberName() == null){
            System.out.print("Further Registration Ongoing");
            return "register";
        }else{
            String response = "Your Phone Number is already Registered to a Church Code: "+ response1.getPayload().getChurchCode();
            System.out.println(response);
            model2.addAttribute("Error", response);
            return "redirect:/authenticate/login?q="+response;
        }
    }

    @PostMapping(value="/registration")
    public String registerMember(@RequestParam("fullname") String fullname, @RequestParam("email") String email,
                                 @RequestParam("churchCode")String churchCode ,@RequestParam("phone") String phone,
                                 @RequestParam("phone_number_privacy")String phone_number_privacy, @RequestParam("language")String language,
                                 @RequestParam("phoneOwner")Boolean phoneOwner, @RequestParam("churchMember")String churchMember,
                                 @RequestParam("receipt_to") String receipt_to, @RequestParam("otherPhoneNumber") String otherPhoneNumber,
                                 @RequestParam("residence")String residence){

        // Member Registration
        AuthMemberRegister register = new AuthMemberRegister();
        register.setEmail(email);
        register.setIsMember(churchMember);
        register.setResidence(residence);
        register.setFullNames(fullname);
        register.setPhoneNumberPrivacy(phone_number_privacy);
        register.setPhoneOwner(phoneOwner);
        register.setMobileNumber(phone);
        register.setPreferredLanguage(language);
        register.setOtherPhoneNumber(otherPhoneNumber);
        register.setGivingReceiptedTo(receipt_to);
        register.setChurchCode(churchCode);


        authApi.registerMember(register);
        return "redirect:/authenticate/login";
    }

    // OfferingAuthentication on the Server Side
    @PostMapping(path="/auth-personnel")
    public String serverLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password, Model model) {
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);

        // Get the Member Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);
        System.out.println("Response: " + response.toString());

        if(response.getPayload().getPersonnelCfmsNumber() != null) {
            model.addAttribute("CFMS_Personnel_No", response.getPayload().getPersonnelCfmsNumber());
            model.addAttribute("Username", username);
            return "check";
        }else {
            return "personnel_error";
        }
    }

    // Registration on the Server Side
    @GetMapping(path="/register")
    public String ServerRegistration(){
        return "register";
    }


    // Get the Authenticator Login Page
    @GetMapping(path="/settings")
    public String checkSettings(@RequestParam("p")String p, @RequestParam("q")String q, Model model){

        // Model Attributes in Base32 Format
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        String phone = new String(decodedBytes);
        String pin = new String(decodedBytes1);
        model.addAttribute("phone",phone);
        model.addAttribute("pin",pin);
        model.addAttribute("Phone", p);
        model.addAttribute("Pin", q);
        return "settings";
    }

    @PostMapping(path="/logout")
    public String authLogout(){
        return "redirect:/authenticate/login";
    }


    @GetMapping(path="/dashboard")
    public String getDashboard(Model model, Model model2, Model model3){
        String username = "mwakesho";
        String password = "0389";

        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);

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

        // Get the List of Local Church Members
        List<MembersItem> membersList = dashboardResponse.getDarepayload().getMembers();
        model.addAttribute("Members", membersList);
        model.addAttribute("ChurchSize", membersList.size());

        // List Deactivated Church Members
        ListDeactivatedMembers listDeactivatedMembers = new ListDeactivatedMembers();
        Deapayload deapayload = new Deapayload();
        deapayload.setChurchName(church_name);
        deapayload.setChurchCode(church_code);

        // Deactivated Members Authentication
        Authentication authentication = new Authentication();
        authentication.setSessionNumber(rand);
        authentication.setPersonnelName(personnel_name);
        authentication.setPassword(password);
        authentication.setUser(username);
        authentication.setInstitutionName(church_name);
        authentication.setInstitutionLevel(church_level);
        authentication.setInstitutionNumber(church_code);

        listDeactivatedMembers.setDeapayload(deapayload);
        listDeactivatedMembers.setAuthentication(authentication);
        ListDeactivatedMembersResponse members1 = authApi.getDeactivatedMembers(listDeactivatedMembers);


        // Deactivated Members List
        List<com.example.tried.auth.dashboard.deactivated.MembersItem> membersList1 = members1.getMembers();
        model2.addAttribute("listDeactivatedMembers", membersList1);
        model2.addAttribute("deactivatedSize", membersList1.size());

        // Session Numbers
        final long rand1 = (int) ((Math.random() * 900000000) + 100000000);
        final int rand2 = (int) ((Math.random() * 9000000) + 1000000);

        // Get the Payment Accounts
        LocalChurchPaymentAccounts paymentAccounts = new LocalChurchPaymentAccounts();
        paymentAccounts.setChurchName(church_name);
        paymentAccounts.setPassword(password);
        paymentAccounts.setChurchCode(church_code);
        paymentAccounts.setUser(username);
        paymentAccounts.setSessionNumber(rand1);

        LocalChurchPaymentAccountsResponse churchPaymentAccountsResponse = authApi
                .getPaymentAccounts(paymentAccounts);

        // Get the Previous Month Trust Fund Summary
        LocalChurchTrustFundSummary trustFundSummary = new LocalChurchTrustFundSummary();
        Payload payload = new Payload();

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
        authentication1.setSessionNumber(rand2);
        authentication1.setInstututionName(church_name);
        authentication1.setInstututionNumber(church_code);
        trustFundSummary.setAuthentication(authentication1);

        System.out.println("Local Church Trust Fund Summary: " + HelperUtility.toJSON(trustFundSummary));

        LocalChurchTrustFundSummaryResponse localChurchTrustFund = authApi.getLocalChurchTrustFundSummary(trustFundSummary);

        System.out.println("Local Church Trust Fund Summary Response: " + HelperUtility.toJSON(localChurchTrustFund));

        List<TransactionsItem> transactions = localChurchTrustFund.getTrupayload().getTransactions();
        List<TransactionsItem> totals = new ArrayList<TransactionsItem>();
        String total_amount = "";
        String total_amount_paid = "";
        String balance = "";
        for(TransactionsItem transaction : transactions){
            if (transaction.getReceiptNumber() == null){
                totals.add(transaction);
            }

            if(transaction.getModeOfPayment() == null){
                total_amount = transaction.getTotalReceiptedAmount();
                total_amount_paid = transaction.getTotalReceiptedAmountPaid();
                balance = transaction.getBalance();
            }
        }

        total_amount = total_amount.substring(0, total_amount.length()-1);
        total_amount_paid = total_amount_paid.substring(0, total_amount_paid.length()-1);
        Integer total_transactions = transactions.size();
        Integer total_zone = totals.size();
        Integer total_month_transactions = total_transactions - total_zone;
        model3.addAttribute("Transactions",  total_month_transactions);
        model3.addAttribute("TotalAmount",  total_amount);
        model3.addAttribute("TotalAmountPaid",  total_amount_paid);

        String previousMonth = localdate.getMonth().minus(1).toString();
        model3.addAttribute("PreviousMonth", WordUtils.capitalize(previousMonth.toLowerCase()));
        //model3.addAttribute("")

        // Get the Trust Fund Summary

        return "personnel_dashboard";
    }


    @GetMapping("/moved")
    public String getUSSD(){
        return "movie";
    }

}
