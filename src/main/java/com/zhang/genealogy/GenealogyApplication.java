package com.zhang.genealogy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhang.genealogy.dao")
public class GenealogyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenealogyApplication.class, args);
    }

}
