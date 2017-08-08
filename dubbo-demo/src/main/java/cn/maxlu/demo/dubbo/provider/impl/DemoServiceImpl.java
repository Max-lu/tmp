package cn.maxlu.demo.dubbo.provider.impl;

import cn.maxlu.demo.dubbo.provider.api.DemoService;

public class DemoServiceImpl implements DemoService {

	@Override
	public void sayHello() {
		System.out.println("hello world");
	}
}
