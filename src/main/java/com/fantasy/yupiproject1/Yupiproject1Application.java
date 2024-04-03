package com.fantasy.yupiproject1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.fantasy.yupiproject1.mapper")
public class Yupiproject1Application {

    public static void main(String[] args) {
        SpringApplication.run(Yupiproject1Application.class, args);
    }

}
