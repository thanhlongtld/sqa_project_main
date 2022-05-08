package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.MonthIncome;
import com.ptit.sqa_project_main.utils.IncomeExcelExporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {
    @GetMapping("/report")
    public String index() {
        return "report";
    }

    @GetMapping("/export/income")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=income_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<MonthIncome> monthIncomeList = new ArrayList<>();
        monthIncomeList.add(new MonthIncome(1,2,3,4,5));
        monthIncomeList.add(new MonthIncome(2,2,3,4,5));
        monthIncomeList.add(new MonthIncome(3,2,3,4,5));
        monthIncomeList.add(new MonthIncome(4,2,3,4,5));
        monthIncomeList.add(new MonthIncome(5,2,3,4,5));

        IncomeExcelExporter excelExporter = new IncomeExcelExporter(monthIncomeList);

        excelExporter.export(response);
    }
}
