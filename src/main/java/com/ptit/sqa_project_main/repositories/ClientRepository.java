package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
