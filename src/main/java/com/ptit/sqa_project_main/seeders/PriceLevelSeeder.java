package com.ptit.sqa_project_main.seeders;

import com.ptit.sqa_project_main.models.Level;
import com.ptit.sqa_project_main.models.PriceLevel;
import com.ptit.sqa_project_main.models.Type;
import com.ptit.sqa_project_main.repositories.PriceLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PriceLevelSeeder implements CommandLineRunner {
    @Autowired
    private PriceLevelRepository priceLevelRepository;
    private int[] hoGiaDinh = {5973, 7052, 8669, 15929};
    private int[] hoNgheo = {3600, 4500, 5600, 6700};
    private int[] coQuan = {9955};
    private int[] dichvu = {9955};
    private int[] sanXuat = {11615};
    private int[] kinhDoanh = {22068};

    @Override
    public void run(String... args) throws Exception {
        if(priceLevelRepository.count() == 0) {
            for(int i = 0;i < hoGiaDinh.length; i++){
                Level level = new Level();
                level.setId(i+1);
                Type type = new Type();
                type.setId(1);
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setPrice(hoGiaDinh[i]);
                priceLevel.setType(type);
                priceLevel.setLevel(level);
                priceLevelRepository.save(priceLevel);
            }
            for(int i = 0;i < hoNgheo.length; i++){
                Level level = new Level();
                level.setId(i+1);
                Type type = new Type();
                type.setId(2);
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setPrice(hoNgheo[i]);
                priceLevel.setType(type);
                priceLevel.setLevel(level);
                priceLevelRepository.save(priceLevel);
            }

            for(int i = 0;i < coQuan.length; i++){
                Type type = new Type();
                type.setId(3);
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setPrice(coQuan[i]);
                priceLevel.setType(type);
                priceLevelRepository.save(priceLevel);
            }

            for(int i = 0;i < dichvu.length; i++){
                Type type = new Type();
                type.setId(4);
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setPrice(dichvu[i]);
                priceLevel.setType(type);
                priceLevelRepository.save(priceLevel);
            }

            for(int i = 0;i < sanXuat.length; i++){
                Type type = new Type();
                type.setId(5);
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setPrice(sanXuat[i]);
                priceLevel.setType(type);
                priceLevelRepository.save(priceLevel);
            }

            for(int i = 0;i < kinhDoanh.length; i++){
                Type type = new Type();
                type.setId(6);
                PriceLevel priceLevel = new PriceLevel();
                priceLevel.setPrice(kinhDoanh[i]);
                priceLevel.setType(type);
                priceLevelRepository.save(priceLevel);
            }
        }
    }
}
