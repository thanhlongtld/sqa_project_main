package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getBillsHavePaymentNull() {
        return this.billRepository.findBillsByPaymentIsNull();
    }
}
