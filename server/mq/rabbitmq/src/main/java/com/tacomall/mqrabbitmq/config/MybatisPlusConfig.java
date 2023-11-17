
package com.tacomall.mqrabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.mybatis.spring.annotation.MapperScan;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;

@EnableTransactionManagement
@Configuration
@MapperScan("com.tacomall.common.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor blockAttackInnerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}
