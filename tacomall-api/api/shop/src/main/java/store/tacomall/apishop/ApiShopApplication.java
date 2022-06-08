/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-11 22:45:41
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/shop/src/main/java/store/tacomall/apishop/ApiShopApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.apishop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import store.tacomall.common.aspect.NoRepeatSubmitAspect;
import store.tacomall.common.config.RedisConfig;
import store.tacomall.common.config.WxMaConfig;
import store.tacomall.common.config.WxPayConfiguration;
import store.tacomall.common.libs.wx.WxPayUtil;
import store.tacomall.common.util.RedisUtil;
import store.tacomall.common.util.SpringContextUtil;

@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apishop" })
@MapperScan({ "store.tacomall.common.mapper", "store.tacomall.apishop.mapper" })
@ComponentScan(basePackages = { "store.tacomall.common", "store.tacomall.apishop" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { RedisConfig.class,
                NoRepeatSubmitAspect.class, WxMaConfig.class, WxPayConfiguration.class, WxPayUtil.class, RedisUtil.class }) })
public class ApiShopApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ApiShopApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }

}
