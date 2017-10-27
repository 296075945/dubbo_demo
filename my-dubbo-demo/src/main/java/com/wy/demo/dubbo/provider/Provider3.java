package com.wy.demo.dubbo.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.wy.demo.dubbo.service.HelloService;
import com.wy.demo.dubbo.service.impl.HelloServiceImpl;

public class Provider3 {
	public static void main(String[] args) throws IOException {
		new Thread(new Provider(20881)).start();
//		new Thread(new Provider(20880)).start();
		System.in.read(); // 按任意键退出
	}
	static class Provider implements Runnable{
		int port ;
		public Provider(int port){
			this.port = port;
		}
		public void run() {
			ApplicationConfig app = new ApplicationConfig("provider");
			
			List<RegistryConfig> registries = new ArrayList<RegistryConfig>(10);
			registries.add(new RegistryConfig("redis://127.0.0.1:6379"));
//			registries.add(new RegistryConfig("zookeeper://127.0.0.1:2181"));
			
			List<ProtocolConfig> protocols = new ArrayList<ProtocolConfig>(10);
			protocols.add(new ProtocolConfig("dubbo", port));
			
//			protocols.add(new ProtocolConfig("injvm", 20000));
			
			ServiceConfig<Object> service = new ServiceConfig<Object>();
			service.setInterface(HelloService.class);
			service.setRef(new HelloServiceImpl());
			service.setProtocols(protocols);
			service.setRegistries(registries);
			service.setApplication(app);
			service.export();
			
			System.out.println("success");
			
		}
		
	}
}
