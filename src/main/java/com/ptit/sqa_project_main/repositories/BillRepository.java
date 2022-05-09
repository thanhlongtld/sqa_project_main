package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Integer> {
    List<Bill> findByClientId(Integer clientId);

    List<Bill> findBillsByPaymentIsNull();
}
