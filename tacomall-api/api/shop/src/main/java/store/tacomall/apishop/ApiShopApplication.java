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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import store.tacomall.common.config.RedisConfig;

@SpringBootApplication(scanBasePackages = { "store.tacomall.common", "store.tacomall.apishop" })
@MapperScan({ "store.tacomall.common.mapper", "store.tacomall.apishop.mapper" })
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { RedisConfig.class }) })
public class ApiShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiShopApplication.class, args);
    }

}
