package com.ptit.sqa_project_main;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.services.ClientService;
import com.ptit.sqa_project_main.services.StringService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest
public class StringServiceTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testDecodeUrl(){
        StringService stringService = context.getBean(StringService.class);

        Assertions.assertEquals("Hộ gia đình",stringService.decodeUrl("H%E1%BB%99%20gia%20%C4%91%C3%ACnh"));
    }
}
