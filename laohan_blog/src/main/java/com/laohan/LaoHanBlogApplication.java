package com.laohan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.laohan.mapper")
public class LaoHanBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaoHanBlogApplication.class,args);
    }
}