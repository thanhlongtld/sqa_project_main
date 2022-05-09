package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.User;
import com.ptit.sqa_project_main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User Auth(String username, String password){
        User user = repository.findUserByUsername(username);
        if(user == null){
            return null;
        }
        else if(!user.getPassword().equals(password)){
            return null;
        }
        return user;
    }
}
