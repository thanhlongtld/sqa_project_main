package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.Usage;
import com.ptit.sqa_project_main.repositories.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsageService {
    @Autowired
    private UsageRepository repository;

    public Usage getByClientIdAndMonth(Integer clientId, Integer month) {
        Client client = new Client();
        client.setId(clientId);
        return this.repository.findByClientIdAndMonth(client, month);
    }
}
