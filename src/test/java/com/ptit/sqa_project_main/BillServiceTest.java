package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.models.*;
import com.ptit.sqa_project_main.repositories.BillRepository;
import com.ptit.sqa_project_main.repositories.PaymentRepository;
import com.ptit.sqa_project_main.repositories.UsageRepository;
import com.ptit.sqa_project_main.services.BillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
public class BillServiceTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testGetBillHavePaymentNull(){
        BillService billService = context.getBean(BillService.class);

        List<Bill> bills = billService.getBillsHavePaymentNull();

        Assertions.assertEquals(null,bills.get(0).getPayment());
        Assertions.assertEquals(null,bills.get(1).getPayment());
    }

    @Test
    public void testConvertToDate(){
        BillService billService = context.getBean(BillService.class);

        Date date1 = new Date(2022 - 1900, 0, 1);
        String input = "2022-01-01";

        String input2 = "01/01/2022";

        Assertions.assertEquals(date1,billService.convertToDate(input));
        Assertions.assertEquals(null,billService.convertToDate(input2));
    }

    @Test
    public void testConvertToMonthString(){
        BillService billService = context.getBean(BillService.class);
        String input = "2022-01-01";
        Date exOutput = new Date(2022 - 1900, 0, 1);
        Date output = billService.convertToDate(input);

        Assertions.assertEquals(exOutput,output);
    }

    @Test
    @Rollback(true)
    public void testGetMonthIncomsStatusDone(){
        BillService billService = context.getBean(BillService.class);
        BillRepository billRepository = context.getBean(BillRepository.class);
        UsageRepository usageRepository = context.getBean(UsageRepository.class);
        PaymentRepository paymentRepository = context.getBean(PaymentRepository.class);

        Integer usageCBM = 20;
        Usage newUsage = new Usage();
        Client client = new Client();
        client.setId(100);
        newUsage.setClient(client);
        newUsage.setMonth(7);
        newUsage.setYear(2022);
        newUsage.setRecentUsedCBM(usageCBM);
        Integer totalCBM = usageCBM;
        newUsage.setTotalCBM(totalCBM);
        newUsage.setCreatedAt(new java.sql.Date(2022 - 1900, 7 - 1, 28));
        usageRepository.save(newUsage);
        Usage u = usageRepository.findTopByOrderByIdDesc();
        Payment payment = new Payment();
        payment.setProvider("Something");
        payment.setType("Something");
        payment.setMessage("Mã "+ 100 + " đóng tiền nước tháng " + 7 + " năm " + 2022);
        paymentRepository.save(payment);
        Payment p = paymentRepository.findTopByOrderByIdDesc();

        Bill bill = new Bill();
        bill.setClient(client);
        bill.setCreatedAt(newUsage.getCreatedAt());
        bill.setTotalPrice(123456);
        bill.setStatus("done");
        bill.setPayment(p);
        bill.setUsage(u);
        billRepository.save(bill);

        List<MonthIncome> monthIncomeList = new ArrayList<>();
        monthIncomeList.add(new MonthIncome("07/2022",20,123456, 123456,0));
        List<MonthIncome> result = billService.getMonthIncoms("2022-07", "2022-07");
        Assertions.assertEquals(monthIncomeList.size(),result.size());
        Assertions.assertEquals( monthIncomeList.get(0).toString(),result.get(0).toString());
    }

    @Test
    @Rollback(true)
    public void testGetMonthIncomsStatusPending(){
        BillService billService = context.getBean(BillService.class);
        BillRepository billRepository = context.getBean(BillRepository.class);
        UsageRepository usageRepository = context.getBean(UsageRepository.class);
        PaymentRepository paymentRepository = context.getBean(PaymentRepository.class);

        Integer usageCBM = 20;
        Usage newUsage = new Usage();
        Client client = new Client();
        client.setId(100);
        newUsage.setClient(client);
        newUsage.setMonth(7);
        newUsage.setYear(2022);
        newUsage.setRecentUsedCBM(usageCBM);
        Integer totalCBM = usageCBM;
        newUsage.setTotalCBM(totalCBM);
        newUsage.setCreatedAt(new java.sql.Date(2022 - 1900, 7 - 1, 28));
        usageRepository.save(newUsage);
        Usage u = usageRepository.findTopByOrderByIdDesc();

        Bill bill = new Bill();
        bill.setClient(client);
        bill.setCreatedAt(newUsage.getCreatedAt());
        bill.setTotalPrice(123456);
        bill.setStatus("pending");
        bill.setPayment(null);
        bill.setUsage(u);
        billRepository.save(bill);

        List<MonthIncome> monthIncomeList = new ArrayList<>();
        monthIncomeList.add(new MonthIncome("07/2022",20,123456, 0,123456));
        List<MonthIncome> result = billService.getMonthIncoms("2022-07", "2022-07");
        Assertions.assertEquals(monthIncomeList.size(),result.size());
        Assertions.assertEquals( monthIncomeList.get(0).toString(),result.get(0).toString());
    }

    @Test
    @Rollback(true)
    public void testGetMonthIncomsIf2True(){
        BillService billService = context.getBean(BillService.class);
        BillRepository billRepository = context.getBean(BillRepository.class);
        UsageRepository usageRepository = context.getBean(UsageRepository.class);

        Integer usageCBM = 20;
        Usage newUsage1 = new Usage();
        Client client = new Client();
        client.setId(100);
        newUsage1.setClient(client);
        newUsage1.setMonth(7);
        newUsage1.setYear(2022);
        newUsage1.setRecentUsedCBM(usageCBM);
        Integer totalCBM = usageCBM;
        newUsage1.setTotalCBM(totalCBM);
        newUsage1.setCreatedAt(new java.sql.Date(2022 - 1900, 7 - 1, 28));
        usageRepository.save(newUsage1);
        Usage u1 = usageRepository.findTopByOrderByIdDesc();

        Bill bill1 = new Bill();
        bill1.setClient(client);
        bill1.setCreatedAt(newUsage1.getCreatedAt());
        bill1.setTotalPrice(123456);
        bill1.setStatus("pending");
        bill1.setPayment(null);
        bill1.setUsage(u1);
        billRepository.save(bill1);

        Usage newUsage2 = new Usage();
        Client client2 = new Client();
        client2.setId(101);
        newUsage2.setClient(client2);
        newUsage2.setMonth(7);
        newUsage2.setYear(2022);
        newUsage2.setRecentUsedCBM(usageCBM);
        newUsage2.setTotalCBM(totalCBM);
        newUsage2.setCreatedAt(new java.sql.Date(2022 - 1900, 7 - 1, 28));
        usageRepository.save(newUsage2);
        Usage u2 = usageRepository.findTopByOrderByIdDesc();

        Bill bill2 = new Bill();
        bill2.setClient(client2);
        bill2.setCreatedAt(newUsage2.getCreatedAt());
        bill2.setTotalPrice(123456);
        bill2.setStatus("pending");
        bill2.setPayment(null);
        bill2.setUsage(u2);
        billRepository.save(bill2);

        List<MonthIncome> monthIncomeList = new ArrayList<>();
        monthIncomeList.add(new MonthIncome("07/2022",40,123456*2, 0,123456*2));
        List<MonthIncome> result = billService.getMonthIncoms("2022-07", "2022-07");
        Assertions.assertEquals(monthIncomeList.size(),result.size());
        Assertions.assertEquals( monthIncomeList.get(0).toString(),result.get(0).toString());
    }

    @Test
    @Rollback(true)
    public void testGetMonthIncomsIf2False(){
        BillService billService = context.getBean(BillService.class);
        BillRepository billRepository = context.getBean(BillRepository.class);
        UsageRepository usageRepository = context.getBean(UsageRepository.class);

        Integer usageCBM = 20;
        Usage newUsage1 = new Usage();
        Client client = new Client();
        client.setId(100);
        newUsage1.setClient(client);
        newUsage1.setMonth(7);
        newUsage1.setYear(2022);
        newUsage1.setRecentUsedCBM(usageCBM);
        Integer totalCBM = usageCBM;
        newUsage1.setTotalCBM(totalCBM);
        newUsage1.setCreatedAt(new java.sql.Date(2022 - 1900, 7 - 1, 28));
        usageRepository.save(newUsage1);
        Usage u1 = usageRepository.findTopByOrderByIdDesc();

        Bill bill1 = new Bill();
        bill1.setClient(client);
        bill1.setCreatedAt(newUsage1.getCreatedAt());
        bill1.setTotalPrice(123456);
        bill1.setStatus("pending");
        bill1.setPayment(null);
        bill1.setUsage(u1);
        billRepository.save(bill1);

        Usage newUsage2 = new Usage();
        Client client2 = new Client();
        client2.setId(101);
        newUsage2.setClient(client2);
        newUsage2.setMonth(8);
        newUsage2.setYear(2022);
        newUsage2.setRecentUsedCBM(usageCBM);
        newUsage2.setTotalCBM(totalCBM);
        newUsage2.setCreatedAt(new java.sql.Date(2022 - 1900, 8 - 1, 28));
        usageRepository.save(newUsage2);
        Usage u2 = usageRepository.findTopByOrderByIdDesc();

        Bill bill2 = new Bill();
        bill2.setClient(client2);
        bill2.setCreatedAt(newUsage2.getCreatedAt());
        bill2.setTotalPrice(123456);
        bill2.setStatus("pending");
        bill2.setPayment(null);
        bill2.setUsage(u2);
        billRepository.save(bill2);

        List<MonthIncome> monthIncomeList = new ArrayList<>();
        monthIncomeList.add(new MonthIncome("07/2022",20,123456, 0,123456));
        monthIncomeList.add(new MonthIncome("08/2022",20,123456, 0,123456));
        List<MonthIncome> result = billService.getMonthIncoms("2022-07", "2022-08");
        Assertions.assertEquals(monthIncomeList.size(),result.size());
        Assertions.assertEquals( monthIncomeList.get(0).toString(),result.get(0).toString());
        Assertions.assertEquals( monthIncomeList.get(1).toString(),result.get(1).toString());
    }
}


