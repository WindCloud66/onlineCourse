package com.houyongju.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HouYongJu
 * @create 2021-08-16 20:16
 */

@ComponentScan("com.houyongju")
@EnableDiscoveryClient
@MapperScan("com.houyongju.educenter.mapper")
@SpringBootApplication()
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);


    }
}
