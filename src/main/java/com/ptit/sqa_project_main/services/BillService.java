package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.MonthIncome;
import com.ptit.sqa_project_main.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getBillsHavePaymentNull() {
        return this.billRepository.findBillsByPaymentIsNull();
    }

    public List<MonthIncome> getMonthIncoms(String startMonth, String endMonth) {
        Date startDate = convertToDate(startMonth + "-01");
        Date endDate = convertToDate(endMonth + "-28");
        List<Bill> bills =  this.billRepository.getBillsByCreatedAtIsGreaterThanEqualAndCreatedAtIsLessThanEqual(startDate,endDate);
        List<MonthIncome> monthIncomeList = new ArrayList<>();
        for(Bill bill: bills) {
            String month = convertToMonthString(bill.getCreatedAt());
            int index = getIndexOf(monthIncomeList,month);
            int a = 0;
            int b = 0;
            if(bill.getStatus().equals("done")) {
                a = bill.getTotalPrice();
            } else {
                b = bill.getTotalPrice();
            }
            if(index < 0) {
                MonthIncome newMonthIncome = new MonthIncome(month, bill.getUsage().getRecentUsedCBM(),bill.getTotalPrice(),a,b);
                monthIncomeList.add(newMonthIncome);
            } else {
                MonthIncome currentMonthIncome = monthIncomeList.get(index);
                currentMonthIncome.setNumOfWater(bill.getUsage().getRecentUsedCBM() + currentMonthIncome.getNumOfWater());
                currentMonthIncome.setAllMoney(bill.getTotalPrice()+currentMonthIncome.getAllMoney());
                currentMonthIncome.setIncome(a+ currentMonthIncome.getIncome());
                currentMonthIncome.setDebt(b+ currentMonthIncome.getDebt());
                monthIncomeList.set(index, currentMonthIncome);
            }
        }
        return monthIncomeList;
    }

    public Date convertToDate(String input) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        try {
            startDate = df.parse(input);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertToMonthString(Date input) {
        DateFormat df = new SimpleDateFormat("MM/yyyy");
        return df.format(input);
    }

    public int getIndexOf(List<MonthIncome> monthIncomeList, String month) {
        for(MonthIncome monthIncome: monthIncomeList) {
            if(monthIncome.getMonth().equals(month)) {
                return monthIncomeList.indexOf(monthIncome);
            }
        }
        return -1;
    }
}
