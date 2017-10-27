package com.wy.demo.dubbo.service.impl;

import com.wy.demo.dubbo.service.HelloService;

public class HelloServiceImpl implements HelloService {
	int i = 0;

	public int say(String hello) {

		System.out.println(hello);
		return i++;
	}

}
