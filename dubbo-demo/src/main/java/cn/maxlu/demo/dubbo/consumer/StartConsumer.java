package cn.maxlu.demo.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.maxlu.demo.dubbo.provider.api.DemoService;

public class StartConsumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo/spring-dubbo-consumer.xml" });
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		demoService.sayHello(); // 执行远程方法
	}
}
