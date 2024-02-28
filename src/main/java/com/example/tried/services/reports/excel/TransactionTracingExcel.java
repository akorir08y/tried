package com.example.tried.services.reports.excel;


import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracing;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracingResponse;
import com.example.tried.auth.personnel.tracing.TransactionsItem;
import com.example.tried.services.AuthApi;
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

import java.util.List;

@Service
public class TransactionTracingExcel {

    @Autowired
    AuthApi authApi;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public TransactionTracingExcel() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Transaction Tracing Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Transaction ID", style);
        createCell(row, 1, "Receiver ID", style);
        createCell(row, 2, "Trust Funds", style);
        createCell(row, 3, "Non Trust Funds", style);
        createCell(row, 4, "Special Trust Funds", style);
        createCell(row, 5, "Giving Status", style);
        createCell(row, 6, "Settlement Status", style);

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

    public void getTransactionTracing(String start_date, String end_date,
                                      String username, String password){

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        // Session Numbers
        final int rand = (int) ((Math.random() * 9000000) + 1000000);

        // Get the Login Details to get the Number of Church Members
        // Login Credentials
        MemberPersonnel personnel = new MemberPersonnel();
        personnel.setUser(username);
        personnel.setPassword(password);

        // Get the Personnel Response
        MemberPersonnelResponse response = authApi.loginMemberPersonnel(personnel);

        // Authentication
        com.example.tried.auth.personnel.tracing.Authentication authenticate = new com.example.
                tried.auth.personnel.tracing.Authentication();
        authenticate.setInstututionLevel(response.getPayload().getOrganisationLevel());
        authenticate.setInstututionNumber(response.getPayload().getOrganisationNumber());
        authenticate.setInstututionName(response.getPayload().getOrganisationName());
        authenticate.setUser(username);
        authenticate.setPassword(password);
        authenticate.setSessionNumber(rand);

        com.example.tried.auth.personnel.tracing.TracingPayload payload = new com.example.tried.
                auth.personnel.tracing.TracingPayload();
        payload.setStartDate(start_date);
        payload.setEndDate(end_date);

        LocalChurchTransactionTracing tracing = new LocalChurchTransactionTracing();
        tracing.setPayload(payload);
        tracing.setAuthentication(authenticate);

        LocalChurchTransactionTracingResponse response1 = authApi.getTransactionTracingSummary(tracing);
        List<TransactionsItem> transaction = response1.getPayload().getTransactions();

        for (TransactionsItem transactionItem : transaction) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++,transactionItem.getCfmsTransactionId() , style);
            createCell(row, columnCount++, transactionItem.getReceiverId(), style);
            createCell(row, columnCount++, transactionItem.getTrustFund(), style);
            createCell(row, columnCount++, transactionItem.getNonTrustFund(), style);
            createCell(row, columnCount++, transactionItem.getSpecialTrustFunds(), style);
            createCell(row, columnCount++, transactionItem.getGivingStatus(), style);
            createCell(row, columnCount++, transactionItem.getSettlementStatus(), style);
        }
    }

    public void export(HttpServletResponse response,String start_date, String end_date,
                       String username, String password) throws IOException, java.io.IOException {
        writeHeaderLine();
        getTransactionTracing(start_date,end_date,username,password);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
