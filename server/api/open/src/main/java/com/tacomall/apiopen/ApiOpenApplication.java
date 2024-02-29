
package com.tacomall.apiopen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.tacomall.common.aspect.NoRepeatSubmitAspect;
import com.tacomall.common.config.ShiroConfig;
import com.tacomall.common.config.WebMvcInterceptorConfig;
import com.tacomall.common.service.impl.OrgStaffServiceImpl;

@SpringBootApplication(scanBasePackages = {"com.tacomall.apiopen" })
@MapperScan("com.tacomall.common.mapper")
@ComponentScan(basePackages = { "com.tacomall.common", "com.tacomall.apiopen" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { 
            WebMvcInterceptorConfig.class,
            OrgStaffServiceImpl.class,
            NoRepeatSubmitAspect.class,
            ShiroConfig.class }) })
public class ApiOpenApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiOpenApplication.class, args);
    }
}
