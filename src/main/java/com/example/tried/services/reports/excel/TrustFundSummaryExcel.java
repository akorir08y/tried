package com.example.tried.services.reports.excel;

import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dashboard.trust_funds.TransactionsItem;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.services.AuthApi;
import com.example.tried.utils.HelperUtility;
import com.itextpdf.io.IOException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class TrustFundSummaryExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    @Autowired
    private AuthApi authApi;

    public TrustFundSummaryExcel() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        // Row Number 1

        createCell(row, 0, "Receipt No", style);
        createCell(row, 1, "Time", style);
        createCell(row, 2, "ContributorName", style);
        createCell(row, 3, "Mode Of Payment", style);
        createCell(row, 4, "Tithe", style);
        createCell(row, 5, "Combined", style);
        createCell(row, 6, "Camp", style);
        createCell(row, 7, "Conf. Dev", style);
        createCell(row, 8, "Thirteenth", style);
        createCell(row, 9, "Total", style);
        createCell(row, 10, "Balance", style);

        // Row Number 2
        createCell(row, 0, "", style);
        createCell(row, 1, "", style);
        createCell(row, 2, "", style);
        createCell(row, 3, "", style);
        createCell(row, 4, "Received", style);
        createCell(row, 4, "Paid", style);
        createCell(row, 5, "Received", style);
        createCell(row, 5, "Paid", style);
        createCell(row, 6, "Received", style);
        createCell(row, 6, "Paid", style);
        createCell(row, 7, "Received", style);
        createCell(row, 7, "Paid", style);
        createCell(row, 8, "Received", style);
        createCell(row, 8, "Paid", style);
        createCell(row, 9, "Received", style);
        createCell(row, 9, "Paid", style);
        createCell(row, 10, "", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 2;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);


        String username = "mwakesho";
        String password = "0389";

        // Session Numbers
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);

        // Get the Personnel Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);

        // Personnel Details
        String church_code = response.getPayload().getOrganisationNumber();
        String church_name = response.getPayload().getOrganisationName();
        String church_level = response.getPayload().getOrganisationLevel();
        String personnel_name = response.getPayload().getPersonnelName();
        String member_no = response.getPayload().getPersonnelCfmsNumber();

        // Get the Previous Month Trust Fund Summary
        LocalChurchTrustFundSummary trustFundSummary = new LocalChurchTrustFundSummary();
        com.example.tried.auth.dashboard.trust_funds.Payload payload = new com.example.tried.auth.dashboard.trust_funds.Payload();

        // Get the Current Year
        LocalDate localdate = LocalDate.now();

        // Trust Fund Summary Payload
        payload.setYear(localdate.getYear());
        payload.setMonth(localdate.getMonthValue() - 1);
        payload.setChurchName(church_name);
        payload.setLocalChurchNumber(church_code);
        trustFundSummary.setPayload(payload);

        //
        com.example.tried.auth.dashboard.trust_funds.Authentication authentication1 = new com.example.tried.auth.dashboard.
                trust_funds.Authentication();

        authentication1.setInstututionLevel(church_level);
        authentication1.setUser(username);
        authentication1.setPersonnelName(personnel_name);
        authentication1.setPassword(password);
        authentication1.setSessionNumber(rand);
        authentication1.setInstututionName(church_name);
        authentication1.setInstututionNumber(church_code);
        trustFundSummary.setAuthentication(authentication1);

        System.out.println("Local Church Trust Fund Summary: " + HelperUtility.toJSON(trustFundSummary));

        LocalChurchTrustFundSummaryResponse localChurchTrustFund = authApi.getLocalChurchTrustFundSummary(trustFundSummary);

        System.out.println("Local Church Trust Fund Summary Response: " + HelperUtility.toJSON(localChurchTrustFund));

        List<TransactionsItem> transactions = localChurchTrustFund.getTrupayload().getTransactions();


        /*
             createCell(row, 0, "Receipt No", style);
        createCell(row, 1, "Time", style);
        createCell(row, 2, "ContributorName", style);
        createCell(row, 3, "Mode Of Payment", style);
        createCell(row, 4, "Tithe", style);
        createCell(row, 5, "Combined", style);
        createCell(row, 6, "Camp", style);
        createCell(row, 7, "Conf. Dev", style);
        createCell(row, 8, "Thirteenth", style);
        createCell(row, 9, "Total", style);
        createCell(row, 10, "Balance", style);

         */

        for (TransactionsItem transaction : transactions) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, transaction.getReceiptNumber(), style);
            createCell(row, columnCount++, transaction.getTransactionDate(), style);
            createCell(row, columnCount++, transaction.getContributorName(), style);
            createCell(row, columnCount++, transaction.getModeOfPayment(), style);
            createCell(row, columnCount++, "Active", style);
        }
    }

    public void export(HttpServletResponse response) throws IOException, java.io.IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
