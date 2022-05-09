package com.ptit.sqa_project_main.seeders;

import com.ptit.sqa_project_main.models.Level;
import com.ptit.sqa_project_main.repositories.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ALevelSeeder implements CommandLineRunner {
    @Autowired
    private LevelRepository levelRepository;
    private String[] levelNames = {
            "Mức 1",
            "Mức 2",
            "Mức 3",
            "Mức 4",
    };

    private int[] levelMins = {
            0,
            11,
            21,
            31,
    };

    private int[] levelMaxs = {
            10,
            20,
            30,
            Integer.MAX_VALUE,
    };

    public void run(String... args) throws Exception {
        if(levelRepository.count() == 0) {
            for (int i = 0; i < levelNames.length; i++) {
                Level level = new Level();
                level.setName(levelNames[i]);
                level.setMin(levelMins[i]);
                level.setMax(levelMaxs[i]);
                levelRepository.save(level);
            }
        }
    }
}
