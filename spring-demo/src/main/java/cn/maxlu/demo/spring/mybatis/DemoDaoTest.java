package cn.maxlu.demo.spring.mybatis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoDaoTest {

	public static void main(String[] args) {
		// start spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		context.start();

        context.getBean(DemoService.class).insertDemo();

        // stop spring
		context.close();
	}

}
