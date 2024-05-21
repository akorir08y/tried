package com.example.tried.controller;

import com.example.tried.auth.dto.MemberProfile;
import com.example.tried.auth.dto.MemberProfileResponse;
import com.example.tried.auth.dto.Profilepayload;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummary;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummaryResponse;
import com.example.tried.auth.personnel.reports.offering.LocalChurchPayload;
import com.example.tried.services.AuthApi;
import com.example.tried.services.reports.excel.LocalChurchOfferingReportExcel;
import com.example.tried.services.reports.pdf.LocalChurchOfferingReport;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    AuthApi authApi;

    @Autowired
    LocalChurchOfferingReport localChurchOfferingReport;

    // Get Local Church Offering Report Excel
    @GetMapping("/export/local_church_offering_report/excel")
    public void getLocalChurchOfferingReportExcel( String username, String password, String phone_number,
                                                   String start_date, String end_date, String means_of_payment, String input,
                                                   HttpServletResponse Outresponse) throws IOException {
        // Get the Phone Number
        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1, phone_number.length());
        }

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
        System.out.println(responsed);
        LocalChurchOfferingReportExcel localChurchOfferingResponseExcel = new LocalChurchOfferingReportExcel();
        localChurchOfferingResponseExcel.export(Outresponse, responsed);
    }

    // Get Local Church Offering Report PDF
    @GetMapping("/export/local_church_offering_report/pdf")
    public void getLocalChurchOfferingReportPDF(String username, String password, String phone_number, String start_date,
                                                String end_date, String means_of_payment, String input, HttpServletResponse Outresponse) throws IOException {

        // Get the Phone Number
        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1, phone_number.length());
        }


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
        System.out.println(responsed);
        localChurchOfferingReport.LocalChurchSummaryReport(Outresponse,phone_number,start_date,end_date,responsed);
    }

    // Get Local Church Offering Report
    @GetMapping("/export/local_church_offering_report")
    public void getLocalChurchOfferingReport(@RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("phone_number") String phone_number,
                                             @RequestParam("start_date") String start_date,
                                             @RequestParam("end_date") String end_date,
                                             @RequestParam("means") String means_of_payment,
                                             @RequestParam("input") String input,HttpServletResponse Outresponse) throws IOException {

        if(input.contains("PDF")){
            getLocalChurchOfferingReportPDF(username, password, phone_number, start_date, end_date, means_of_payment, input, Outresponse);
        }else if(input.contains("Excel")){
            getLocalChurchOfferingReportExcel(username, password, phone_number, start_date, end_date, means_of_payment, input, Outresponse);
        }
        System.out.println("Local Church Offering Successfully Downloaded");
    }


    @PostMapping("/export/local_church_offering_report/html")
    public LocalChurchOfferingSummaryResponse getLocalChurchOfferingReportHTML(@RequestParam("username") String username,
                                                                               @RequestParam("password") String password,
                                                                               @RequestParam("phone_number") String phone_number,
                                                                               @RequestParam("start_date") String start_date,
                                                                               @RequestParam("end_date") String end_date,
                                                                               @RequestParam("means") String means_of_payment,HttpServletResponse Outresponse) throws IOException {

        // Get the Phone Number
        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1, phone_number.length());
        }

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

        LocalChurchOfferingSummaryResponse response2 = authApi.getLocalChurchOfferingReports(localChurchOfferingSummary);
        System.out.println(response2);
        return response2;
    }
}
