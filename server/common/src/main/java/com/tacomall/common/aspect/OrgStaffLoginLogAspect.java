package com.tacomall.common.aspect;

import java.time.LocalDateTime;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tacomall.common.entity.org.OrgStaffLoginLog;
import com.tacomall.common.mapper.org.OrgStaffLoginLogMapper;
import com.tacomall.common.util.IpUtil;
import com.tacomall.common.util.JsonUtil;

@Aspect
@Component
public class OrgStaffLoginLogAspect {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private OrgStaffLoginLogMapper orgStaffLoginLogMapper;

  @Pointcut("@annotation(com.tacomall.common.annotation.OrgStaffLoginLog)")
  public void logPointCut() {

  }

  @Around("logPointCut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    long beginTime = System.currentTimeMillis();
    Object result = point.proceed();
    long time = System.currentTimeMillis() - beginTime;
    saveSysLog(point, time);
    return result;
  }

  private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();

    OrgStaffLoginLog orgStaffLoginLog = new OrgStaffLoginLog();

    String className = joinPoint.getTarget().getClass().getName();
    String methodName = signature.getName();
    this.logger.info(className + "." + methodName + "()");
    Object[] args = joinPoint.getArgs();
    try {
      String params = JsonUtil.toJson(args);
      this.logger.info(params);
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
          .getRequest();
      orgStaffLoginLog.setIp(IpUtil.getIpAddr(request));
      orgStaffLoginLog.setCreateTime(LocalDateTime.now());
      orgStaffLoginLogMapper.insert(orgStaffLoginLog);
    } catch (Exception ignored) {

    }
  }
}
