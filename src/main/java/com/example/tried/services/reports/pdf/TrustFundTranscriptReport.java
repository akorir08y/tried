package com.example.tried.services.reports.pdf;

import com.example.tried.auth.dto.MemberProfile;
import com.example.tried.auth.dto.MemberProfileResponse;
import com.example.tried.auth.dto.Profilepayload;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.reports.transcript.TransactionItem;
import com.example.tried.auth.personnel.reports.transcript.TrustFundTranscriptResponse;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracing;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracingResponse;
import com.example.tried.auth.personnel.tracing.TransactionsItem;
import com.example.tried.services.AuthApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TrustFundTranscriptReport {

    // Resource Loader
    @Autowired
    ResourceLoader resourceLoader;

    // Decimal Format
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Font Sizes
    public static float font_size = 8f;
    public static float font_size7 = 7f;

    public static float font_size8 = 15f;
    public static float heading_font = 12f;

    // Font Types
    PdfFont font;
    PdfFont bold;
    PdfFont italic;

    PdfFont bolden;

    // Get User Information
    @Autowired
    AuthApi authApi;

    private PdfFontFactory pdfFontFactory;
    // private PdfWriter pdfWriter;


    public void trustFundTranscriptReport(String phoneNumber, HttpServletResponse OutputResponse, String start_date, String end_date,
                                          String username, String password, TrustFundTranscriptResponse responsed) throws IOException {

        // Load Resource Fonts
        Resource REGULAR = resourceLoader.getResource("classpath:fonts/trebuc.ttf");
        Resource BOLD = resourceLoader.getResource("classpath:fonts/Trebuchet MS.ttf");
        Resource ITALIC = resourceLoader.getResource("classpath:fonts/TrebuchetMSItalic.ttf");
        Resource BOLDEN = resourceLoader.getResource("classpath:fonts/trebucbd.ttf");

        // Start by doing Font
        try {
            System.out.println("Regular: "+ REGULAR);
            font = PdfFontFactory.createFont(REGULAR.getFile().toString(), PdfEncodings.WINANSI);
            bold = PdfFontFactory.createFont(BOLD.getFile().toString(), PdfEncodings.WINANSI);
            italic = PdfFontFactory.createFont(ITALIC.getFile().toString(), PdfEncodings.WINANSI);
            bolden = PdfFontFactory.createFont(BOLDEN.getFile().toString(), PdfEncodings.WINANSI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(phoneNumber.startsWith("+254")){
            phoneNumber = phoneNumber.substring(1, phoneNumber.length());
        }

        // Trust Fund Summary
        // Get the Member Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + phoneNumber);
        profile.setProfilepayload(payload);

        // Get User Profile Information
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        String church_code = response.getPayload().getChurchCode();

        // Generate the PDF
        PdfWriter pdfWriter = new PdfWriter(OutputResponse.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A1);


        // Adding Image
        Table image = new Table(1);
        addImage(image);

        // Adding Header Data
        Table table = new Table(1);
        table.setBorder(Border.NO_BORDER);
        addHeaderData(table,response,phoneNumber);

        // Horizontal Header
        Table invoice_header = new Table(1);
        addHorizontalLine(invoice_header,start_date,end_date);

        // Data Generated By Trust Fund Summary Report
        int columns = 28;
        Table summary_report = new Table(columns);
        getTrustFundTranscriptReport(summary_report,phoneNumber,responsed,start_date,end_date,username,password);

        float width1 [] = {100,170,100,170};
        Table signature = new Table(width1);
        getSignature(signature);

        Table footer = new Table(1);
        accountSummaryFooter(footer, response);


        document.add(image);
        document.add(table);
        document.add(invoice_header);
        document.add(summary_report);
        document.add(signature);
        document.add(footer);
        document.close();
        System.out.println("PDF Created!");

    }


    private void addImage(Table table){
        Resource res = resourceLoader.getResource("classpath:static/images/Logo.png");

        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create(res.getInputStream().readAllBytes());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(imageData);
        table.setWidthPercent(90).
                setBorder(Border.NO_BORDER);

        table.addCell(new Cell().add(image.scaleAbsolute(400, 200))
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP).setBorder(Border.NO_BORDER));
    }

    private void addHeaderData(Table table, MemberProfileResponse response,String phoneNumber) {
        // Member Church Details Response
        RequestChurchDetails churchDetails = new RequestChurchDetails();
        churchDetails.setChurchCode(response.getPayload().getChurchCode());
        churchDetails.setAccessNumber(phoneNumber);
        churchDetails.setMobileServiceProvider("Safaricom");


        RequestChurchDetailsResponse response1 = authApi.getMemberChurchDetails(churchDetails);

        table.setWidthPercent(10);

        table.addCell(new Cell().add("Seventh Day Adventist")
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER).setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(response.getPayload().getChurchName())
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(" ")
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(" ")
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(response1.getDistrictName())
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(response.getPayload().getConferenceName())
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.setHorizontalAlignment(HorizontalAlignment.RIGHT).
                setVerticalAlignment(VerticalAlignment.TOP).setMarginTop(-120f);
    }

    private void addHorizontalLine(Table invoice_header, String start_date, String end_date){
        Border b2 = new SolidBorder(new DeviceRgb(53, 133, 146), 4);


        LocalDate date = LocalDate.now();
        String Month = String.valueOf(date.getMonth());

        invoice_header.setBorder(Border.NO_BORDER);

        invoice_header.addCell(new Cell().add("Trust Fund Transcript from "+ start_date + " to " +end_date).setBorder(Border.NO_BORDER))
                .setFontSize(9)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBold()
                .setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(15f)
                .setBackgroundColor(Color.WHITE);
    }

    private void getTrustFundTranscriptReport(Table summaryReport,String start_date ,TrustFundTranscriptResponse transcript, String end_date,
                                            String phone_number, String username, String password) throws JsonProcessingException {


        if(phone_number.startsWith("+254")){
            phone_number = phone_number.substring(1,phone_number.length());
        }

        // Get the Member Details
        MemberProfile profile = new MemberProfile();
        Profilepayload ProfilePayload = new Profilepayload();
        ProfilePayload.setFromWithin(true);
        ProfilePayload.setMobileNumber("+" + phone_number);
        profile.setProfilepayload(ProfilePayload);

        // Get User Profile Information
        MemberProfileResponse responsed = authApi.getMemberDetails(profile);

        // Session Numbers
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);
        personnel.setChurchCode(responsed.getPayload().getChurchCode());

        // Get the Personnel Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);


        List<TransactionItem> transactions = transcript.getTrpayload().getTransactions();

        summaryReport.addCell(new Cell().add("Conference Settlement Reference")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Transaction Receipt Number")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);


        summaryReport.addCell(new Cell().add("Transaction Initiation Time")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Contributor Name")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Contributor Contact")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Church Code")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Member Id")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Receiver Name")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Transaction Total Amount")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Means Of Giving")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Trust Fund")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Settlement At Clearance Account")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Method Of Collection")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Tithe")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Camp Meeting")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Combined Offerings")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Thirteenth Sabbath")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Conference Development")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("E-Money Reference")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("E-Money Notification Time")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Telco Transaction Time")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("E-Money Agent")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Conference Settlement Confirmation")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Confirmed In Bank Records")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Trust-Funds - Special Trust Fund")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Special Trust Funds")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Conference Number")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Conference Name")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        for(TransactionItem transaction : transactions){

            if (transaction.getConferenceSettlementReference() != null) {
                summaryReport.addCell(new Cell().add(transaction.getConferenceSettlementReference())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getTransactionReceiptNumber() != null){
                summaryReport.addCell(new Cell().add(transaction.getTransactionReceiptNumber())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else {
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getTransactionInitiationTime() != null){
                summaryReport.addCell(new Cell().add(transaction.getTransactionInitiationTime())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else {
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }


            if(transaction.getContributorName() != null) {
                summaryReport.addCell(new Cell().add(transaction.getContributorName())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getContributorContact() != null) {
                summaryReport.addCell(new Cell().add(transaction.getContributorContact())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }


            if(transaction.getChurchCode() != null) {
                summaryReport.addCell(new Cell().add(transaction.getChurchCode())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getMemberId() != null) {
                summaryReport.addCell(new Cell().add(transaction.getMemberId())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getReceiverName() != null) {
                summaryReport.addCell(new Cell().add(transaction.getReceiverName())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }


            if(transaction.getTransactionTotalAmount() != null) {
                summaryReport.addCell(new Cell().add(transaction.getTransactionTotalAmount())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getMeansOfGiving() != null) {
                summaryReport.addCell(new Cell().add(transaction.getMeansOfGiving())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getTrustFund() != null) {
                summaryReport.addCell(new Cell().add(transaction.getTrustFund())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getSettlementAtClearenceAccount() != null) {
                summaryReport.addCell(new Cell().add(transaction.getSettlementAtClearenceAccount())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getMethodOfCollection() != null) {
                summaryReport.addCell(new Cell().add(transaction.getMethodOfCollection())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }


            if(transaction.getTithe() != null) {
                summaryReport.addCell(new Cell().add(transaction.getTithe())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getCampMeeting() != null) {
                summaryReport.addCell(new Cell().add(transaction.getCampMeeting())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getCombinedOfferings() != null) {
                summaryReport.addCell(new Cell().add(transaction.getCombinedOfferings())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getThirteenthSabbath() != null) {
                summaryReport.addCell(new Cell().add(transaction.getThirteenthSabbath())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getConferenceDevelopment() != null) {
                summaryReport.addCell(new Cell().add(transaction.getConferenceDevelopment())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getEMoneyReference() != null) {
                summaryReport.addCell(new Cell().add(transaction.getEMoneyReference())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getEMoneyNotificationTime() != null) {
                summaryReport.addCell(new Cell().add(transaction.getEMoneyNotificationTime())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getTelcoTransactionTime() != null) {
                summaryReport.addCell(new Cell().add(transaction.getTelcoTransactionTime())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getEMoneyAgent() != null) {
                summaryReport.addCell(new Cell().add(transaction.getEMoneyAgent())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getConferenceSettlementConfirmation() != null) {
                summaryReport.addCell(new Cell().add(transaction.getConferenceSettlementConfirmation())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getConfirmedInBankRecords() != null) {
                summaryReport.addCell(new Cell().add(transaction.getConfirmedInBankRecords())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getTrustFundsLessSpecialTrustFund() != null) {
                summaryReport.addCell(new Cell().add(transaction.getTrustFundsLessSpecialTrustFund())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getSpecialTrustFunds() != null) {
                summaryReport.addCell(new Cell().add(transaction.getSpecialTrustFunds())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getConferenceNumber() != null) {
                summaryReport.addCell(new Cell().add(transaction.getConferenceNumber())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getConferenceName() != null) {
                summaryReport.addCell(new Cell().add(transaction.getConferenceName())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("0")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }


        }

    }

    private void getSignature(Table signature) {

        signature.setMarginTop(50f);
        signature.setWidthPercent(100);

        signature.addCell(new Cell().add("Treasurer").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));

        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setBorderLeft(Border.NO_BORDER)
                .setMarginBottom(10f)
                .setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER));

        signature.addCell(new Cell().add("Signature").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));


        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setBorderLeft(Border.NO_BORDER)
                .setMarginBottom(10f)
                .setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER));

        signature.addCell(new Cell().add("Church Elder").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));

        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setMarginBottom(10f)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER));

        signature.addCell(new Cell().add("Signature").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));


        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setMarginBottom(10f)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER));


        signature.addCell(new Cell().add("Pastor").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));

        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setMarginBottom(10f)
                .setBorderTop(Border.NO_BORDER));

        signature.addCell(new Cell().add("Signature").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));


        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setMarginBottom(10f)
                .setBorderTop(Border.NO_BORDER));

        signature.addCell(new Cell().add("Conference Internal Auditor").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));

        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setMarginBottom(10f)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER));

        signature.addCell(new Cell().add("Signature").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10f)
                .setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));


        signature.addCell(new Cell().add("").setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE)
                .setBorderLeft(Border.NO_BORDER)
                .setMarginBottom(10f)
                .setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER));
    }


    private void accountSummaryFooter(Table accountSum, MemberProfileResponse profile){

        accountSum.addCell(new Cell().add("Statement generated by "+profile.getPayload().getMemberName() + ", " + new Date())
                .setFontSize(8)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(30f)
                .setBold()
                .setBackgroundColor(Color.WHITE));

        accountSum.addCell(new Cell().add("SDA CFMS © " + Calendar.getInstance().get(Calendar.YEAR))
                .setFontSize(9)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(5f)
                .setBold()
                .setBackgroundColor(Color.WHITE));
    }
}