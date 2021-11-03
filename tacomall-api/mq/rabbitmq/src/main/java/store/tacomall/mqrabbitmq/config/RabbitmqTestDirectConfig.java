/***
 * @Author: 码上talk|RC
 * @Date: 2021-03-30 14:28:34
 * @LastEditTime: 2021-10-06 15:00:49
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/config/RabbitmqTestDirectConfig.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqTestDirectConfig {

  @Bean
  public Queue TestDirectQueue() {
    return new Queue("TestDirectQueue", true);
  }

  @Bean
  DirectExchange TestDirectExchange() {
    return new DirectExchange("TestDirectExchange");
  }

  @Bean
  Binding bindingTestDirect() {
    return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
  }
}