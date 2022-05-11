package com.ptit.sqa_project_main.utils;

import com.ptit.sqa_project_main.models.MonthIncome;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IncomeExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<MonthIncome> monthIncomeList;

    public IncomeExcelExporter(List<MonthIncome> monthIncomeList) {
        this.monthIncomeList = monthIncomeList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Báo cáo doanh thu theo tháng");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);


        createCell(row, 0, "Tháng", style);
        createCell(row, 1, "Số nước tiêu thụ", style);
        createCell(row, 2, "Tổng tiền phải thu được", style);
        createCell(row, 3, "Doanh thu", style);
        createCell(row, 4, "Còn nợ", style);

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
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);


        for (MonthIncome monthIncome : monthIncomeList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, "Tháng " + monthIncome.getMonth(), style);
            createCell(row, columnCount++, monthIncome.getNumOfWater(), style);
            createCell(row, columnCount++, monthIncome.getAllMoney(), style);
            createCell(row, columnCount++, monthIncome.getIncome(), style);
            createCell(row, columnCount++, monthIncome.getDebt(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
