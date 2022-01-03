/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-09-18 14:01:01
 * @LastEditTime: 2022-01-03 16:16:56
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/config/WebMvcInterceptorConfig.java
 */
package store.tacomall.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import store.tacomall.common.interceptor.AuthorizationInterceptor;

@Configuration
public class WebMvcInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    AuthorizationInterceptor authorizationInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}
