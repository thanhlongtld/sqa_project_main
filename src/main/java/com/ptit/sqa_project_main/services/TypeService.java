package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import com.ptit.sqa_project_main.repositories.PriceLevelRepository;
import com.ptit.sqa_project_main.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TypeService {

    @Autowired
    private TypeRepository repository;

    @Autowired
    private PriceLevelRepository priceLevelRepository;

    public List<Type> getAll() {
        return (List<Type>) this.repository.findAll();
    }

    public void save(Type type){
        this.repository.save(type);
    }

    public void deleteById(Integer id) {
        this.priceLevelRepository.deletePriceLevelsByTypeId(id);
        this.repository.deleteById(id);
    }

    public Type getTypeById(Integer id) {
        return this.repository.getTypeById(id);
    }
}
