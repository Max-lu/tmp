package cn.maxlu.demo.dubbo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartProvider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo/spring-dubbo-provider.xml"});
        context.start();
 
        System.in.read(); // 按任意键退出
	}
}
