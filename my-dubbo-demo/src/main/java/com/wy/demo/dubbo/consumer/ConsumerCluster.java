package com.wy.demo.dubbo.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.wy.demo.dubbo.module.User;
import com.wy.demo.dubbo.service.HelloService;

public class ConsumerCluster {
	static ConcurrentHashSet<Integer> set = new ConcurrentHashSet<>();
	public static void main(String[] args) throws IOException {
		Thread[] threads = new Thread[200];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Consumer("app" + i));

		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();

		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		for(int i:set){
			System.out.println(i);
		}
		System.in.read();
	}

	static class Consumer implements Runnable {
		String appName;
		HelloService helloService;
		User user;

		public Consumer(String appName) {
			this.appName = appName;
			ApplicationConfig application = new ApplicationConfig(appName);
			List<RegistryConfig> registries = new ArrayList<RegistryConfig>(10);
			registries.add(new RegistryConfig("redis://127.0.0.1:6379"));

			ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
			reference.setApplication(application);
			reference.setRegistries(registries);
			reference.setInterface(HelloService.class);

			helloService = reference.get();
			user = new User();
			user.setName("wy");
			System.out.println(helloService);
			
		}

		@Override
		public void run() {

			for (int i = 0; i < 100; i++) {
				set.add(helloService.say(user));
			}
		}

	}
}
