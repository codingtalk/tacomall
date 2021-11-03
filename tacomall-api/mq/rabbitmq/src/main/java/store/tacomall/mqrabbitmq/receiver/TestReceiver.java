/***
 * @Author: 码上talk|RC
 * @Date: 2021-03-30 14:29:57
 * @LastEditTime: 2021-10-06 15:00:44
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/receiver/TestReceiver.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq.receiver;

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
