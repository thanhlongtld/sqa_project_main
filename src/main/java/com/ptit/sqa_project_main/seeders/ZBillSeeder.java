package com.ptit.sqa_project_main.seeders;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.Payment;
import com.ptit.sqa_project_main.models.Usage;
import com.ptit.sqa_project_main.repositories.BillRepository;
import com.ptit.sqa_project_main.repositories.PaymentRepository;
import com.ptit.sqa_project_main.repositories.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ZBillSeeder implements CommandLineRunner {
    String[] providers = {"MBBank","eBay","Techcombank","Payoo"};
    String[] _types = {"VISA", "Paypal", "chuyển khoản", "chuyển tiền mặt"};

    int index = 1;
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UsageRepository usageRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {
        if(billRepository.count() == 0) {
            for(int i = 1; i <= 10; i++) {
                genDataForOneClient(i);
            }
        }
    }

    private void genDataForOneClient(Integer clientId) {
        int totalCBM = 0;
        Integer month = 1;
        Integer year = 2021;
        while (year == 2021 || (year == 2022 && month < 5)) {
            Integer usageCBM = 10 + (int)(Math.random() * ((30 - 10) + 1));
            Usage newUsage = new Usage();
            Client client = new Client();
            client.setId(clientId);
            newUsage.setClient(client);
            newUsage.setMonth(month);
            newUsage.setYear(year);
            newUsage.setRecentUsedCBM(usageCBM);
            totalCBM += usageCBM;
            newUsage.setTotalCBM(totalCBM);
            newUsage.setCreatedAt(new Date(year - 1900, month, 28));
            usageRepository.save(newUsage);
            newUsage.setId(index);
            Payment payment = new Payment();
            payment.setProvider(providers[clientId%4]);
            payment.setType(_types[clientId%4]);
            payment.setMessage("Mã "+ clientId + " đóng tiền nước tháng " + month + " năm " + year);
            paymentRepository.save(payment);
            payment.setId(index);
            Bill bill = new Bill();
            bill.setClient(client);
            bill.setCreatedAt(newUsage.getCreatedAt());
            bill.setTotalPrice(100000 + (int)(Math.random() * (300000 + 1)));
            bill.setStatus("done");
            bill.setPayment(payment);
            bill.setUsage(newUsage);
            billRepository.save(bill);
            if(month == 12) {
                month = 1;
                year = 2022;
            } else {
                month ++;
            }
            index ++;
        }
    }
}
