package cn.maxlu.demo.spring.inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luwei on 2017/7/4.
 */

public class Test {
    public static void main(String[] args) {
        // start spring
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/inject/spring-context.xml");
        context.start();

        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.bean.MSG);

        // stop spring
        context.close();
    }
}
