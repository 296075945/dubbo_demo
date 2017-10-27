package com.wy.demo.dubbo.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.wy.demo.dubbo.service.HelloService;
import com.wy.demo.dubbo.service.impl.HelloServiceImpl;

public class Provider1 {
	public static void main(String[] args) throws IOException {
		ApplicationConfig app = new ApplicationConfig("provider");
		
		List<RegistryConfig> registries = new ArrayList<RegistryConfig>(10);
		registries.add(new RegistryConfig("redis://127.0.0.1:6379"));
//		registries.add(new RegistryConfig("zookeeper://127.0.0.1:2181"));
		
		List<ProtocolConfig> protocols = new ArrayList<ProtocolConfig>(10);
		protocols.add(new ProtocolConfig("dubbo", 20880));
		protocols.add(new ProtocolConfig("injvm", 20000));
		
		ServiceConfig<Object> service = new ServiceConfig<Object>();
		service.setInterface(HelloService.class);
		service.setRef(new HelloServiceImpl());
		service.setProtocols(protocols);
		service.setRegistries(registries);
		service.setApplication(app);
		service.export();
		
		new Thread(new Consumer(),"consumer").start();
		
		System.out.println("success");
		System.in.read(); // 按任意键退出
	}
	static class Consumer implements Runnable{

		public void run() {
			ApplicationConfig application = new ApplicationConfig("consumer");
			
			List<RegistryConfig> registries = new ArrayList<RegistryConfig>(10);
			registries.add(new RegistryConfig("redis://127.0.0.1:6379"));
			registries.add(new RegistryConfig("zookeeper://127.0.0.1:2181"));
			
			ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
			reference.setApplication(application);
//			reference.setRegistries(registries);
			reference.setInterface(HelloService.class);
			//使用injvm协议
//			reference.setScope(Constants.SCOPE_LOCAL);
			
			HelloService helloService = reference.get();
			
			
			System.out.println(helloService.say("hahah"));
		}
		
	}
}
