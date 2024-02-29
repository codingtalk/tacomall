
package com.tacomall.common.interceptor;

import java.util.Map;
import java.lang.reflect.Method;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tacomall.common.annotation.LoginUser;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.JwtUtil;
import com.tacomall.common.util.StringUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final String TOKEN_KEY = "x-access-token";
    public static final String USER_KEY = "LOGIN_USER_KEY";

    @Resource
    Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(LoginUser.class)) {
            LoginUser loginUser = method.getAnnotation(LoginUser.class);
            String token = request.getHeader(TOKEN_KEY);
            if (loginUser.required() && StringUtil.isBlank(token)) {
                ExceptionUtil.throwUnauthorizedException("token不能为空");
            }
            if (!StringUtil.isBlank(token)) {
                try {
                    JwtUtil jwtUtil = new JwtUtil();
                    jwtUtil.setISSUER(environment.getProperty("spring.application.name"));
                    Map<String, String> res = jwtUtil.verify(token);
                    request.setAttribute(USER_KEY, res);
                } catch (Exception e) {
                    if (loginUser.required()) {
                        ExceptionUtil.throwUnauthorizedException("token鉴权失败");
                    }
                }
            }
        }
        return true;
    }
}
