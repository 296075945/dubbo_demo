package com.wy.demo.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wy.demo.dubbo.module.User;

public interface HelloService {
	
	
	int say(User hello);
}
