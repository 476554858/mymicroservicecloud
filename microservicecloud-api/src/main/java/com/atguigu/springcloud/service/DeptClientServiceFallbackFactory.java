package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept get(Long id) {
                System.out.println("服务不可调用");
                return null;
            }

            @Override
            public List<Dept> list() {
                System.out.println("服务不可调用");
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                System.out.println("服务不可调用");
                return false;
            }
        };
    }
}
