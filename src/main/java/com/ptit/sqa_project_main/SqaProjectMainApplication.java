package com.ptit.sqa_project_main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SqaProjectMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqaProjectMainApplication.class, args);
    }

}
