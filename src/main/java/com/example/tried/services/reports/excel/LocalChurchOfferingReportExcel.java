package com.example.tried.services.reports.excel;

import com.example.tried.auth.personnel.reports.non_trust_funds.MembersItem;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummaryResponse;
import com.itextpdf.io.IOException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalChurchOfferingReportExcel {


    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public LocalChurchOfferingReportExcel() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(LocalChurchOfferingSummaryResponse offeringSummaryResponse) {
        sheet = workbook.createSheet("Local Church Offering Report");

        int columnCount = 9;

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Receipt No", style);
        createCell(row, 1, "Member Name", style);
        createCell(row, 2, "Member Number", style);
        createCell(row, 3, "Mode of Payment", style);
        createCell(row, 4, "Tithe", style);
        createCell(row, 5, "Combined", style);
        createCell(row, 6, "Camp", style);
        createCell(row, 7, "Conf Dev.", style);
        createCell(row, 8, "Thirteenth", style);

        HashMap<String, Integer> churchFunds = offeringSummaryResponse.getPayload().getLocalChurchFunds();
        churchFunds.put("Total",0);
        for (Map.Entry<String,Integer> mapElement : churchFunds.entrySet()) {
            String key = mapElement.getKey();
            createCell(row, columnCount++, key, style);
        }

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void getLocalChurchOfferingReport(LocalChurchOfferingSummaryResponse offeringSummaryResponse){

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        List<HashMap<String, Object>> Members = offeringSummaryResponse.getPayload().getMembers();


        for (HashMap<String, Object> hashMap: Members) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if(key.contains("receiptNumber")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("memberName")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("memberNumber")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("meansOfPayment")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("tithe")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("combined")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("camp")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("Development")){
                    createCell(row, columnCount++,value , style);
                }

                if(key.contains("thirteenth")){
                    createCell(row, columnCount++,value , style);
                }

                createCell(row, columnCount++,value , style);

                if(key.contains("totalAmount")){
                    createCell(row, columnCount++,value , style);
                }
            }
        }
    }

    public void export(HttpServletResponse response, LocalChurchOfferingSummaryResponse offeringSummaryResponse) throws IOException, java.io.IOException {
        writeHeaderLine(offeringSummaryResponse);
        getLocalChurchOfferingReport(offeringSummaryResponse);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
