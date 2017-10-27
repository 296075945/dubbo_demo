package com.wy.demo.dubbo.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.wy.demo.dubbo.service.HelloService;

public class Consumer1 {
	public static void main(String[] args) throws IOException {
		ApplicationConfig application = new ApplicationConfig("consumer");
		
		List<RegistryConfig> registries = new ArrayList<RegistryConfig>(10);
		registries.add(new RegistryConfig("redis://127.0.0.1:6379"));
		registries.add(new RegistryConfig("zookeeper://127.0.0.1:2181"));
		
		ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
		reference.setApplication(application);
		reference.setRegistries(registries);
		reference.setInterface(HelloService.class);
//		reference.setCheck(true);
//		reference.setInjvm(true);
		//使用injvm协议
//		reference.setScope(Constants.SCOPE_LOCAL);
		
		HelloService helloService = reference.get();
		System.out.println(reference.getProtocol());
		
		
		System.out.println(helloService.say("hahah"));
		
				
	}
}
