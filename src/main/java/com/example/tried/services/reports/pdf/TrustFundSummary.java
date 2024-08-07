package com.example.tried.services.reports.pdf;

import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dashboard.trust_funds.TransactionsItem;
import com.example.tried.auth.dto.MemberProfile;
import com.example.tried.auth.dto.MemberProfileResponse;
import com.example.tried.auth.dto.Profilepayload;
import com.example.tried.auth.member.*;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.reports.payment_mode.TrustFundsSummaryWithPaymentMode;
import com.example.tried.auth.reports.payment_mode.TrustFundsSummaryWithPaymentModeResponse;
import com.example.tried.config.FileStorageProperties;
import com.example.tried.dto.account.OfferStatement;
import com.example.tried.exception.FileStorageException;
import com.example.tried.services.AuthApi;
import com.example.tried.services.PersonnelApi;
import com.example.tried.utils.HelperUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class TrustFundSummary {

    // Resource Loader
    @Autowired
    ResourceLoader resourceLoader;

    // Decimal Format
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Font Sizes
    public static float font_size = 9f;
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

    @Autowired
    PersonnelApi personnelApi;

    private PdfFontFactory pdfFontFactory;
    // private PdfWriter pdfWriter;


    public void trustFundSummaryReport(TrustFundsSummaryWithPaymentMode trustFundSummary, HttpServletResponse OutputResponse,
                                       String church_code, String church_name, String phone_number, String personnel_name) throws IOException, NullPointerException {

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

        // Generate the PDF
        PdfWriter pdfWriter = new PdfWriter(OutputResponse.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);


        // Adding Image
        Table image = new Table(1);
        addImage(image);

        // Adding Header Data
        Table table = new Table(1);
        table.setBorder(Border.NO_BORDER);
        addHeaderData(table, church_code, church_name, phone_number);

        // Horizontal Header
        Table invoice_header = new Table(1);
        addHorizontalLine(invoice_header,trustFundSummary);

        // Data Generated By Trust Fund Summary Report
        // float width2 [] = {140,140,140,140};
        Table summary_report = new Table(11);
        getTrustFundSummaryReport(summary_report,trustFundSummary);

        float[] width = {100,170,100,170};
        Table signature = new Table(width);
        getSignature(signature);

        Table footer = new Table(1);
        accountSummaryFooter(footer, personnel_name);


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
        image.scaleToFit(180,120);
        table.setWidthPercent(50).
                setBorder(Border.NO_BORDER);

        table.addCell(new Cell().add(image.scaleAbsolute(180, 120))
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP).setBorder(Border.NO_BORDER));
    }

    private void addHeaderData(Table table, String church_code, String church_name, String phone_number) throws JsonProcessingException {

        // Session Numbers
        final int session_number = (int) ((Math.random() * 9000000) + 1000000);

        // church_code, phone_number, church_name

        // Request Church Details with Code
        RequestChurchDetailsWithCode requestDetails = new RequestChurchDetailsWithCode();
        Churchpayload churchPayload = new Churchpayload();
        churchPayload.setChurchCode(church_code);
        churchPayload.setAccessNumber(phone_number);
        churchPayload.setMobileServiceProvider("Safaricom");
        churchPayload.setSessionNumber(session_number);
        requestDetails.setChurchpayload(churchPayload);

        System.out.println("Request Church Details: "+ requestDetails);

        RequestChurchDetailsWithCodeResponse response1 = authApi.getChurchCodeDetails(requestDetails);
        System.out.println("Request Church Code with Details: "+ response1);

        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        table.setWidthPercent(50);

        table.addCell(new Cell().add("Seventh Day Adventist")
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER).setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(church_name)
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

        table.addCell(new Cell().add(response1.getConferenceName())
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.setFixedPosition(380, 720, 150);
    }

    private void addHorizontalLine(Table invoice_header, TrustFundsSummaryWithPaymentMode trustFundSummary){
        Border b2 = new SolidBorder(new DeviceRgb(53, 133, 146), 4);

        String Church_Name = trustFundSummary.getPayload().getChurchName();
        LocalDate date = LocalDate.now();
        Integer Month = trustFundSummary.getPayload().getMonth();
        String Year = String.valueOf(trustFundSummary.getPayload().getYear());

        List<String> list = new ArrayList<String>();

        // Instantiating list using Collections.addAll()
        Collections.addAll(list, "January", "February", "March", "April", "May", "June", "July","August", "September", "October", "November", "December");

        Integer Month_Number = Month - 1;
        String month_name = list.get(Month_Number);

        invoice_header.setBorder(Border.NO_BORDER);

        invoice_header.addCell(new Cell().add("Full Trust Fund Summary Report "+ Church_Name + " In " + month_name + " " + Year).setBorder(Border.NO_BORDER))
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

    private void getTrustFundSummaryReport(Table summaryReport, TrustFundsSummaryWithPaymentMode trustFundSummary) throws JsonProcessingException, NullPointerException {

        System.out.println("Trust Fund Summary Report: " + HelperUtility.toJSON(trustFundSummary));

        TrustFundsSummaryWithPaymentModeResponse localChurchTrustFund = personnelApi.getTrustFundSummaryWithPaymentMode(trustFundSummary);

        System.out.println("Local Church Trust Fund Summary Response: " + HelperUtility.toJSON(localChurchTrustFund));

        List<com.example.tried.auth.reports.payment_mode.TransactionsItem> transactions = localChurchTrustFund.getPayload().getTransactions();

        summaryReport.addCell(new Cell().add("Receipt No")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Time")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);


        summaryReport.addCell(new Cell().add("Contributor Name")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Mode of Payment")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Tithe")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Combined")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);
        summaryReport.addCell(new Cell().add("Camp")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Conf Dev")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Thirteenth")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Total")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Balance")).setFontSize(7)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        for(com.example.tried.auth.reports.payment_mode.TransactionsItem transaction : transactions){

            if (transaction.getReceiptNumber() != null) {
                summaryReport.addCell(new Cell().add(transaction.getReceiptNumber())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(Color.WHITE);
            }else if(transaction.getReceiptNumber() == null) {
                summaryReport.addCell(new Cell().add("")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getTransactionDate() != null){
                summaryReport.addCell(new Cell().add(transaction.getTransactionDate())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else {
                summaryReport.addCell(new Cell().add("")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getContributorName() != null){
                summaryReport.addCell(new Cell().add(transaction.getContributorName())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else if(transaction.getContributorName() == null){
                summaryReport.addCell(new Cell().add("")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(transaction.getModeOfPayment() != null) {
                summaryReport.addCell(new Cell().add(transaction.getModeOfPayment())).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else if(transaction.getModeOfPayment() == null){
                summaryReport.addCell(new Cell().add("")).setFontSize(7)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }


            Border b2 = new SolidBorder(new DeviceRgb(0, 0, 0), 1);

            Table tithe = new Table(2);
            Table combined = new Table(2);
            Table camp = new Table(2);
            Table conf_dev = new Table(2);
            Table thirteenth = new Table(2);
            Table total = new Table(2);

            // Tithe and Tithe Paid
            tithe.addCell(new Cell().add("Rcvd").setBorderRight(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            tithe.addCell(new Cell().add("Paid").setBorderLeft(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderRight(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);

            tithe.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getTithe()))).setBorderRight(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            tithe.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getTithePaid()))).setBorderLeft(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);

            // Combined and Combined Paid

            combined.addCell(new Cell().add("Rcvd").setBorderRight(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            combined.addCell(new Cell().add("Paid").setBorderLeft(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderRight(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);


            combined.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getCombinedOfferings()))).setBorderRight(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            combined.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getCombinedOfferingsPaid()))).setBorderLeft(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);

            // Camping and Camping Paid
            camp.addCell(new Cell().add("Rcvd").setBorderRight(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            camp.addCell(new Cell().add("Paid").setBorderLeft(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderRight(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);


            camp.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getCampMeeting()))).setBorderRight(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            camp.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getCampMeetingPaid()))).setBorderLeft(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);


            // Conference Development and Conference Development Paid
            conf_dev.addCell(new Cell().add("Rcvd").setBorderRight(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            conf_dev.addCell(new Cell().add("Paid").setBorderLeft(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderRight(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);


            conf_dev.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getConferenceDevelopment()))).setBorderRight(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            conf_dev.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getConferenceDevelopmentPaid()))).setBorderLeft(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);

            // Thirteenth Sabbath and Thirteenth Sabbath Paid
            thirteenth.addCell(new Cell().add("Rcvd").setBorderRight(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            thirteenth.addCell(new Cell().add("Paid").setBorderLeft(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderRight(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);


            thirteenth.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getThirteenthSabbath()))).setBorderRight(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            thirteenth.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getThirteenthSabbathPaid()))).setBorderLeft(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);

            // Total Receipted Amount and Total Receipted Amount Paid
            total.addCell(new Cell().add("Rcvd").setBorderRight(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            total.addCell(new Cell().add("Paid").setBorderLeft(b2).setBorderBottom(b2)
                    .setBorderTop(Border.NO_BORDER).setBorderRight(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);


            total.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getTotalReceiptedAmount()))).setBorderRight(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);
            total.addCell(new Cell().add(String.valueOf(Double.parseDouble(transaction.getTotalReceiptedAmountPaid()))).setBorderLeft(b2).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)).setBorder(Border.NO_BORDER).setFontSize(6);


            summaryReport.addCell(new Cell().add(tithe)).setFontSize(8)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);

            summaryReport.addCell(new Cell().add(combined)).setFontSize(8)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);

            summaryReport.addCell(new Cell().add(camp)).setFontSize(8)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);

            summaryReport.addCell(new Cell().add(conf_dev)).setFontSize(8)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);

            summaryReport.addCell(new Cell().add(thirteenth)).setFontSize(8)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);

            summaryReport.addCell(new Cell().add(total)).setFontSize(8)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);

            summaryReport.addCell(new Cell().add(transaction.getBalance())).setFontSize(8)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);
        }

    }

    private void getSignature(Table signature) {

        signature.setMarginTop(50f);

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


    private void accountSummaryFooter(Table accountSum, String personnel_name){

        accountSum.addCell(new Cell().add("Statement generated by "+ personnel_name + ", " + new Date())
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
