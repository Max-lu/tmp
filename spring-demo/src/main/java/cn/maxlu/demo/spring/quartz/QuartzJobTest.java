package cn.maxlu.demo.spring.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("all")
public class QuartzJobTest {

	public static void main(String[] args) {
		// start spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-quartz.xml");
	}
}
