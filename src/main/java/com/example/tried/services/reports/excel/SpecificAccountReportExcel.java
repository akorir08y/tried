package com.example.tried.services.reports.excel;


import com.example.tried.auth.reports.specific.TransactionsItem;
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

public class SpecificAccountReportExcel {

    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public SpecificAccountReportExcel() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Specific Account Summary Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Member Number", style);
        createCell(row, 1, "Contributor Name", style);
        createCell(row, 2, "Sum", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if(value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void getSpecificAccountSummary(List<com.example.tried.auth.reports.specific.TransactionsItem> transaction){

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        double sum = 0.00;

        int size = transaction.size();
        int lastRow = size + rowCount;

        // Session Numbers
        for (TransactionsItem transactionItem : transaction) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++,transactionItem.getBusinessMemberId() , style);
            createCell(row, columnCount++, transactionItem.getContributorName(), style);
            createCell(row, columnCount++, transactionItem.getSum(), style);
            sum += Double.valueOf(transactionItem.getSum());
        }

        Row row = sheet.createRow(lastRow);
        createCell(row, 0,"" , style);
        createCell(row, 1,"Total Sum" , style);
        createCell(row, 2,sum , style);
    }

    public void export(HttpServletResponse response,List<com.example.tried.auth.reports.specific.TransactionsItem> transactionsItems) throws IOException, NullPointerException, java.io.IOException {
        writeHeaderLine();
        getSpecificAccountSummary(transactionsItems);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
