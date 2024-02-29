
package com.tacomall.apiadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.tacomall.common.aspect.NoRepeatSubmitAspect;
import com.tacomall.common.config.RedisConfig;
import com.tacomall.common.config.WebMvcInterceptorConfig;
import com.tacomall.common.config.WxMaConfiguration;
import com.tacomall.common.config.WxPayConfiguration;
import com.tacomall.common.properties.WxMaProperties;
import com.tacomall.common.properties.WxPayProperties;
import com.tacomall.common.util.RedisUtil;

@SpringBootApplication(scanBasePackages = { "com.tacomall.apiadmin" })
@MapperScan({ "com.tacomall.common.mapper", "com.tacomall.apiadmin.mapper" })
@ComponentScan(basePackages = { "com.tacomall.common", "com.tacomall.apiadmin" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { 
            NoRepeatSubmitAspect.class,
            RedisUtil.class, 
            RedisConfig.class,
            WebMvcInterceptorConfig.class,
            WxPayConfiguration.class,
            WxMaConfiguration.class,
            WxPayProperties.class,
            WxMaProperties.class}) })
public class ApiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }

}
