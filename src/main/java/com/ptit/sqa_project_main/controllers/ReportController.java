package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.MonthIncome;
import com.ptit.sqa_project_main.models.User;
import com.ptit.sqa_project_main.services.BillService;
import com.ptit.sqa_project_main.utils.IncomeExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user!=null){
            return "report";
        }
        return "redirect:/login";

    }

    @GetMapping("/report/income")
    public void exportToExcel(HttpServletResponse response, @RequestParam String start, @RequestParam String end) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=income_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<MonthIncome> monthIncomeList = billService.getMonthIncoms(start,end);

        IncomeExcelExporter excelExporter = new IncomeExcelExporter(monthIncomeList);

        excelExporter.export(response);
    }
}
