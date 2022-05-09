package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.Level;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LevelRepository extends CrudRepository<Level, Integer> {

    @Query("SELECT e FROM Level e WHERE e.id = :priceLevelId")
    Level getLevelByPriceLevelId (@Param("priceLevelId") Integer priceLevelId);
}
