package com.example.tried.services.reports.excel;


import com.example.tried.auth.personnel.reports.transcript.TransactionItem;
import com.itextpdf.io.IOException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class TrustFundTranscriptExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public TrustFundTranscriptExcel() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Trust Fund Transcript Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Conference Settlement Reference", style);
        createCell(row, 1, "Transaction Receipt Number", style);
        createCell(row, 2, "Transaction Initiation Time", style);
        createCell(row, 3, "Contributor Name", style);
        createCell(row, 4, "Contributor Contact", style);
        createCell(row, 5, "Church Code", style);
        createCell(row, 6, "Member Id", style);
        createCell(row, 7, "Receiver Name", style);
        createCell(row, 8, "Transaction Total Amount", style);
        createCell(row, 9, "Means Of Giving", style);
        createCell(row, 10, "Trust Fund", style);
        createCell(row, 11, "Settlement At Clearance Account", style);
        createCell(row, 12, "Method of Collection", style);
        createCell(row, 13, "Tithe", style);
        createCell(row, 14, "Camp Meeting", style);
        createCell(row, 15, "Combined Offerings", style);
        createCell(row, 16, "Thirteenth Sabbath", style);
        createCell(row, 17, "Conference Development", style);
        createCell(row, 18, "E-Money Reference", style);
        createCell(row, 19, "E-Money Notification Time", style);
        createCell(row, 20, "Telco Transaction Time", style);
        createCell(row, 21, "E-Money Agent", style);
        createCell(row, 22, "Conference Settlement Confirmation", style);
        createCell(row, 23, "Confirmed In Bank Records", style);
        createCell(row, 24, "Trust-Funds - Special Trust Fund", style);
        createCell(row, 25, "Special Trust Funds", style);
        createCell(row, 26, "Conference Number", style);
        createCell(row, 27, "Conference Name", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Double){
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void getTransactionTracing(List<TransactionItem> transaction){

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        // Session Numbers
        for (TransactionItem transactionItem : transaction) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, transactionItem.getConferenceSettlementReference() , style);
            createCell(row, columnCount++, transactionItem.getTransactionReceiptNumber(), style);
            createCell(row, columnCount++, transactionItem.getTransactionInitiationTime(), style);
            createCell(row, columnCount++, transactionItem.getContributorName(), style);
            createCell(row, columnCount++, transactionItem.getContributorContact(), style);
            createCell(row, columnCount++, transactionItem.getChurchCode(), style);
            createCell(row, columnCount++, transactionItem.getMemberId(), style);
            createCell(row, columnCount++, transactionItem.getReceiverName() , style);
            createCell(row, columnCount++, transactionItem.getTransactionTotalAmount(), style);
            createCell(row, columnCount++, transactionItem.getMeansOfGiving(), style);
            createCell(row, columnCount++, transactionItem.getTrustFund(), style);
            createCell(row, columnCount++, transactionItem.getSettlementAtClearenceAccount(), style);
            createCell(row, columnCount++, transactionItem.getMethodOfCollection(), style);
            createCell(row, columnCount++, transactionItem.getTithe(), style);
            createCell(row, columnCount++, transactionItem.getCampMeeting() , style);
            createCell(row, columnCount++, transactionItem.getCombinedOfferings(), style);
            createCell(row, columnCount++, transactionItem.getThirteenthSabbath(), style);
            createCell(row, columnCount++, transactionItem.getConferenceDevelopment(), style);
            createCell(row, columnCount++, transactionItem.getEMoneyReference(), style);
            createCell(row, columnCount++, transactionItem.getEMoneyNotificationTime(), style);
            createCell(row, columnCount++, transactionItem.getTelcoTransactionTime(), style);
            createCell(row, columnCount++, transactionItem.getEMoneyAgent(), style);
            createCell(row, columnCount++, transactionItem.getConferenceSettlementConfirmation(), style);
            createCell(row, columnCount++, transactionItem.getConfirmedInBankRecords(), style);
            createCell(row, columnCount++, transactionItem.getTrustFundsLessSpecialTrustFund(), style);
            createCell(row, columnCount++, transactionItem.getSpecialTrustFunds(), style);
            createCell(row, columnCount++, transactionItem.getConferenceNumber(), style);
            createCell(row, columnCount++, transactionItem.getConferenceName(), style);
        }
    }

    public void export(HttpServletResponse response,List<TransactionItem> transactionsItems) throws IOException, java.io.IOException {
        writeHeaderLine();
        getTransactionTracing(transactionsItems);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
