/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-06 15:00:57
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/mq/rabbitmq/src/main/java/store/tacomall/mqrabbitmq/config/MybatisPlusConfig.java
 * @Just do what I think it is right
 */
package store.tacomall.mqrabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.mybatis.spring.annotation.MapperScan;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;

@EnableTransactionManagement
@Configuration
@MapperScan("store.tacomall.common.mapper")
public class MybatisPlusConfig {

  @Bean
  public MybatisPlusInterceptor blockAttackInnerInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
    return interceptor;
  }
}
