package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.models.ScheduledEmail;
import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.repositories.TypeRepository;
import com.ptit.sqa_project_main.services.ScheduledEmailService;
import com.ptit.sqa_project_main.services.TypeService;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
public class TypeServiceTest {



    @Autowired
    private TypeRepository repository;

    @Autowired
    private ApplicationContext context;


    @Rollback(true)
    @Test
    public void testDelete(){
        TypeService typeService = context.getBean(TypeService.class);
        typeService.deleteById(1);

        Assertions.assertNull(this.repository.getTypeById(1));
    }

    @Rollback(true)
    @Test
    public void testSave(){
        Type type = new Type();
        type.setId(1);
        type.setName("Test Test");


        TypeService typeService = context.getBean(TypeService.class);
        typeService.save(type);

        Assertions.assertEquals("Test Test",this.repository.getTypeById(1).getName());
    }

    @Test
    public void testGetAll (){
        // test get all
        TypeService typeService = context.getBean(TypeService.class);


        List<Type> output = typeService.getAll();


        //  Assertions.assertEquals(expResult,output);
        Assertions.assertEquals(3,output.get(0).getId());
        Assertions.assertEquals("Cơ quan hành chính",output.get(0).getName());

        Assertions.assertEquals(1,output.get(1).getId());
        Assertions.assertEquals("Hộ gia đình",output.get(1).getName());

        Assertions.assertEquals(2,output.get(2).getId());
        Assertions.assertEquals("Hộ nghèo",output.get(2).getName());

        Assertions.assertEquals(6,output.get(3).getId());
        Assertions.assertEquals("Đơn vị kinh doanh dịch vụ",output.get(3).getName());

        Assertions.assertEquals(5,output.get(4).getId());
        Assertions.assertEquals("Đơn vị sản xuất",output.get(4).getName());

        Assertions.assertEquals(4,output.get(5).getId());
        Assertions.assertEquals("Đơn vị sự nghiệp, dịch vụ công cộng",output.get(5).getName());

    }


    @Test
    public void testGetTypeById(){
        TypeService typeService = context.getBean(TypeService.class);

        Assertions.assertEquals("Hộ nghèo", typeService.getTypeById(2).toString());
    }
}
