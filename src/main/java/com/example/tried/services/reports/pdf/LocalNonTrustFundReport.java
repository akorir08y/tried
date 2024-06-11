package com.example.tried.services.reports.pdf;

import com.example.tried.auth.dto.MemberProfile;
import com.example.tried.auth.dto.MemberProfileResponse;
import com.example.tried.auth.dto.Profilepayload;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem;
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
import java.util.*;

@Service
public class LocalNonTrustFundReport {

    // Resource Loader
    @Autowired
    ResourceLoader resourceLoader;

    List<String> keys = new ArrayList<String>();
    List<String> keys_unfiltered = new ArrayList<String>();

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

    private PdfFontFactory pdfFontFactory;
    // private PdfWriter pdfWriter;


    public void nonTrustFundSummaryReport(HttpServletResponse response, String phone_number,
                                          String start_date, String end_date, List<HashMap<String, Object>> membersItems,
                                          HashMap<String, Integer> churchFunds, String personnel_name, String church_name,
                                          String district_name, String conference_name) throws IOException , NullPointerException{

        // Load Resource Fonts
        Resource REGULAR = resourceLoader.getResource("classpath:fonts/trebuc.ttf");
        Resource BOLD = resourceLoader.getResource("classpath:fonts/Trebuchet MS.ttf");
        Resource ITALIC = resourceLoader.getResource("classpath:fonts/TrebuchetMSItalic.ttf");
        Resource BOLDEN = resourceLoader.getResource("classpath:fonts/trebucbd.ttf");


        // Get the List of All the Accounts in the Churches
        for (Map.Entry<String,Integer> mapElement : churchFunds.entrySet()) {
            String key = mapElement.getKey();
            if(!key.contains("local_combined_offerings")){
                keys_unfiltered.add(key);
            }
            if(key.contains("_")) {
                key = key.replace("_", " ");
                if(!key.contains("local combined offerings")){
                    keys.add(key);
                }
            }else{
                keys.add(key);
            }

        }

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
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A1);


        // Adding Image
        Table image = new Table(1);
        addImage(image);

        // Adding Header Data
        Table table = new Table(1);
        table.setBorder(Border.NO_BORDER);
        addHeaderData(table,church_name, district_name, conference_name);

        // Horizontal Header
        Table invoice_header = new Table(1);
        addHorizontalLine(invoice_header,start_date, end_date,church_name);


        int columns = 6 + keys.size();
        // Data Generated By Trust Fund Summary Report
        Table summary_report = new Table(columns);
        getTrustFundSummaryReport(summary_report,membersItems,keys);


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
        table.setWidthPercent(90).
                setBorder(Border.NO_BORDER);

        table.addCell(new Cell().add(image.scaleAbsolute(180, 120))
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP).setBorder(Border.NO_BORDER));
    }

    private void addHeaderData(Table table,String church_name,String district_name, String conference_name) throws JsonProcessingException {
        table.setWidthPercent(10);
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

        table.addCell(new Cell().add(district_name)
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(conference_name)
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.setHorizontalAlignment(HorizontalAlignment.RIGHT).
                setVerticalAlignment(VerticalAlignment.TOP).setMarginTop(-120f);
    }

    private void addHorizontalLine(Table invoice_header,String start_date,String end_date,String church_name){
        Border b2 = new SolidBorder(new DeviceRgb(53, 133, 146), 4);

        LocalDate date = LocalDate.now();
        String Month = String.valueOf(date.getMonth());

        invoice_header.setBorder(Border.NO_BORDER);

        invoice_header.addCell(new Cell().add("Full Non Trust Fund Summary Report "+church_name +" From " +start_date + " To "+ end_date).setBorder(Border.NO_BORDER))
                .setFontSize(8)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBold()
                .setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(15f)
                .setBackgroundColor(Color.WHITE);
    }

    private void getTrustFundSummaryReport(Table summaryReport, List<HashMap<String, Object>> membersItems, List<String> keys) {
        summaryReport.addCell(new Cell().add("Receipt Number")).setFontSize(6)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Member Name")).setFontSize(6)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);


        summaryReport.addCell(new Cell().add("Member Number")).setFontSize(6)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Means of Payment")).setFontSize(6)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        summaryReport.addCell(new Cell().add("Local Combined Offerings")).setFontSize(6)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);

        for(int i = 0; i < keys.size(); i++) {
            summaryReport.addCell(new Cell().add(keys.get(i))).setFontSize(6)
                    .setFont(bolden)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(Color.WHITE);
        }

        summaryReport.addCell(new Cell().add("Total Amount")).setFontSize(6)
                .setFont(bolden)
                .setTextAlignment(TextAlignment.CENTER)
                .setBackgroundColor(Color.WHITE);



        for(HashMap<String, Object> membersItem : membersItems){

            if (membersItem.get("receiptNumber") != null) {
                summaryReport.addCell(new Cell().add(String.valueOf(membersItem.get("receiptNumber")))).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("")).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(membersItem.get("memberName") != null){
                summaryReport.addCell(new Cell().add(String.valueOf(membersItem.get("memberName")))).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else {
                summaryReport.addCell(new Cell().add("")).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(membersItem.get("memberNumber") != null){
                summaryReport.addCell(new Cell().add(String.valueOf(membersItem.get("memberNumber")))).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("")).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(membersItem.get("meansOfPayment") != null) {
                summaryReport.addCell(new Cell().add(String.valueOf(membersItem.get("meansOfPayment")))).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("")).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            if(membersItem.get("local_combined_offerings") != null) {
                summaryReport.addCell(new Cell().add(String.valueOf(membersItem.get("local_combined_offerings")))).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("")).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }

            for(int i = 0; i < keys.size(); i++) {
                if(membersItem.get(keys.get(i)) != null) {
                    summaryReport.addCell(new Cell().add(String.valueOf(membersItem.get(keys.get(i))))).setFontSize(6)
                            .setFont(bolden)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBackgroundColor(Color.WHITE);
                }else{
                    summaryReport.addCell(new Cell().add("")).setFontSize(6)
                            .setFont(bolden)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBackgroundColor(Color.WHITE);
                }
            }


            if(membersItem.get("totalAmount") != null) {
                summaryReport.addCell(new Cell().add(String.valueOf(membersItem.get("totalAmount")))).setFontSize(6)
                        .setFont(bolden)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(Color.WHITE);
            }else{
                summaryReport.addCell(new Cell().add("")).setFontSize(6)
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
