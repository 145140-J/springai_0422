package com.llsstt.lstai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.llsstt.lstai.mapper")
@SpringBootApplication
public class LstAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LstAiApplication.class, args);
    }

}
