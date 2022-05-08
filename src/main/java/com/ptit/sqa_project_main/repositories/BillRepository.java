package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
