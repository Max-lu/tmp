package cn.maxlu.demo.spring.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoBeanTest {

	public static void main(String[] args) throws InterruptedException {
		// start spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		context.start();

		// get bean
		DemoBean bean = context.getBean(DemoBean.class);
		System.out.println(bean.getName());

		// stop spring
		context.close();
	}
}
