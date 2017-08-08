package cn.maxlu.demo.jms.spring.queue;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by luwei on 2017/8/7.
 */
@Component
public class QueueReceiver1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String textMessage = ((TextMessage) message).getText();
                System.out.println("QueueReceiver1 接收到消息：" + textMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
