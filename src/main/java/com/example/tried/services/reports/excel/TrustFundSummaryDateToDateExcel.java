package com.example.tried.services.reports.excel;

import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dashboard.trust_funds.TransactionsItem;
import com.example.tried.auth.reports.payment_mode.date_to_date.TrustFundSummaryDateToDatePaymentMode;
import com.example.tried.auth.reports.payment_mode.date_to_date.TrustFundSummaryDateToDatePaymentModeResponse;
import com.example.tried.auth.reports.trust_funds_date_to_date.TrustFundDateToDate;
import com.example.tried.auth.reports.trust_funds_date_to_date.TrustFundDateToDateResponse;
import com.example.tried.services.AuthApi;
import com.example.tried.services.PersonnelApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.itextpdf.io.IOException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;


public class TrustFundSummaryDateToDateExcel {

    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private final PersonnelApi personnelApi;

    public TrustFundSummaryDateToDateExcel(PersonnelApi personnelApi) {
        this.personnelApi = personnelApi;
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Trust Fund Date To Date Summary Report");

        Row row = sheet.createRow(0);
        Row row1 = sheet.createRow(1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        // Row Number 1
        createCell(row, 0, "Receipt No", style);
        createCell(row, 1, "Time", style);
        createCell(row, 2, "Contributor Name", style);
        createCell(row, 3, "Mode Of Payment", style);
        createCell(row, 4, "Tithe", style);
        createCell(row, 6, "Combined", style);
        createCell(row, 8, "Camp", style);
        createCell(row, 10, "Conf. Dev", style);
        createCell(row, 12, "Thirteenth", style);
        createCell(row, 14, "Total", style);
        createCell(row, 16, "Balance", style);

        sheet.addMergedRegion(new CellRangeAddress(0,0,4,5));
        sheet.addMergedRegion(new CellRangeAddress(0,0,6,7));
        sheet.addMergedRegion(new CellRangeAddress(0,0,8,9));
        sheet.addMergedRegion(new CellRangeAddress(0,0,10,11));
        sheet.addMergedRegion(new CellRangeAddress(0,0,12,13));
        sheet.addMergedRegion(new CellRangeAddress(0,0,14,15));

        // Row Number 2
        createCell(row1, 0, "", style);
        createCell(row1, 1, "", style);
        createCell(row1, 2, "", style);
        createCell(row1, 3, "", style);
        createCell(row1, 4, "Received", style);
        createCell(row1, 5, "Paid", style);
        createCell(row1, 6, "Received", style);
        createCell(row1, 7, "Paid", style);
        createCell(row1, 8, "Received", style);
        createCell(row1, 9, "Paid", style);
        createCell(row1, 10, "Received", style);
        createCell(row1, 11, "Paid", style);
        createCell(row1, 12, "Received", style);
        createCell(row1, 13, "Paid", style);
        createCell(row1, 14, "Received", style);
        createCell(row1, 15, "Paid", style);
        createCell(row1, 16, "", style);

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

    private void writeDataLines(TrustFundSummaryDateToDatePaymentMode trustFundDateToDate) throws JsonProcessingException {
        int rowCount = 2;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        System.out.println("Local Trust Fund Date to Date With Payment Mode Summary: "+ trustFundDateToDate);

        TrustFundSummaryDateToDatePaymentModeResponse trustFundDatetoDateResponse =
                personnelApi.getTrustFundSummaryDateByDateWithPaymentMode(trustFundDateToDate);

        System.out.println("Local Trust Fund Date to Date With Payment Mode Summary Response: "+ trustFundDatetoDateResponse);

        List<com.example.tried.auth.reports.payment_mode.date_to_date.TransactionsItem> transactions = trustFundDatetoDateResponse.getMdpayload().getTransactions();

        for (com.example.tried.auth.reports.payment_mode.date_to_date.TransactionsItem transaction : transactions) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, transaction.getReceiptNumber(), style);
            createCell(row, columnCount++, transaction.getTransactionDate(), style);
            createCell(row, columnCount++, transaction.getContributorName(), style);
            createCell(row, columnCount++, transaction.getModeOfPayment(), style);
            createCell(row, columnCount++, transaction.getTithe(), style);
            createCell(row, columnCount++, transaction.getTithePaid(), style);
            createCell(row, columnCount++, transaction.getCombinedOfferings(), style);
            createCell(row, columnCount++, transaction.getCombinedOfferingsPaid(), style);
            createCell(row, columnCount++, transaction.getCampMeeting(), style);
            createCell(row, columnCount++, transaction.getCampMeetingPaid(), style);
            createCell(row, columnCount++, transaction.getConferenceDevelopment(), style);
            createCell(row, columnCount++, transaction.getConferenceDevelopmentPaid(), style);
            createCell(row, columnCount++, transaction.getThirteenthSabbath(), style);
            createCell(row, columnCount++, transaction.getThirteenthSabbathPaid(), style);
            createCell(row, columnCount++, transaction.getTotalReceiptedAmount(), style);
            createCell(row, columnCount++, transaction.getTotalReceiptedAmountPaid(), style);
            createCell(row, columnCount++, transaction.getBalance(), style);
        }
    }

    public void export(HttpServletResponse response, TrustFundSummaryDateToDatePaymentMode trustFundDateToDate) throws IOException, java.io.IOException {
        writeHeaderLine();
        writeDataLines(trustFundDateToDate);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
