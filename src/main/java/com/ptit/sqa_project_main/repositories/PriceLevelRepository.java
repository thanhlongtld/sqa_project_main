package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Level;
import com.ptit.sqa_project_main.models.PriceLevel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceLevelRepository extends CrudRepository<PriceLevel, Integer> {

    @Query("SELECT e FROM PriceLevel e WHERE e.type.id = ?1")
    List<PriceLevel> getPriceLevelsByTypeId(Integer typeId);

    PriceLevel getPriceLevelById(Integer id);

    void deletePriceLevelsByTypeId(Integer typeId);
}
