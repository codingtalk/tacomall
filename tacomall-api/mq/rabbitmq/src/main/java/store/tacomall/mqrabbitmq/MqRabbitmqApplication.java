/***
 * @Author: 码上talk|RC
 * @Date: 2021-03-30 14:26:19
 * @LastEditTime: 2021-10-11 23:34:51
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/MqRabbitmqApplication.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.mqrabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import store.tacomall.common.config.RedisConfig;
import store.tacomall.common.config.WebMvcInterceptorConfig;

@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.mqrabbitmq" })
@MapperScan("store.tacomall.common.mapper")
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        RedisConfig.class, WebMvcInterceptorConfig.class }) })
public class MqRabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqRabbitmqApplication.class, args);
    }
}