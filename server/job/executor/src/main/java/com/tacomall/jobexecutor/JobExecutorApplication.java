package com.tacomall.jobexecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.tacomall.common.aspect.NoRepeatSubmitAspect;
import com.tacomall.common.config.RedisConfig;
import com.tacomall.common.config.WebMvcCorsConfig;
import com.tacomall.common.config.WebMvcInterceptorConfig;
import com.tacomall.common.util.RedisUtil;

@SpringBootApplication
@ComponentScan(basePackages = { "com.tacomall.common", "com.tacomall.jobexecutor" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { NoRepeatSubmitAspect.class,
                RedisUtil.class, RedisConfig.class,
                WebMvcInterceptorConfig.class, WebMvcCorsConfig.class }) })
public class JobExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobExecutorApplication.class, args);
    }

}