package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Integer> {
    Type getTypeById(Integer id);

    void deleteTypeById(Integer id);
}
