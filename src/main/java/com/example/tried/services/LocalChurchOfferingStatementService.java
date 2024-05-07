package com.example.tried.services;


import com.example.tried.auth.dto.MemberProfile;
import com.example.tried.auth.dto.MemberProfileResponse;
import com.example.tried.auth.dto.Profilepayload;
import com.example.tried.auth.financial.*;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.auth.reports.statements.Authentication;
import com.example.tried.auth.reports.statements.LocalChurchOfferingStatement;
import com.example.tried.auth.reports.statements.LocalChurchOfferingStatementResponse;
import com.example.tried.auth.reports.statements.MembersItem;
import com.example.tried.auth.reports.statements.Payload;
import com.example.tried.config.FileStorageProperties;
import com.example.tried.dto.account.OfferStatement;
import com.example.tried.exception.FileStorageException;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LocalChurchOfferingStatementService {
    // Resource Loader
    @Autowired
    ResourceLoader resourceLoader;

    // Decimal Format
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Font Sizes
    public static float font_size = 12f;
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

    private final Path fileStorageLocation;

    @Autowired
    public LocalChurchOfferingStatementService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }


    public void createOfferingStatement(LocalChurchOfferingStatement statement, HttpServletResponse OutputResponse) throws IOException {

        String PhoneNumber = statement.getAuthentication().getPhoneNumber();

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


        // Get the Member Details
        MemberProfile profile = new MemberProfile();
        Profilepayload payload = new Profilepayload();
        payload.setFromWithin(true);
        payload.setMobileNumber("+" + PhoneNumber);
        profile.setProfilepayload(payload);


        // Get User Profile Information
        MemberProfileResponse response = authApi.getMemberDetails(profile);

        // Generate the PDF
        PdfWriter pdfWriter = new PdfWriter(OutputResponse.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.getDefaultPageSize();


        // Adding Image
        Table image = new Table(1);
        addImage(image);

        // Adding Header Data
        Table table = new Table(1);
        table.setBorder(Border.NO_BORDER);
        addHeaderData(table,response,PhoneNumber);

        // Horizontal Header
        Table invoice_header = new Table(1);
        addHorizontalLine(invoice_header);

        // User Information Table
        Table UserInfo = new Table(2);
        userInformation(UserInfo,response,PhoneNumber, statement);

        // Horizontal Footer
        Table invoice_header2 = new Table(1);
        addHorizontalFooter(invoice_header2);

        // Account Summary Header
        Table accounts = new Table(1);
        accountSummary(accounts);

        // Get the Offering Statement Data
        Table statements = new Table(5);
        getOfferingData(statements, statement, response);

        // Account Summary Footer
        Table footer = new Table(1);
        accountSummaryFooter(footer, response);

        document.add(image);
        document.add(table);
        document.add(invoice_header);
        document.add(UserInfo);
        document.add(invoice_header2);
        document.add(accounts);
        document.add(statements);
        document.add(footer);
        document.close();
        System.out.println("PDF Created!");

    }


    private void addImage(Table table){
        Resource res = resourceLoader.getResource("classpath:static/images/Logo.png");

        ImageData imageData = null;
        try {
            //imageData = ImageDataFactory.create(ClassLoader.getSystemResource("static/Logo.png").toURI().toURL());
            imageData = ImageDataFactory.create(res.getInputStream().readAllBytes());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(imageData);
        table.setWidthPercent(50).
                setBorder(Border.NO_BORDER);
        // table.setHeaderRows(0);

        table.addCell(new Cell().add(image.scaleAbsolute(250, 80))
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


        table.setWidthPercent(50);

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

        table.setFixedPosition(380, 720, 200);
    }

    private void addHorizontalLine(Table invoice_header){
        Border b2 = new SolidBorder(new DeviceRgb(53, 133, 146), 4);

        invoice_header.addCell(new Cell().add("Local Church Statement")
                .setFontSize(15)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBorderBottom(b2)
                .setBold()
                .setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(15f)
                .setBackgroundColor(Color.WHITE));
    }

    private void userInformation(Table table, MemberProfileResponse response,String PhoneNumber,
                                 LocalChurchOfferingStatement statement){

        table.setBorder(Border.NO_BORDER);

        // Tabled Information
        table.setBorder(Border.NO_BORDER);
        table.addCell(new Cell().add(response.getPayload().getMemberName())
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER).setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add("Tithe & Offering Statement")
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER).setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add(PhoneNumber)
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

        table.addCell(new Cell().add("From "+ statement.getPayload().getStartDate() +" to "+ statement.getPayload().getEndDate())
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));


        table.addCell(new Cell().add("CFMS No. "+response.getPayload().getMembershipNumber())
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));


        table.addCell(new Cell().add("Church Code. "+response.getPayload().getChurchCode())
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .setFont(bolden)
                .setBold()
                .setFontSize(font_size));

    }

    private void addHorizontalFooter(Table invoice_header){
        Border b2 = new SolidBorder(new DeviceRgb(53, 133, 146), 4);

        invoice_header.addCell(new Cell().add("")
                .setFontSize(15)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBorderBottom(b2)
                .setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(5f)
                .setBackgroundColor(Color.WHITE));
    }

    private void accountSummary(Table accountSum){

        accountSum.addCell(new Cell().add("Accounts Summary")
                .setFontSize(15)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(15f)
                .setBold()
                .setBackgroundColor(Color.WHITE));
    }

    private void getOfferingData(Table Statement, LocalChurchOfferingStatement statement,MemberProfileResponse profile) throws JsonProcessingException {

        // Table Headers
        Statement.addCell(new Cell().add("Date")
                .setFontSize(12)
                .setFont(bolden)
                .setWidthPercent(20)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setPaddingBottom(5f)
                .setMarginTop(15f)
                .setBold()
                .setBackgroundColor(Color.WHITE));

        Statement.addCell(new Cell().add("Description")
                .setFontSize(12)
                .setFont(bolden)
                .setWidthPercent(40)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setPaddingBottom(5f)
                .setMarginTop(15f)
                .setBold()
                .setBackgroundColor(Color.WHITE));

        Statement.addCell(new Cell().add("Refunds")
                .setFontSize(12)
                .setFont(bolden)
                .setWidthPercent(10)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setPaddingBottom(5f)
                .setMarginTop(15f)
                .setBold()
                .setBackgroundColor(Color.WHITE));

        Statement.addCell(new Cell().add("Givings")
                .setFontSize(12)
                .setFont(bolden)
                .setWidthPercent(10)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setPaddingBottom(5f)
                .setMarginTop(15f)
                .setBold()
                .setBackgroundColor(Color.WHITE));

        Statement.addCell(new Cell().add("Balance")
                .setFontSize(12)
                .setFont(bolden)
                .setWidthPercent(10)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setPaddingBottom(5f)
                .setMarginTop(15f)
                .setBold()
                .setBackgroundColor(Color.WHITE));



        LocalChurchOfferingStatementResponse response = personnelApi.getLocalChurchOfferingStatement(statement);

        List<com.example.tried.auth.reports.statements.MembersItem> members = response.getLspayload().getMembers();

        for(MembersItem mem : members) {
            Statement.addCell(new Cell().add(String.valueOf(mem.getTransactionDate()))
                    .setFontSize(10)
                    .setFont(font)
                    .setWidthPercent(20)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setPaddingBottom(5f)
                    .setBackgroundColor(Color.WHITE));

            Statement.addCell(new Cell().add(String.valueOf(mem.getNarration()))
                    .setFontSize(10)
                    .setFont(font)
                    .setWidthPercent(40)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setPaddingBottom(5f)
                    .setBackgroundColor(Color.WHITE));

                if (mem.getAmountReceived() == null){
                    Statement.addCell(new Cell().add(" ")
                            .setFontSize(10)
                            .setFont(bolden)
                            .setWidthPercent(10)
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.LEFT)
                            .setPaddingBottom(5f)
                            .setBackgroundColor(Color.WHITE));
                }else{
                    Statement.addCell(new Cell().add(mem.getAmountReceived())
                            .setFontSize(10)
                            .setFont(bolden)
                            .setWidthPercent(10)
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.LEFT)
                            .setPaddingBottom(5f)
                            .setBackgroundColor(Color.WHITE));
                }

            Statement.addCell(new Cell().add(String.valueOf(mem.getAmountRefunded()))
                    .setFontSize(10)
                    .setFont(bolden)
                    .setWidthPercent(10)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setPaddingBottom(5f)
                    .setBackgroundColor(Color.WHITE));


            Statement.addCell(new Cell().add("("+String.valueOf(mem.getBalance())+")")
                        .setFontSize(10)
                        .setFont(bolden)
                        .setWidthPercent(10)
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setPaddingBottom(5f)
                        .setBackgroundColor(Color.WHITE));

        }

    }


    private void accountSummaryFooter(Table accountSum, MemberProfileResponse profile){

        accountSum.addCell(new Cell().add("Statement generated by "+profile.getPayload().getMemberName() + ", " + new Date())
                .setFontSize(9)
                .setFont(bolden)
                .setWidthPercent(100)
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingBottom(10f)
                .setMarginTop(30f)
                .setBold()
                .setBackgroundColor(Color.WHITE));

        accountSum.addCell(new Cell().add("SDA CFMS © " + Calendar.getInstance().get(Calendar.YEAR))
                .setFontSize(11)
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
