package com.houyongju.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HouYongJu
 * @create 2021-08-22 14:17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.houyongju"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.houyongju.staservice.mapper")
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
    }
}
