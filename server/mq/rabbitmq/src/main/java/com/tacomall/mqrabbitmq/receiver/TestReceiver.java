package com.tacomall.mqrabbitmq.receiver;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TestDirectQueue")
public class TestReceiver {

    @RabbitHandler
    public void process(Map m) {
        System.out.println("TestReceiver消费者收到消息  : " + m.toString());
        try {
            Thread.sleep(1000 * 10);
        } catch (Exception e) {

        } finally {
            System.out.println("耗时服务处理完毕！");
        }
    }

}
