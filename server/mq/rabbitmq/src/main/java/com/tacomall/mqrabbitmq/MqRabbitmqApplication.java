package com.tacomall.mqrabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.tacomall.common.config.RedisConfig;
import com.tacomall.common.config.WebMvcInterceptorConfig;

@SpringBootApplication(scanBasePackages = { "com.tacomall.common", "com.tacomall.mqrabbitmq" })
@MapperScan("com.tacomall.common.mapper")
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        RedisConfig.class, WebMvcInterceptorConfig.class }) })
public class MqRabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqRabbitmqApplication.class, args);
    }
}