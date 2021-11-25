package com.kapcb.framework.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <a>Title: LogAspect </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LogAspect <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/25 21:53
 */
@Slf4j
@Aspect
public abstract class LogAspect {

    ThreadLocal<Long> currentTime = new ThreadLocal<>();


    @Pointcut(value = "@annotation(com.kapcb.framework.web.annotation.Log)")
    public void logPoint() {
    }

    @Around(value = "logPoint()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        currentTime.set(System.currentTimeMillis());
        result = proceedingJoinPoint.proceed();

        return result;
    }

    @AfterThrowing(pointcut = "logPoint()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        currentTime.remove();

    }

    public abstract String getUsername();

    public abstract String getUserId();
}
