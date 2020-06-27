package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Hystrix_DashBoard_APP {
    public static void main(String[] args) {
        SpringApplication.run(Hystrix_DashBoard_APP.class,args);
    }
}
