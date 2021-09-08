package com.kcyu.springtoken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kcyu.springtoken.mapper")
public class SpringTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTokenApplication.class, args);
    }

}
