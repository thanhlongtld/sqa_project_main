package com.ptit.sqa_project_main.repositories;

import com.ptit.sqa_project_main.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
