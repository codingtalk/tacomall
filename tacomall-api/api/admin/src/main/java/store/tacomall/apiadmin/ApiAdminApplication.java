/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2022-01-03 16:51:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/ApiAdminApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import store.tacomall.common.config.WebMvcInterceptorConfig;
import store.tacomall.common.config.WxMaConfig;
import store.tacomall.common.config.WxPayConfiguration;
import store.tacomall.common.libs.wx.WxPayUtil;

@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apiadmin" })
@MapperScan({ "store.tacomall.common.mapper", "store.tacomall.apiadmin.mapper" })
@ComponentScan(basePackages = { "store.tacomall.common", "store.tacomall.apiadmin" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { WebMvcInterceptorConfig.class,
                WxMaConfig.class, WxPayConfiguration.class, WxPayUtil.class }) })
public class ApiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }

}
