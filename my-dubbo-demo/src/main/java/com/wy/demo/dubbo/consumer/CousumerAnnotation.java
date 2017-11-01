package com.wy.demo.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wy.demo.dubbo.service.HelloService;

//@Service("haha")
public class CousumerAnnotation {

	@Reference
	HelloService helloService;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/dubbo-demo-consumer.xml" });
		context.start();

		CousumerAnnotation hh = (CousumerAnnotation) context.getBean("haha"); // 获取远程服务代理

		System.out.println(hh.helloService); // 显示调用结果
	}
}
