package com.houyongju.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HouYongJu
 * @create 2021-08-15 17:12
 */
@SpringBootApplication
@ComponentScan("com.houyongju")
@MapperScan("com.houyongju.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class);
    }
}
