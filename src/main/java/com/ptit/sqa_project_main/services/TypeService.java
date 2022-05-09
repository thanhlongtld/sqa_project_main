package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import com.ptit.sqa_project_main.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository repository;

    public List<Type> getAll() {
        return (List<Type>) this.repository.findAll();
    }
}
