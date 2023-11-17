
package com.tacomall.apima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.FilterType;

import com.tacomall.common.aspect.NoRepeatSubmitAspect;
import com.tacomall.common.config.ShiroConfig;
import com.tacomall.common.util.SpringContextUtil;

@SpringBootApplication(scanBasePackages = { "com.tacomall.common", "com.tacomall.apima" })
@MapperScan({ "com.tacomall.common.mapper", "com.tacomall.apima.mapper" })
@ComponentScan(basePackages = { "com.tacomall.common", "com.tacomall.apima"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { NoRepeatSubmitAspect.class, ShiroConfig.class }) })
public class ApiMaApplication {
    public static void main(String[] args) {
        SpringContextUtil.setApplicationContext(SpringApplication.run(ApiMaApplication.class, args));
    }

}
