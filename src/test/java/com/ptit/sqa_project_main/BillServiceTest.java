package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.services.BillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

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
}


