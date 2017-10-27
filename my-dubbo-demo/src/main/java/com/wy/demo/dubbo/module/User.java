package com.wy.demo.dubbo.module;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class User implements Serializable {

	private static final long serialVersionUID = 1609415403100275799L;

	@NotNull(message ="不能为空")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
