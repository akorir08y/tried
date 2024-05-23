package com.example.tried.controller;

import com.example.tried.auth.dashboard.Dapayload;
import com.example.tried.auth.dashboard.ListMembers;
import com.example.tried.auth.dashboard.ListMembersResponse;
import com.example.tried.auth.dashboard.MembersItem;
import com.example.tried.auth.dashboard.deactivated.Authentication;
import com.example.tried.auth.dashboard.deactivated.Deapayload;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembers;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembersResponse;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.enums.Receipt;
import com.example.tried.auth.enums.SelectedLanguage;
import com.example.tried.auth.enums.Telco;
import com.example.tried.auth.member.*;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.accounts.LocalChurchAccounts;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccounts;
import com.example.tried.auth.personnel.payments_accounts.ListLocalChurchPaymentAccountsResponse;
import com.example.tried.services.AuthApi;
import com.example.tried.services.PersonnelApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.codec.binary.Base32;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/authenticate")
@Slf4j
public class AuthControl {

    @Autowired
    AuthApi authApi;

    @Autowired
    PersonnelApi personnelApi;

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
                             @RequestParam(value="q")String q) throws JsonProcessingException {

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

    @GetMapping(path="/personnel-registered")
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
                                  Model model2) throws JsonProcessingException {


        // Login Credentials
        LoginCredentials credentials = new LoginCredentials();
        credentials.setAccessNumber(username);
        credentials.setPin(password);

        System.out.println("Credentials: " + credentials);

        // Login to the Users API
        AuthMemberResponse response = authApi.getMemberCredentials(credentials);

        // Member Profile Loading
        MemberProfile profile = new MemberProfile();

        Profilepayload profilePayload = new Profilepayload();
        profilePayload.setMobileNumber("+" + username);
        profilePayload.setFromWithin(true);
        profile.setProfilepayload(profilePayload);

        System.out.println("Profile Payload: " + profilePayload);

        MemberProfileResponse profiler = authApi.getMemberDetails(profile);

        // Get the Member Church Details
        RequestChurchDetails requestChurchDetails = new RequestChurchDetails();
        requestChurchDetails.setAccessNumber(username);
        requestChurchDetails.setChurchCode(profiler.getPayload().getChurchCode());
        requestChurchDetails.setMobileServiceProvider("Safaricom");


        //Base 32 Encode Phone
        Base32 base32 = new Base32();
        String phone_encode = base32.encodeAsString(username.getBytes());
        String pass_encode = base32.encodeAsString(password.getBytes());

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        MemberProfile profiled = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + username);
        profiled.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profiled);

        // More Profile Info
        RequestChurchDetailsResponse requestCode = authApi.getMemberChurchDetails(requestChurchDetails);
        String otherPhoneNumber = requestCode.getOtherPhoneNumber();

        if(!(response.getState() == null)){
            model.addAttribute("personal_no", username);
            model.addAttribute("personal_no2", otherPhoneNumber);
            model.addAttribute("personal_pin", password);
            model.addAttribute("church_code", requestCode.getChurchNumber());
            model.addAttribute("church_name", requestCode.getChurchName());
            model2.addAttribute("phone",username);
            model2.addAttribute("pin",password);
            model2.addAttribute("cfms_member_name", response1.getPayload().getMemberName());
            model2.addAttribute("cfms_member_number", response1.getPayload().getMembershipNumber());
            model2.addAttribute("Password", pass_encode);
            model2.addAttribute("Phone", phone_encode);
            return "offering";
        }else{
            String responsed = "Check your Login Credentials";
            model2.addAttribute("Error", response);
            return "redirect:/authenticate/login?q="+response;
        }
    }

    // Get the Server Side OfferingAuthentication Information
    @PostMapping(path="/auth-register")
    public String registerCredentials(@RequestParam("fullname")String fullname,
                                      @RequestParam("phone") String phone,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam("pin") String pin,
                                      @RequestParam("church_code") String church_code,
                                      Model model, Model model2) throws JsonProcessingException {

        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        phone = phone.replace(",", "");

        if(phone.startsWith("+254")){
            phone = phone.substring(1,phone.length());
        }

        // Further Registration of Files
        model.addAttribute("fullname", fullname);
        model.addAttribute("phone", phone);
        model.addAttribute("email", email);
        model.addAttribute("pin", pin);
        model.addAttribute("church_code", church_code);

        System.out.println("Phone Number: " + phone);

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone);
        profile.setProfilepayload(payload);
        MemberProfileResponse response1 = authApi.getMemberDetails(profile);


        RequestChurchDetailsWithCode requestCode = new RequestChurchDetailsWithCode();
        Churchpayload churchpayload = new Churchpayload();
        churchpayload.setChurchCode(church_code);
        churchpayload.setAccessNumber(phone);
        churchpayload.setMobileServiceProvider("Safaricom");
        churchpayload.setSessionNumber(session_number);
        requestCode.setChurchpayload(churchpayload);

        RequestChurchDetailsWithCodeResponse detailsWithCodeResponse = authApi.getChurchCodeDetails(requestCode);

        if(detailsWithCodeResponse.getChurchName() == null){
            String response = "The Church Number Requested Does not Exist. Try Again";
            System.out.println(response);
            model2.addAttribute("Error", response);
            return "redirect:/authenticate/login?q="+response;
        }

        if(response1.getPayload().getMemberName() == null) {
            System.out.print("Further Registration Ongoing");
            return "register-member";
        }else{
            String response = "An Error Occured During Registration";
            System.out.println(response);
            model2.addAttribute("Error", response);
            return "redirect:/authenticate/login?q="+response;
        }
    }

    @PostMapping(value="/registration")
    public String registerMember(@RequestParam("fullname") String fullname, @RequestParam(value = "email", required = false) String email,
                                 @RequestParam("churchCode")String churchCode ,@RequestParam("phone") String phone,
                                 @RequestParam("phone_number_privacy")String phone_number_privacy, @RequestParam("language")String language,
                                 @RequestParam(value="phoneOwner", required = false)Boolean phoneOwner, @RequestParam(value="church_member", required = false)String churchMember,
                                 @RequestParam("receipt_to") String receipt_to, @RequestParam(value = "residence", required = false)String residence,
                                 @RequestParam("pin") String pin) throws JsonProcessingException {

        if (phoneOwner == null) {
            phoneOwner = false;
        }else if(phoneOwner == true){
            phoneOwner = true;
        }

        if (churchMember == null) {
            churchMember = "false";
        }else if(churchMember == "true"){
            churchMember = "true";
        }

        if(phone.startsWith("0")){
            phone = phone.substring(1, phone.length());
            phone = String.format("%s%s", "254", phone);
        }

        if(phone.startsWith("+254")){
            phone = phone.substring(1,phone.length());
        }

        // Member Registration
        MemberRegister register = new MemberRegister();
        register.setIsMember(churchMember);
        register.setResidence(residence);
        register.setFullNames(fullname);
        register.setPhoneNumberPrivacy(phone_number_privacy);
        register.setPhoneOwner(phoneOwner);
        if(phone.length() == 12 && phone.startsWith("254")){
            register.setMobileNumber(phone);
        }
        register.setPin(pin);
        register.setPreferredLanguage(language);
        register.setGivingReceiptedTo(receipt_to);
        register.setChurchCode(churchCode);
        register.setAreas("");
        register.setGroupName("");


        AuthMemberRegistrationResponse response = authApi.registerMember(register);
        System.out.println("Response: " + response);

        String responsed;

        if(response != null){
            if(response.getStatus() == 0){

                String creation_date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).format(new Date());

                String cfms_web = "https://lakeatts.co.ke:8443/cfms-web/authenticate/login";

                String message = "<#>Your CFMS account has been created at " + creation_date +".\n";
                message += "Your Pin is "+pin+" . Use the pin to set a new one within 7 days.\n";
                message += "You can now visit the CFMS Web App on this particular URL "+cfms_web + " Or Alternatively, \n";
                message += "You can now download CFMS Android App From Play Store.";


                // OfferingAuthentication Details
                com.example.tried.auth.dto.Authentication authentication = new com.example.tried.auth.dto.Authentication();
                authentication.setUserName("");
                authentication.setPassword("");

                //SMS Request
                SmsRequest smsRequest = new SmsRequest();
                smsRequest.setMessageGroup("Individual");

                // List of Strings/
                List<String> Number = new ArrayList<>();
                if(phone.startsWith("254")) {
                    Number.add(phone);
                }
                smsRequest.setPhoneNumber(Number);

                // Additional Information
                smsRequest.setClientAccount("");
                smsRequest.setClientName("");
                smsRequest.setClintLevel("");
                smsRequest.setDate(creation_date);
                smsRequest.setMessages(message);

                // The Function Name
                String function = "lakeattsSMSGateway";

                // Combine All Collected Data
                SMS sms = new SMS();
                sms.setSmsRequest(smsRequest);
                sms.setAuthentication(authentication);
                sms.setFunction(function);

                SMSResponse response1 = personnelApi.sendRegistrationMessage(sms);
                System.out.println("SMS Response: "+ response1);
                return "redirect:/authenticate/login";

            }else if(response.getStatus() == 1){
                if(response.getNotice() != null) {
                    responsed = response.getNotice();
                    return "redirect:/authenticate/register-member?q=" + responsed;
                }else{
                    responsed = response.getError();
                    return "redirect:/authenticate/register-member?q=" + responsed;
                }
            }
        }else{
            responsed = "Registration was not Successful";
            return "redirect:/authenticate/register-member?q=" + responsed;
        }
        return "Registration Process Completed";
    }

    // OfferingAuthentication on the Server Side
    @PostMapping(path="/auth-personnel")
    public String serverLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("phone_number") String phone_number,
                              @RequestParam("pin") String pin, Model model, Model model2, Model model3) throws JsonProcessingException {

        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

        // Member Profile Information
        MemberProfile memberProfile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setMobileNumber("+" + phone_number);
        profilepayload.setFromWithin(true);

        memberProfile.setProfilepayload(profilepayload);
        MemberProfileResponse details = authApi.getMemberDetails(memberProfile);

        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(details.getPayload().getChurchCode());

        // Get the Member Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);

        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

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

        // Get the Treasurer Phone Number
        String personnel_phone = response.getPayload().getPersonnelPhone();

        // Credentials
        //Base 32 Encode Phone
        Base32 base321 = new Base32();
        String user_encode = base321.encodeAsString(username.getBytes());
        String pass_encode = base321.encodeAsString(password.getBytes());
        String phone_encode = base321.encodeAsString(phone_number.getBytes());
        String pin_encode = base321.encodeAsString(pin.getBytes());

        // Get the Current Year
        LocalDate localdate = LocalDate.now();
        String previousMonth = localdate.getMonth().minus(1).toString();


        if(response.getPayload().getPersonnelCfmsNumber() != null) {
            model.addAttribute("Members", membersList);
            model.addAttribute("ChurchSize", membersList.size());
            model2.addAttribute("personnel_name", username);
            model2.addAttribute("personnel_password", password);
            model2.addAttribute("personnel_phone", phone_number);
            model3.addAttribute("Username", user_encode);
            model3.addAttribute("Password", pass_encode);
            model3.addAttribute("Phone", phone_encode);
            model3.addAttribute("PreviousMonth", WordUtils.capitalize(previousMonth.toLowerCase()));
            return "personnel_dashboard";
        }else {
            String responsed = "Check your Login Credentials";
            model.addAttribute("Error", responsed);
            model.addAttribute("p", user_encode);
            model.addAttribute("q", pass_encode);
            return "redirect:/authenticate/statement2?p="+user_encode+"&q="+pass_encode+"&e="+response;
        }
    }


    // Personnel Authentication on the Server Side
    @PostMapping(path="/personnel")
    public String serverLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model, Model model2, Model model3) throws JsonProcessingException {

        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode("29999");

        System.out.println("Personnel Login: "+personnel);
        // Get the Member Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);

        System.out.println("Personnel Login Response: "+personnel);

        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

        // Get the Number of Members in a Church
        ListMembers members = new ListMembers();
        // Personnel Details
        String church_code = response.getPayload().getOrganisationNumber();
        String church_name = response.getPayload().getOrganisationName();
        String church_level = response.getPayload().getOrganisationLevel();
        String personnel_name = response.getPayload().getPersonnelName();
        String member_no = response.getPayload().getPersonnelCfmsNumber();
        String phone_number = response.getPayload().getPersonnelPhone();

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

        // Get the Treasurer Phone Number
        String personnel_phone = response.getPayload().getPersonnelPhone();

        // Credentials
        //Base 32 Encode Phone
        Base32 base321 = new Base32();
        String user_encode = base321.encodeAsString(username.getBytes());
        String pass_encode = base321.encodeAsString(password.getBytes());
        String phone_encode = base321.encodeAsString(phone_number.getBytes());

        // Get the Current Year
        LocalDate localdate = LocalDate.now();
        String previousMonth = localdate.getMonth().minus(1).toString();


        if(response.getPayload().getPersonnelCfmsNumber() != null) {
            model.addAttribute("Members", membersList);
            model.addAttribute("ChurchSize", membersList.size());
            model2.addAttribute("personnel_name", username);
            model2.addAttribute("personnel_password", password);
            model2.addAttribute("personnel_phone", phone_number);
            model3.addAttribute("Username", user_encode);
            model3.addAttribute("Password", pass_encode);
            model3.addAttribute("Phone", phone_encode);
            model3.addAttribute("PreviousMonth", WordUtils.capitalize(previousMonth.toLowerCase()));
            return "personnel_dashboard";
        }else {
            String responsed = "Check your Login Credentials";
            model.addAttribute("Error", responsed);
            model.addAttribute("p", user_encode);
            model.addAttribute("q", pass_encode);
            return "redirect:/authenticate/login?p="+user_encode+"&q="+pass_encode+"&e="+responsed;
        }
    }

    // Registration on the Server Side
    @GetMapping(path="/register-member")
    public String ServerRegistration(){
        return "register-member";
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


    @GetMapping(path="/personnel_dashboard")
    public String getDashboard(@RequestParam("p") String p, @RequestParam("q") String q,
                               @RequestParam("r") String r, Model model, Model model2, Model model3) throws JsonProcessingException {

        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        byte[] decodedBytes2 = base32.decode(r);

        String phone_number = new String(decodedBytes);
        String password = new String(decodedBytes1);
        String username = new String(decodedBytes2);


        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }


        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

        // Member Profile Information
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

        // Personnel Information
        model2.addAttribute("personnel_name", username);
        model2.addAttribute("personnel_password", password);
        model2.addAttribute("personnel_phone", phone_number);


        // Credentials
        //Base 32 Encode Phone
        Base32 base321 = new Base32();
        String user_encode = base321.encodeAsString(username.getBytes());
        String pass_encode = base321.encodeAsString(password.getBytes());
        String phone_encode = base321.encodeAsString(phone_number.getBytes());

        model3.addAttribute("Username", user_encode);
        model3.addAttribute("Password", pass_encode);
        model3.addAttribute("Phone", phone_encode);

        // Get the Current Year
        LocalDate localdate = LocalDate.now();
        String previousMonth = localdate.getMonth().minus(1).toString();
        model3.addAttribute("PreviousMonth", WordUtils.capitalize(previousMonth.toLowerCase()));

        // Get the Trust Fund Summary
        return "personnel_dashboard";
    }


    @GetMapping(path="/personnel_register")
    public String getPersonnelRegistration(@RequestParam(value="p")String p,@RequestParam(value="q")String q
                                        ,@RequestParam(value="r")String r,
                                           Model model, Model model2,Model model3, Model model4) throws JsonProcessingException {

        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        byte[] decodedBytes2 = base32.decode(r);

        String phone_number = new String(decodedBytes);
        String password = new String(decodedBytes1);
        String username = new String(decodedBytes2);



        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

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

        // Encrypted Credentials
        model3.addAttribute("Phone", p);
        model3.addAttribute("Password", q);
        model3.addAttribute("Username", r);



        // Credentials
        model4.addAttribute("church_code", response.getPayload().getOrganisationNumber());
        model4.addAttribute("personnel_name", username);
        model4.addAttribute("personal_password", password);
        model4.addAttribute("personal_no", phone_number);
        model4.addAttribute("personal_pin", phone_number);

        return "personnel_register";
    }


    @GetMapping("/moved")
    public String getUSSD(){
        return "movie";
    }


    // New Interface Profile Information
    @GetMapping("/profile2")
    public String getProfileInformation(Model model, @RequestParam(value="p")String p,
                                        @RequestParam(value="q")String q,
                                        Model model2) throws JsonProcessingException {



        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        String phoneNumber = new String(decodedBytes);
        String pin = new String(decodedBytes1);

        if(phoneNumber.startsWith("+254")){
            phoneNumber = phoneNumber.substring(1,phoneNumber.length());
        }

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
        model.addAttribute("phone", phoneNumber);
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

        model2.addAttribute("Phone", p);
        model2.addAttribute("Password", q);

        // Credentials
        return "profile2";
    }

    // Get the Statement 2
    @GetMapping("/statement2")
    public String getOfferingStatement(@RequestParam(value="p")String p,
                                       @RequestParam(value="q")String q, Model model) throws JsonProcessingException {
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        String phoneNumber = new String(decodedBytes);
        String pin = new String(decodedBytes1);
        model.addAttribute("phone",phoneNumber);
        model.addAttribute("phone_number",phoneNumber);
        model.addAttribute("pin",pin);
        model.addAttribute("Phone", p);
        model.addAttribute("Password", q);

        if(phoneNumber.startsWith("+254")){
            phoneNumber = phoneNumber.substring(1,phoneNumber.length());
        }

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
        List<String> account_info = new ArrayList<String>();

        RequestChurchDetailsResponse churchDetails = authApi.getMemberChurchDetails(requestCode);
        System.out.println("Response: "+ churchDetails.getDepartmentalAccounts());
        String cash = churchDetails.getDepartmentalAccounts();
        String cash1 = churchDetails.getTrustFundAccounts();
        String cash2 = churchDetails.getSpecialTrustFundAccounts();
        // System.out.println("Raw: "+ cash);
        cash = cash.substring(1, cash.length()-1);
        cash1 = cash1.substring(1, cash1.length()-1);
        cash2 = cash2.substring(1, cash2.length()-1);
        // System.out.println("With Out Curly Brackets: "+cash);
        String[] cashArray = cash.split(",");
        String[] cashArray1 = cash1.split(",");
        String[] cashArray2 = cash2.split(",");
        // System.out.println("Split array:"+ cashArray);


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

        for(int i=0; i<account_names.size(); i++){
            account_info.add(account_names.get(i) + "#" + account_number.get(i));
        }

        model.addAttribute("AccountDetails", account_info);
        model.addAttribute("AccountNumbers", account_number);
        return "statement2";
    }


    @GetMapping("/offering")
    public String getMemberOffering(@RequestParam(value="p")String p,
                                    @RequestParam(value="q")String q,Model model, Model model2) throws JsonProcessingException {
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        String phoneNumber = new String(decodedBytes);
        String pin = new String(decodedBytes1);
        model2.addAttribute("phone",phoneNumber);
        model2.addAttribute("pin",pin);
        model2.addAttribute("Phone", p);
        model2.addAttribute("Password", q);

        System.out.println("Phone Number Retrieved using Post Request: "+phoneNumber);

        if(phoneNumber.startsWith("+254")){
            phoneNumber = phoneNumber.substring(1,phoneNumber.length());
        }

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
        model2.addAttribute("cfms_member_name", profile.getPayload().getMemberName());
        model2.addAttribute("cfms_member_number", profile.getPayload().getMembershipNumber());
        return "offering";
    }

    @GetMapping("/personnel_receipting")
    public String getPersonnelReceipting(@RequestParam("p") String p,@RequestParam("q") String q,
                                         @RequestParam("r") String r,Model model, Model model2, Model model3) throws JsonProcessingException {

        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        byte[] decodedBytes2 = base32.decode(r);

        String phone_number = new String(decodedBytes);
        String password = new String(decodedBytes1);
        String username = new String(decodedBytes2);


        // Session Numbers
        final long rand = (int) ((Math.random() * 900000000) + 100000000);

        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

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
        String treasurer_no = response.getPayload().getPersonnelPhone();

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

        // Plaintext Credentials
        model2.addAttribute("personal_no",phone_number);
        model2.addAttribute("personal_password",password);
        model2.addAttribute("personnel_name", username);
        model2.addAttribute("treasurer_no", treasurer_no);

        model2.addAttribute("church_code", church_code);

        // Encrypted Credentials
        model3.addAttribute("Phone", p);
        model3.addAttribute("Password", q);
        model3.addAttribute("Username", r);


        return "personnel_receipting";
    }

    @GetMapping("/reports")
    public String getReportsAndStatements(@RequestParam("p") String p,@RequestParam("q") String q,
                                         @RequestParam("r") String r,Model model, Model model2, Model model3,
                                          Model model4) throws JsonProcessingException {

        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        byte[] decodedBytes2 = base32.decode(r);

        String phone_number = new String(decodedBytes);
        String password = new String(decodedBytes1);
        String username = new String(decodedBytes2);


        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

        // Plaintext Credentials
        model.addAttribute("personal_no",phone_number);
        model.addAttribute("personal_password",password);
        model.addAttribute("personnel_name", username);


        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+"+ phone_number);
        profile.setProfilepayload(profilepayload);

        MemberProfileResponse responser = authApi.getMemberDetails(profile);
        String church_name = responser.getPayload().getChurchName();
        String church_code = responser.getPayload().getChurchCode();

        // Get the Personnel Phone Number
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(church_code);

        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);
        String personnel_phone = response.getPayload().getPersonnelPhone();

        model.addAttribute("treasurer_no", personnel_phone);

        // Get the Number of Members in a Church
        ListMembers members = new ListMembers();

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

        model2.addAttribute("Members", unique);


        // Encrypted Credentials
        model3.addAttribute("Phone", p);
        model3.addAttribute("Password", q);
        model3.addAttribute("Username", r);


        // Get Specific Accounts For Specific Summary Reports
        RequestChurchDetails requestCode = new RequestChurchDetails();
        requestCode.setChurchCode(church_code);
        requestCode.setAccessPoint("Web App");
        requestCode.setConnectionPurpose("Registration");
        requestCode.setAccessNumber(phone_number);
        requestCode.setMobileServiceProvider("Safaricom");

        List<String> account_names = new ArrayList<String>();
        List<String> account_number = new ArrayList<String>();
        List<String> account_info = new ArrayList<String>();

        RequestChurchDetailsResponse churchDetails = authApi.getMemberChurchDetails(requestCode);
        System.out.println("Response: "+ churchDetails.getDepartmentalAccounts());
        String cash = churchDetails.getDepartmentalAccounts();
        String cash1 = churchDetails.getTrustFundAccounts();
        String cash2 = churchDetails.getSpecialTrustFundAccounts();
        // System.out.println("Raw: "+ cash);
        cash = cash.substring(1, cash.length()-1);
        cash1 = cash1.substring(1, cash1.length()-1);
        cash2 = cash2.substring(1, cash2.length()-1);
        // System.out.println("With Out Curly Brackets: "+cash);
        String[] cashArray = cash.split(",");
        String[] cashArray1 = cash1.split(",");
        String[] cashArray2 = cash2.split(",");
        // System.out.println("Split array:"+ cashArray);


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

        for(int i=0; i<account_names.size(); i++){
            account_info.add(account_names.get(i) + "#" + account_number.get(i));
        }

        model4.addAttribute("AccountDetails", account_info);
        model4.addAttribute("AccountNumbers", account_number);

        return "reports";
    }


    @GetMapping("/report-member")
    public String getMemberStatement(@RequestParam("p") String p,@RequestParam("q") String q,
                                          @RequestParam("r") String r,
                                     @RequestParam("member_name") String member_name,@RequestParam("member_number") String member_number,
                                     Model model, Model model2, Model model3) throws JsonProcessingException {

        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        byte[] decodedBytes2 = base32.decode(r);

        String phone_number = new String(decodedBytes);
        String password = new String(decodedBytes1);
        String username = new String(decodedBytes2);


        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

        // Plaintext Credentials
        model.addAttribute("personal_no",phone_number);
        model.addAttribute("personal_password",password);
        model.addAttribute("personnel_name", username);


        MemberProfile profile = new MemberProfile();
        Profilepayload profilepayload = new Profilepayload();
        profilepayload.setFromWithin(true);
        profilepayload.setMobileNumber("+"+ phone_number);
        profile.setProfilepayload(profilepayload);

        MemberProfileResponse responser = authApi.getMemberDetails(profile);
        String church_name = responser.getPayload().getChurchName();
        String church_code = responser.getPayload().getChurchCode();

        // Get the Personnel Phone Number
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(church_code);

        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);
        String personnel_phone = response.getPayload().getPersonnelPhone();

        model.addAttribute("treasurer_no", personnel_phone);
        model2.addAttribute("member_name", member_name);
        model2.addAttribute("member_number", member_number);

        // Encrypted Credentials
        model3.addAttribute("Phone", p);
        model3.addAttribute("Password", q);
        model3.addAttribute("Username", r);


        return "report-member";
    }

    @GetMapping("/manage_accounts")
    public String selectLocalChurchAccounts(@RequestParam("p") String p, @RequestParam("q") String q,
                                            @RequestParam("r") String r,Model model, Model model2) throws IOException, JsonProcessingException {

        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        byte[] decodedBytes2 = base32.decode(r);

        String phone_number = new String(decodedBytes);
        String password = new String(decodedBytes1);
        String username = new String(decodedBytes2);


        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }


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
            if(result4.containsKey("priority_number")) {
                result4.put("account_number", key);
            }else if(!result4.containsKey("priority_number")){
                result4.put("account_number", key);
                result4.put("priority_number", "0");
            }

            if(!result4.containsKey("department")){
                result4.put("department", "No Department");
            }
            accounts.add(result4);
        }

        System.out.println("Accounts: " + accounts);

        // Encrypted Credentials
        model.addAttribute("Phone", p);
        model.addAttribute("Password", q);
        model.addAttribute("Username", r);
        model.addAttribute("Accounts", accounts);

        // Plaintext Credentials
        model2.addAttribute("personal_no",phone_number);
        model2.addAttribute("personal_password",password);
        model2.addAttribute("personnel_name", username);

        return "manage_accounts";
    }

    @GetMapping("/fund_transfer")
    public String getPaymentAccounts(@RequestParam("p") String p, @RequestParam("q") String q,
                                     @RequestParam("r") String r, Model model,
                                     Model model2, Model model3, Model model4) throws JsonProcessingException {

        // Encrypted Credentials
        Base32 base32 = new Base32();
        byte[] decodedBytes = base32.decode(p);
        byte[] decodedBytes1 = base32.decode(q);
        byte[] decodedBytes2 = base32.decode(r);

        String phone_number = new String(decodedBytes);
        String password = new String(decodedBytes1);
        String username = new String(decodedBytes2);


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

        // Member Personnel
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(profiler.getPayload().getChurchCode());

        MemberPersonnelResponse responsed = authApi.loginMemberPersonnel(personnel);
        String treasurer_number = responsed.getPayload().getPersonnelPhone();

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
        model.addAttribute("cashList", cashList);
        model2.addAttribute("bankList", bankList);

        // User Information Unencrypted
        model3.addAttribute("personnel_name", username);
        model3.addAttribute("personnel_password", password);
        model3.addAttribute("personnel_no", phone_number);
        model3.addAttribute("treasurer_no", treasurer_number);

        // Encrypted Information
        model4.addAttribute("Phone", p);
        model4.addAttribute("Password", q);
        model4.addAttribute("Username", r);

        return "fund_transfer";
    }
}
