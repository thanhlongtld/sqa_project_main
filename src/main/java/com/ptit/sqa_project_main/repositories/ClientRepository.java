package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> getClientsByNameLike(String name);
}
