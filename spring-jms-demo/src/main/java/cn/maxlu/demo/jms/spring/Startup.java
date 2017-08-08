package cn.maxlu.demo.jms.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.TextMessage;

/**
 * Created by luwei on 2017/8/7.
 */
public class Startup {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        context.start();
        JmsTemplate jmsQueueTemplate = (JmsTemplate)context.getBean("jmsQueueTemplate");
        JmsTemplate jmsTopicTemplate = (JmsTemplate)context.getBean("jmsTopicTemplate");
        sendMsg(jmsQueueTemplate, jmsTopicTemplate);
    }

    private static void sendMsg(JmsTemplate jmsQueueTemplate, JmsTemplate jmsTopicTemplate) {
        jmsQueueTemplate.send("test.queue", session -> {
            TextMessage message = session.createTextMessage();
            message.setText("好消息");
            return message;
        });

        jmsTopicTemplate.send("test.topic", session -> {
            TextMessage message = session.createTextMessage();
            message.setText("好消息");
            return message;
        });
    }
}
