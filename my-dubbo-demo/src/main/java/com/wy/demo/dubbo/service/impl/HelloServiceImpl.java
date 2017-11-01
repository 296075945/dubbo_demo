package com.wy.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wy.demo.dubbo.module.User;
import com.wy.demo.dubbo.service.HelloService;
/**
 * 
 * @author Administrator
 *
 */
@Service()
public class HelloServiceImpl implements HelloService {
	int i = 0;

	@Override
	public int say(User hello) {

		System.out.println(hello.getName());
		return i++;
	}

}
