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
import com.example.tried.auth.dto.*;
import com.example.tried.auth.enums.Receipt;
import com.example.tried.auth.enums.SelectedLanguage;
import com.example.tried.auth.enums.Telco;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.services.AuthApi;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                                 @RequestParam("phoneOwner")Boolean phoneOwner, @RequestParam("church_member")String churchMember,
                                 @RequestParam("receipt_to") String receipt_to, @RequestParam("otherPhoneNumber") String otherPhoneNumber,
                                 @RequestParam("residence")String residence,@RequestParam("pin") String pin){

        // Member Registration
        MemberRegister register = new MemberRegister();
        register.setIsMember(churchMember);
        register.setResidence(residence);
        register.setFullNames(fullname);
        register.setPhoneNumberPrivacy(phone_number_privacy);
        register.setPhoneOwner(phoneOwner);
        register.setMobileNumber(phone);
        register.setPin(pin);
        register.setPreferredLanguage(language);
        register.setGivingReceiptedTo(receipt_to);
        register.setChurchCode(churchCode);
        register.setAreas("");
        register.setGroupName("");


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
        String phone_number = "254786439659";

        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

        // Member Profile Information
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


        // Get the Current Year
        LocalDate localdate = LocalDate.now();

        String previousMonth = localdate.getMonth().minus(1).toString();
        model3.addAttribute("PreviousMonth", WordUtils.capitalize(previousMonth.toLowerCase()));

        // Get the Trust Fund Summary
        return "personnel_dashboard";
    }


    @GetMapping(path="/personnel-register")
    public String getPersonnelRegistration(Model model, Model model2){
        String username = "mwakesho";
        String password = "0389";
        String phone_number = "254786439659";

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

        // Get the List of Local Church Members
        List<MembersItem> membersList = dashboardResponse.getDarepayload().getMembers();

        Set<MembersItem> unique =
                new HashSet<MembersItem>(membersList);

        model.addAttribute("Members", unique);
        model.addAttribute("ChurchSize", unique.size());
        System.out.println("Church Member List Size: "+ membersList.size());
        System.out.println("Church Member Set Size: "+ unique.size());

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

        Set<com.example.tried.auth.dashboard.deactivated.MembersItem> hSet =
                new HashSet<com.example.tried.auth.dashboard.deactivated.MembersItem>(membersList1);
        model2.addAttribute("listDeactivatedMembers", hSet);
        model2.addAttribute("deactivatedSize", hSet.size());

        return "personnel_registration";
    }


    @GetMapping("/moved")
    public String getUSSD(){
        return "movie";
    }


    // New Interface Profile Information
    @GetMapping("/profile2")
    public String getProfileInformation(Model model){
        String phoneNumber = "254707981971";
        String pin = "1226";

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
        return "profile2";
    }

    // Get the Statement 2
    @GetMapping("/statement2")
    public String getOfferingStatement(){
        return "statement2";
    }


    @GetMapping("/offering")
    public String getMemberOffering(Model model){
        String phoneNumber = "254707981971";
        String pin = "1226";

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

        model.addAttribute("personal_no", phoneNumber);
        model.addAttribute("personal_no2", otherPhoneNumber);
        model.addAttribute("personal_pin", pin);
        model.addAttribute("church_code", ChurchCode);
        model.addAttribute("church_name", profile.getPayload().getChurchName());
        return "member_giving";
    }
}
