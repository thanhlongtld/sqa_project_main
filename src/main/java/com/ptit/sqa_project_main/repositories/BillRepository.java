package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.MonthIncome;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Integer> {
    List<Bill> findByClientId(Integer clientId);

    List<Bill> findBillsByPaymentIsNull();

    @Query("SELECT function('date_format', b.createdAt, '%m/%Y') as month, sum(b.totalPrice) as allMoney, sum(u.recentUsedCBM) as numOfWater  FROM Bill b, Usage u WHERE b.createdAt BETWEEN :startDate AND :endDate AND b.usage.id = u.id GROUP BY function('date_format', b.createdAt, '%m/%Y')")
    List<MonthIncome> getMonthIncomes(@Param("startDate") Date startDate, @Param("endDate")Date endDate);
}
