package com.houyongju.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author HouYongJu
 * @create 2021-08-06 10:27
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.houyongju"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);


    }
}
