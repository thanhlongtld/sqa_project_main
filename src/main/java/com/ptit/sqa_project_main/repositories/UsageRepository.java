package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.Usage;
import org.springframework.data.repository.CrudRepository;

public interface UsageRepository extends CrudRepository<Usage, Integer> {
    Usage findByClientIdAndMonthYear(Client client, Integer month, Integer year);
}
