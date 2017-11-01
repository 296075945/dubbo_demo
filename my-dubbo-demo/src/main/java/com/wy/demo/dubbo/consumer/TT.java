package com.wy.demo.dubbo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.wy.demo.dubbo.module.User;
import com.wy.demo.dubbo.service.HelloService;
import com.wy.demo.dubbo.service.impl.HelloServiceImpl;

public class TT {
	public static void main(String[] args) {
		
		RegistryConfig registry = new RegistryConfig("multicast://224.5.6.7:1234");
		
		ServiceConfig<Object> service = new ServiceConfig<>();
		service.setApplication(new ApplicationConfig("provider"));
		service.setRegistry(registry); 
		service.setInterface(HelloService.class);
		service.setRef(new HelloServiceImpl());
		service.setValidation("jvalidation");
		service.export();
		
		ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
		reference.setApplication(new ApplicationConfig("consumer"));
		reference.setInterface(HelloService.class);
		reference.setRegistry(registry);
		HelloService hello = reference.get();
		System.out.println(hello.say(new User()));
		
	}
}
