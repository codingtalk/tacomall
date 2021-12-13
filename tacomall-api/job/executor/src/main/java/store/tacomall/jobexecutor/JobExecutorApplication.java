/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 16:45:46
 * @LastEditTime: 2021-12-13 08:27:22
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/job/executor/src/main/java/store/tacomall/jobexecutor/JobExecutorApplication.java
 */
package store.tacomall.jobexecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import store.tacomall.common.aspect.NoRepeatSubmitAspect;
import store.tacomall.common.config.RedisConfig;
import store.tacomall.common.config.WebMvcCorsConfig;
import store.tacomall.common.config.WebMvcInterceptorConfig;
import store.tacomall.common.config.WxMaConfig;
import store.tacomall.common.config.WxPayConfiguration;
import store.tacomall.common.libs.wx.WxPayUtil;
import store.tacomall.common.util.RedisUtil;

@SpringBootApplication
@ComponentScan(basePackages = { "store.tacomall.common", "store.tacomall.jobexecutor" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { WxPayUtil.class, WxPayConfiguration.class,
                WxMaConfig.class, NoRepeatSubmitAspect.class, RedisUtil.class, RedisConfig.class,
                WebMvcInterceptorConfig.class, WebMvcCorsConfig.class }) })
public class JobExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobExecutorApplication.class, args);
    }

}