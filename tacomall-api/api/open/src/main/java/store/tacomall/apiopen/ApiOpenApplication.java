/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-23 16:13:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/ApiOpenApplication.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiopen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import store.tacomall.apiopen.controller.WxContronller;
import store.tacomall.common.aspect.NoRepeatSubmitAspect;
import store.tacomall.common.config.WebMvcInterceptorConfig;
import store.tacomall.common.config.WxMaConfig;
import store.tacomall.common.config.WxPayConfiguration;
import store.tacomall.common.libs.wx.WxPayUtil;

@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apiopen" })
@MapperScan("store.tacomall.common.mapper")
@ComponentScan(basePackages = { "store.tacomall.common", "store.tacomall.apiopen" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { NoRepeatSubmitAspect.class,
                WebMvcInterceptorConfig.class, WxPayUtil.class, WxMaConfig.class, WxPayConfiguration.class,
                WxContronller.class }) })
public class ApiOpenApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiOpenApplication.class, args);
    }
}
