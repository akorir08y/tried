package com.example.tried.controller;

import com.example.tried.auth.dto.*;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.services.AuthApi;
import com.example.tried.utils.HelperUtility;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/personnel")
@Slf4j
public class PersonnelController {

    @Autowired
    AuthApi authApi;


    @PostMapping(path="/profile")
    public HashMap<String, String> getMemberProfile(@RequestParam("phone_number")String phoneNumber){

        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+"+ phoneNumber);
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
    public MemberRegisterUpdateResponse ActivateMemberDetails(@RequestParam("phone") String phone){

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
        updatepayload.setFullNames(profile.getPayload().getMemberName());
        updatepayload.setEmail("any@gmail.com");
        updatepayload.setMobileNumber(phone);
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

        System.out.println("Update Payload: " + updatepayload.toString());
        System.out.println("Auth Member Registration: "+ HelperUtility.toJSON(authMemberRegister));
        MemberRegisterUpdateResponse responsed = authApi.getMemberRegistrationUpdate(authMemberRegister);
        return responsed;
    }


    // Activate Deactivated Member
    // Get the Profile Items to Save Registration MemberRegisterUpdateResponse
    @PostMapping(path="/deactivate-member")
    public MemberRegisterUpdateResponse DeactivateMemberDetails(@RequestParam("phone") String phone){

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
        updatepayload.setFullNames(profile.getPayload().getMemberName());
        updatepayload.setEmail("any@gmail.com");
        updatepayload.setMobileNumber(phone);
        updatepayload.setChurchCode(profile.getPayload().getMemberName());
        updatepayload.setPreferredLanguage(profile.getPayload().getPreferredLanguage());
        updatepayload.setPhoneNumberPrivacy("Normal");
        updatepayload.setResidence("Any");
        updatepayload.setPhoneOwner(false);
        updatepayload.setIsMember("true");
        updatepayload.setGivingReceiptedTo("Self");
        updatepayload.setMembershipNumber(profile.getPayload().getMembershipNumber());
        updatepayload.setOtherPhoneNumber("+254");
        updatepayload.setSessionNumber(rand);

        authMemberRegister.setUpdatepayload(updatepayload);

        System.out.println("Update Payload: " + updatepayload.toString());
        System.out.println("Auth Member Registration: "+ HelperUtility.toJSON(authMemberRegister));
        MemberRegisterUpdateResponse responsed = authApi.getMemberRegistrationUpdate(authMemberRegister);
        return responsed;
    }

}
