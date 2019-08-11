package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;

@RestController
public class DeptController
{
	@Autowired
	private DeptService deptService;

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id){
		Dept dept = this.deptService.get(id);
		if(null==dept){
			throw new RuntimeException("改id:"+id+"没有对应的信息");
		}
		return dept;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id){
		return new Dept().setDeptno(id).setDname("改id没有对应的消息").setDb_source("no this database");
	}


}
