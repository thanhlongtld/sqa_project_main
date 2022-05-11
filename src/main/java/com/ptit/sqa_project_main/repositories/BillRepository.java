package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.MonthIncome;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Integer> {
    List<Bill> findByClientId(Integer clientId);

    List<Bill> findBillsByPaymentIsNull();

    List<Bill> getBillsByCreatedAtIsGreaterThanEqualAndCreatedAtIsLessThanEqual(Date startDate, Date endDate);
}
