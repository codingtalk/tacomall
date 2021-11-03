/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-21 14:41:59
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/ApiMaApplication.java
 * @Just do what I think it is right
 */
package store.tacomall.apima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import store.tacomall.common.config.WxPayConfiguration;
import store.tacomall.common.libs.wx.WxPayUtil;

@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apima" })
@MapperScan({ "store.tacomall.common.mapper", "store.tacomall.apima.mapper" })
@ComponentScan(basePackages = { "store.tacomall.common", "store.tacomall.apima" }, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { WxPayConfiguration.class, WxPayUtil.class }) })
public class ApiMaApplication {
  public static void main(String[] args) {
    SpringApplication.run(ApiMaApplication.class, args);
  }

}
