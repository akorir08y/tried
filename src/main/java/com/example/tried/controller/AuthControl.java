package com.example.tried.controller;

import com.example.tried.auth.dates.DateRange;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.auth.member.RequestChurchDetailsWithCodeResponse;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.services.AuthApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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
    public String getProfile(Model model){

        String phoneNumber = "254707981971";

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

        // Member Details
        model.addAttribute("Fullname", Fullname);
        model.addAttribute("Phone", phoneNumber);
        model.addAttribute("ChurchCode", ChurchCode);
        model.addAttribute("Group", group);

        return "profile";
    }

    // Get the Statement Page
    @GetMapping(path="/statement")
    public String getStatement(Model model){
        DateRange dateRange = new DateRange();
        dateRange.setDateFrom(new Date());
        dateRange.setDateTo(new Date());
        model.addAttribute("dateRange", dateRange);
        return "statement";
    }

    // Server Side OfferingAuthentication
    @GetMapping(path="/auth")
    public String serverLogin(){
        return "auth";
    }

    @GetMapping(path="/personnel2")
    public String loadPersonnelPage(){
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

        if(!(response.getState() == null)){
            model.addAttribute("Username", username);
            model.addAttribute("State", response.getState());
            model2.addAttribute("Name", profiler.getPayload().getMemberName());
            model2.addAttribute("ChurchName", profiler.getPayload().getChurchName());
            model2.addAttribute("ChurchCode", profiler.getPayload().getChurchCode());
            model2.addAttribute("MemberNo", profiler.getPayload().getMembershipNumber());
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
                                      Model model, Model model2){

        AuthMemberRegister register = new AuthMemberRegister();

        // Further Registration of Files
        model.addAttribute("fullname", fullname);
        model.addAttribute("phone", phone);
        model.addAttribute("email", email);
        model.addAttribute("pin", pin);


        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phone);
        profile.setProfilepayload(payload);

        MemberProfileResponse response1 = authApi.getMemberDetails(profile);
        System.out.println("Null Value: "+ response1.getPayload().getMemberName());

        if(response1.getPayload().getMemberName() == null){
            System.out.print("Further Registration Ongoing");
            return "register";
        }else {
            String response = "Your Phone Number is already Registered to a Church Code: "+ response1.getPayload().getChurchCode();
            System.out.println(response);
            model2.addAttribute("Error", response);
            return "redirect:/authenticate/login?q="+response;
        }
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
    public String checkSettings(){
        return "settings";
    }

    @PostMapping(path="/logout")
    public String authLogout(){
        return "redirect:/authenticate/login";
    }

}
