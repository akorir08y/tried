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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalChurchOfferingReportExcel {

    List<String> keys = new ArrayList<String>();
    List<String> keys_unfiltered = new ArrayList<String>();

    Map<String, Object> otpMap = new HashMap<>();

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
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Receipt No", style);
        createCell(row, 1, "Transaction Date", style);
        createCell(row, 2, "Member Name", style);
        createCell(row, 3, "Member Number", style);
        createCell(row, 4, "Mode of Payment", style);
        createCell(row, 5, "Tithe", style);
        createCell(row, 6, "Combined", style);
        createCell(row, 7, "Camp", style);
        createCell(row, 8, "Conference Development", style);
        createCell(row, 9, "Thirteenth", style);

        HashMap<String, Integer> churchFunds = offeringSummaryResponse.getPayload().getLocalChurchFunds();
        for (Map.Entry<String,Integer> mapElement : churchFunds.entrySet()) {
            String key = mapElement.getKey();
            keys_unfiltered.add(key);
            if(key.contains("_")) {
                key = key.replace("_", " ");
                keys.add(key);
            }else{
                keys.add(key);
            }

        }
        keys.add("Total");
        keys_unfiltered.add("totalAmount");

        int count = 10;

        for(int i=0; i < keys.size(); i++) {
            createCell(row, count++, keys.get(i), style);
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
        font.setFontHeight(10);
        style.setFont(font);

        List<HashMap<String, Object>> Members = offeringSummaryResponse.getPayload().getMembers();


        for (HashMap<String, Object> hashMap: Members) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, 0, hashMap.get("receiptNumber"), style);
            createCell(row, 1, hashMap.get("transactionDate"), style);
            createCell(row, 2, hashMap.get("memberName"), style);
            createCell(row, 3, hashMap.get("memberNumber"), style);
            createCell(row, 4, hashMap.get("meansOfPayment"), style);
            createCell(row, 5, hashMap.get("tithe"), style);
            createCell(row, 6, hashMap.get("combined"), style);
            createCell(row, 7, hashMap.get("camp"), style);
            createCell(row, 8, hashMap.get("cdf"), style);
            createCell(row, 9, hashMap.get("thirteenth"), style);

            int count = 10;


            for(int i = 0; i < keys_unfiltered.size(); i++){
                if(hashMap.get("receiptNumber") != null){
                    createCell(row, count++, hashMap.get(keys_unfiltered.get(i)), style);
                }else{
                    createCell(row, 4,"Total Amount" , style);
                    createCell(row, count++, hashMap.get(keys_unfiltered.get(i)), style);
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
