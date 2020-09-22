package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeptProvider8004_APP {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8004_APP.class, args);
    }
}
