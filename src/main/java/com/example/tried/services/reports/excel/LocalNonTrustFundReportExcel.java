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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalNonTrustFundReportExcel {

    List<String> keys = new ArrayList<String>();
    List<String> keys_unfiltered = new ArrayList<String>();


    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public LocalNonTrustFundReportExcel() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(HashMap<String, Integer> churchFunds) {
        sheet = workbook.createSheet("Local Non Trust Fund Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Receipt Number", style);
        createCell(row, 1, "Member Name", style);
        createCell(row, 2, "Member Number", style);
        createCell(row, 3, "Mode of Payment", style);
        createCell(row, 4, "Local Combined Offerings", style);



        System.out.println("HashMap:"+ churchFunds);

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

        keys.add("Total Amount");
        keys_unfiltered.add("totalAmount");

        int count = 5;
        System.out.println("Keys Unfiltered: "+ keys_unfiltered);
        System.out.println("Keys Unfiltered Size: "+ keys_unfiltered.size());

        for(int i=0; i < keys.size(); i++){
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
        }else if(value instanceof String){
            cell.setCellValue((String) value);
        }else if(value instanceof Double){
            cell.setCellValue((Double) value);
        }else if(value instanceof Float){
            cell.setCellValue((Float) value);
        }
        cell.setCellStyle(style);
    }

    public void getNonTrustFundReport(List<HashMap<String, Object>> membersItems, HashMap<String, Integer> accounts){

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);



        for (HashMap<String, Object> membersItem: membersItems) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++,membersItem.get("receiptNumber") , style);
            createCell(row, columnCount++, membersItem.get("memberName"), style);
            createCell(row, columnCount++, membersItem.get("memberNumber"), style);
            createCell(row, columnCount++, membersItem.get("meansOfPayment"), style);
            createCell(row, columnCount++, membersItem.get("local_combined_offerings"), style);

            int count = 5;

            int total = 3 + keys_unfiltered.size();

            for(int i = 0; i < keys_unfiltered.size(); i++){
                if(membersItem.get("receiptNumber") != null){
                    createCell(row, count++, membersItem.get(keys_unfiltered.get(i)), style);
                }else{
                    createCell(row, 3,"Total Local Combined Offerings" , style);
                    createCell(row, count++, membersItem.get(keys_unfiltered.get(i)), style);
                    createCell(row, total,"Total Amount" , style);
                }
            }



        }
    }

    public void export(HttpServletResponse response, List<HashMap<String, Object>> membersItems, HashMap<String, Integer> accounts) throws IOException, java.io.IOException {
        writeHeaderLine(accounts);
        getNonTrustFundReport(membersItems, accounts);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
