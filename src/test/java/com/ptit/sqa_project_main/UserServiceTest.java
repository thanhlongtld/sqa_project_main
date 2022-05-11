package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.models.User;
import com.ptit.sqa_project_main.services.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private ApplicationContext context;

    
    @Test
    public void testAuth1(){
        // thanh cong
        User expUser = new User();
        expUser.setId(1);
        expUser.setName("Nong Đố");
        expUser.setUsername("admin");
        expUser.setPassword("admin");

        UserService userService = context.getBean(UserService.class);
        Assertions.assertEquals(expUser.getId(),userService.Auth("admin","admin").getId());
    }

    @Test
    public void testAuth2(){
        // username khong ton tai
        User expUser = null;

        UserService userService = context.getBean(UserService.class);
        Assertions.assertEquals(expUser,userService.Auth("admin1","admin"));
    }

    @Test
    public void testAuth3(){
        // sai mat khau
        User expUser = null;

        UserService userService = context.getBean(UserService.class);
        Assertions.assertEquals(expUser,userService.Auth("admin1","admin1"));
    }
}
