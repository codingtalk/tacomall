package com.tacomall.common.aspect;

import java.util.Map;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.tacomall.common.annotation.SimpleRestLogin;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.JwtUtil;
import com.tacomall.common.util.SpringContextUtil;
import com.tacomall.common.util.StringUtil;

import cn.hutool.core.util.ObjectUtil;

@Aspect
@Component
public class SimpleRestControllerAspect {

    @Resource
    Environment environment;

    private static final String TOKEN_KEY = "x-access-token";

    public static final String USER_KEY = "LOGIN_USER_KEY";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.tacomall.common.annotation.SimpleRestController)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    @SuppressWarnings("unchecked")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Map<String, String> pathVariableMap = (Map<String, String>) args[0];
        Object obj = SpringContextUtil.getBean(String.format("%sServiceImpl", pathVariableMap.get("domain")));
        Method method = ReflectionUtils.findMethod(obj.getClass(), pathVariableMap.get("action"),
                new Class[] { JSONObject.class });
        SimpleRestLogin simpleRestLogin = AnnotationUtils.findAnnotation(method, SimpleRestLogin.class);
        if (ObjectUtil.isNotNull(simpleRestLogin)) {
            String token = request.getHeader(TOKEN_KEY);
            if (simpleRestLogin.required() && StringUtil.isBlank(token)) {
                ExceptionUtil.throwUnauthorizedException("token不能为空");
            }
            if (!StringUtil.isBlank(token)) {
                try {
                    JwtUtil jwtUtil = new JwtUtil();
                    jwtUtil.setISSUER(environment.getProperty("spring.application.name"));
                    Map<String, String> res = jwtUtil.verify(token);
                    request.setAttribute(USER_KEY, res);
                } catch (Exception e) {
                    if (simpleRestLogin.required()) {
                        ExceptionUtil.throwUnauthorizedException("token鉴权失败");
                    }
                }
            }
        }
        return ReflectionUtils.invokeMethod(
                method,
                obj, args[1]);
    }
}
