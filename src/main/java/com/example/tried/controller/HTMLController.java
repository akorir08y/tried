package com.example.tried.controller;


import com.example.tried.config.MpesaConfiguration;
import com.example.tried.dto.c2b.SimulateC2BRequest;
import com.example.tried.dto.express.InternalSTKPushRequest;
import com.example.tried.services.DarajaApi;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.tried.utils.Constants.CUSTOMER_PAYBILL_ONLINE;
import static com.example.tried.utils.Constants.LAKEATTS_PAYBILL;


@Controller
@RequestMapping(value = {"/payment"})
public class HTMLController {

    @Autowired
    DarajaApi darajaApi;

    @Autowired
    MpesaConfiguration mpesaConfiguration;

    // Get the Mpesa Page
    @GetMapping(path="/stk-push-request")
    public String getMPesaSTKPage(){
        System.out.println("Loading up the Mpesa Push Request Page");
        return "stk-push-request";
    }

    // STK Push Request
    @PostMapping("/STK-Push")
    public @ResponseBody String stkPushRequest(@RequestParam("amount") String amount, @RequestParam("phone_number")
    String phone_number) throws JSONException {
        System.out.println("Getting the Amount and the Phone Number");

        // Get the Amount and Details to push the Request
        InternalSTKPushRequest internalSTKPushRequest = new InternalSTKPushRequest();
        internalSTKPushRequest.setAmount(amount);
        internalSTKPushRequest.setPhoneNumber(phone_number);

        // Print out Amount
        System.out.println("Phone Number: " + phone_number);
        System.out.println("Amount: " + amount);

        // Perform the STK Push Request
        darajaApi.performSTKPushTransaction(internalSTKPushRequest);

        return "STK Push Request has been Received Successfully";
    }


    // STK Push Request
    @PostMapping("/paybill")
    public @ResponseBody String paybillPayment(@RequestParam("amount") String amount, @RequestParam("phone_number")
    String phone_number) throws JSONException {
        System.out.println("Getting the Amount and the Phone Number For Paybill");

        // Get the Amount and Details to push the Request
        SimulateC2BRequest simulateC2BRequest = new SimulateC2BRequest();
        simulateC2BRequest.setAmount(amount);
        simulateC2BRequest.setMsisdn(phone_number);
        simulateC2BRequest.setCommandID(CUSTOMER_PAYBILL_ONLINE);
        simulateC2BRequest.setShortCode(mpesaConfiguration.getShortCode());
        simulateC2BRequest.setBillRefNumber(LAKEATTS_PAYBILL);

        // Print out Amount
        System.out.println("Phone Number: " + phone_number);
        System.out.println("Amount: " + amount);

        // Perform the STK Push Request
        darajaApi.simulateC2BTransaction(simulateC2BRequest);

        return "Paybill Payment Request has been Received Successfully";
    }
}
