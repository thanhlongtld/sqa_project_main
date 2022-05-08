package com.ptit.sqa_project_main.services;


import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> getAll() {
        return (List<Client>) this.repository.findAll();
    }
}
