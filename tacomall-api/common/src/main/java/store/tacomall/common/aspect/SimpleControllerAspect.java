package store.tacomall.common.aspect;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.util.SpringContextUtil;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Aspect
@Component
public class SimpleControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(store.tacomall.common.annotation.SimpleController)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    @SuppressWarnings("unchecked")
    public ResponseJson<Object> around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Map<String, String> pathVariableMap = (Map<String, String>) args[0];
        Object obj = SpringContextUtil.getBean(String.format("%sServiceImpl", pathVariableMap.get("domain")));
        ResponseJson<Object> json = (ResponseJson<Object>) ReflectionUtils.invokeMethod(
                ReflectionUtils.findMethod(obj.getClass(), pathVariableMap.get("action"),
                        new Class[] { Integer.class }),
                obj, 1);
        return json;
    }
}
