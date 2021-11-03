package store.tacomall.common.aspect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import store.tacomall.common.annotation.NoRepeatSubmit;
import store.tacomall.common.constant.RedisKeyConstant;
import store.tacomall.common.util.RedisUtil;
import store.tacomall.common.util.StringUtil;

@Aspect
@Component
public class NoRepeatSubmitAspect {

    @Autowired
    RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(NoRepeatSubmitAspect.class);

    private static Gson gson = new Gson();

    private static final String SUFFIX = "SUFFIX";

    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class userCla = (Class) obj.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for (Integer i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            Object val = new Object();
            try {
                val = f.get(obj);
                map.put(f.getName(), val);
            } catch (IllegalArgumentException e) {
                logger.error("getKeyAndValue IllegalArgumentException", e);
            } catch (IllegalAccessException e) {
                logger.error("getKeyAndValue IllegalAccessException", e);
            }

        }
        logger.info("扫描结果：" + gson.toJson(map));
        return map;
    }

    @Pointcut("@annotation(noRepeatSubmit)")
    public void repeatPoint(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around(value = "repeatPoint(noRepeatSubmit)")
    public Object doBefore(ProceedingJoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) {
        String key = RedisKeyConstant.COMMON_NO_REPEAT_SUBMIT + noRepeatSubmit.location();
        String name = noRepeatSubmit.name();
        Integer argIndex = noRepeatSubmit.argIndex();
        String suffix;
        Object[] args = joinPoint.getArgs();
        if (StringUtils.isEmpty(name)) {
            suffix = String.valueOf(args[argIndex]);
        } else {
            Map<String, Object> keyAndValue = getKeyAndValue(args[argIndex]);
            Object valueObj = keyAndValue.get(name);
            if (valueObj == null) {
                suffix = SUFFIX;
            } else {
                suffix = String.valueOf(valueObj);
            }
        }
        key = key + "-" + suffix;
        logger.info("==================================================");
        for (Object arg : args) {
            logger.info(gson.toJson(arg));
        }
        logger.info("==================================================");
        long seconds = noRepeatSubmit.seconds();
        logger.info("lock key : " + key);

        if (StringUtil.isNotBlank(redisUtil.get(key, 0))) {
            throw new RuntimeException("操作过于频繁，请稍后重试");
        }
        redisUtil.set(key, "ack", 0, seconds);
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            logger.error("运行业务代码出错", throwable);
            throw new RuntimeException(throwable.getMessage());
        } finally {
        }
    }
}
