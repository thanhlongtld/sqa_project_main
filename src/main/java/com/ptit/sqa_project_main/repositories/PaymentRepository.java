package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Payment findTopByOrderByIdDesc();
}
