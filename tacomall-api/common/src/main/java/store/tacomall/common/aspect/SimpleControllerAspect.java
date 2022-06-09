package store.tacomall.common.aspect;

import store.tacomall.common.annotation.SimpleRestLogin;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.common.util.SpringContextUtil;
import store.tacomall.common.util.StringUtil;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
public class SimpleControllerAspect {

    @Resource
    Environment environment;

    private static final String TOKEN_KEY = "x-access-token";

    public static final String USER_KEY = "LOGIN_USER_KEY";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(store.tacomall.common.annotation.SimpleRestController)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    @SuppressWarnings("unchecked")
    public ResponseJson<Object> around(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = point.getArgs();
        Map<String, String> pathVariableMap = (Map<String, String>) args[0];
        Object obj = SpringContextUtil.getBean(String.format("%sServiceImpl", pathVariableMap.get("domain")));
        Method method = ReflectionUtils.findMethod(obj.getClass(), pathVariableMap.get("action"),
                new Class[] { JSONObject.class });
        if (method.isAnnotationPresent(SimpleRestLogin.class)) {
            SimpleRestLogin simpleRestLogin = method.getAnnotation(SimpleRestLogin.class);
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
        ResponseJson<Object> json = (ResponseJson<Object>) ReflectionUtils.invokeMethod(
                method,
                obj, args[1]);
        return json;
    }
}
