package com.example.tried.services.reports.excel;

import com.example.tried.auth.dto.MemberProfile;
import com.example.tried.auth.dto.MemberProfileResponse;
import com.example.tried.auth.dto.Profilepayload;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummary;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummaryResponse;
import com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem;
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

public class LocalNonTrustFundReportExcel {


    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public LocalNonTrustFundReportExcel() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Local Non Trust Fund Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Receipt Number", style);
        createCell(row, 1, "Member Name", style);
        createCell(row, 2, "Member Number", style);
        createCell(row, 3, "Mode of Payment", style);
        createCell(row, 4, "Local Combined Offerings", style);
        createCell(row, 5, "Total Amount", style);

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

    public void getNonTrustFundReport(List<MembersItem> membersItems){

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);



        for (MembersItem membersItem: membersItems) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;


            if(membersItem.getMemberNumber() != null) {

                createCell(row, columnCount++,membersItem.getReceiptNumber() , style);
                createCell(row, columnCount++, membersItem.getMemberName(), style);
                createCell(row, columnCount++, membersItem.getMemberNumber(), style);
                createCell(row, columnCount++, membersItem.getMeansOfPayment(), style);
                createCell(row, columnCount++, membersItem.getLocalCombinedOfferings(), style);
                createCell(row, columnCount++, membersItem.getTotalAmount(), style);

            }else{

                createCell(row, columnCount++,"" , style);
                createCell(row, columnCount++, "", style);
                createCell(row, columnCount++,"Local Combined Offerings" , style);
                createCell(row, columnCount++, membersItem.getLocalCombinedOfferings(), style);
                createCell(row, columnCount++,"Total Amount" , style);
                createCell(row, columnCount++, membersItem.getTotalAmount(), style);
            }
        }
    }

    public void export(HttpServletResponse response, List<MembersItem> membersItems) throws IOException, java.io.IOException {
        writeHeaderLine();
        getNonTrustFundReport(membersItems);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
