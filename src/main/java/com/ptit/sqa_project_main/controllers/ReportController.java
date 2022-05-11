package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.MonthIncome;
import com.ptit.sqa_project_main.services.BillService;
import com.ptit.sqa_project_main.utils.IncomeExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    BillService billService;

    @GetMapping("/report")
    public String index() {
        return "report";
    }

    @GetMapping("/report/income")
    public void exportToExcel(HttpServletResponse response, @RequestParam String start, @RequestParam String end) throws IOException {
        System.out.println(start);
        System.out.println(end);

//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=income_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);

        List<MonthIncome> monthIncomeList = billService.getMonthIncoms(new java.sql.Date(2022,01,01), new java.sql.Date(2022,12,30));
        for (MonthIncome monthIncome: monthIncomeList) {
            System.out.println(monthIncome.getMonth());
        }
//        monthIncomeList.add(new MonthIncome("1/2022",2,3,4,5));
//        monthIncomeList.add(new MonthIncome("2/2022",2,3,4,5));
//        monthIncomeList.add(new MonthIncome("3/2022",2,3,4,5));
//        monthIncomeList.add(new MonthIncome("4/2022",2,3,4,5));
//        monthIncomeList.add(new MonthIncome("5/2022",2,3,4,5));
//
//        IncomeExcelExporter excelExporter = new IncomeExcelExporter(monthIncomeList);
//
//        excelExporter.export(response);
    }
}
