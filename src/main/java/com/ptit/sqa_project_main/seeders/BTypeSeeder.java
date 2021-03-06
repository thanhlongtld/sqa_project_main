package com.ptit.sqa_project_main.seeders;

import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BTypeSeeder implements CommandLineRunner {
    @Autowired
    private TypeRepository typeRepository;
    private String[] types = {
            "Hộ gia đình",
            "Hộ nghèo",
            "Cơ quan hành chính",
            "Đơn vị sự nghiệp, dịch vụ công cộng",
            "Đơn vị sản xuất",
            "Đơn vị kinh doanh dịch vụ"
    };

    public void run(String... args) throws Exception {
        if(typeRepository.count() == 0) {
            for (String name: types) {
                Type type = new Type();
                type.setName(name);
                typeRepository.save(type);
            }
        }
    }
}