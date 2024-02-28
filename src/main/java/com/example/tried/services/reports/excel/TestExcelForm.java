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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public class TestExcelForm {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    @Autowired
    private AuthApi authApi;

    public TestExcelForm() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Here1");

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
        createCell(row, 5, "Tithe", style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,4,5));
        createCell(row, 6, "Combined", style);
        createCell(row, 7, "Combined", style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,6,7));
        createCell(row, 8, "Camp", style);
        createCell(row, 9, "Camp", style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,8,9));
        createCell(row, 10, "Conf. Dev", style);
        createCell(row, 11, "Conf. Dev", style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,10,11));
        createCell(row, 12, "Thirteenth", style);
        createCell(row, 13, "Thirteenth", style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,12,13));
        createCell(row, 14, "Total", style);
        createCell(row, 15, "Total", style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,14,15));
        createCell(row, 16, "Balance", style);

    }


    private void writeSubHeaderLine() {
        int rowCount = 1;

        Row row = sheet.createRow(rowCount);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);


        // Row Number 2 ==== Sub Header Line  ====
        createCell(row, 0, "", style);
        createCell(row, 1, "", style);
        createCell(row, 2, "", style);
        createCell(row, 3, "", style);
        createCell(row, 4, "Received", style);
        createCell(row, 5, "Paid", style);
        createCell(row, 6, "Received", style);
        createCell(row, 7, "Paid", style);
        createCell(row, 8, "Received", style);
        createCell(row, 9, "Paid", style);
        createCell(row, 10, "Received", style);
        createCell(row, 11, "Paid", style);
        createCell(row, 12, "Received", style);
        createCell(row, 13, "Paid", style);
        createCell(row, 14, "Received", style);
        createCell(row, 15, "Paid", style);
        createCell(row, 16, "", style);
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

        for (int i = 0; i < 15; i++) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, "10685399", style);
            createCell(row, columnCount++, "07 Jan,24 11:42", style);
            createCell(row, columnCount++, "Andrew Keitany", style);
            createCell(row, columnCount++, "Cash", style);
            createCell(row, columnCount++, "100.00", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "10.00", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "50.00", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "160.000", style);
            createCell(row, columnCount++, "0.0", style);
            createCell(row, columnCount++, "-160.000", style);
        }
    }

    public void export(HttpServletResponse response) throws IOException, java.io.IOException {
        writeHeaderLine();
        writeSubHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
