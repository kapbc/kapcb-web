//package com.kapcb.framework.web.aspect;
//
//import cn.hutool.core.date.DateUtil;
//import com.kapcb.framework.common.constants.enums.StringPool;
//import com.kapcb.framework.common.constants.pattern.DateFormatPattern;
//import com.kapcb.framework.web.properties.WebLogProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
///**
// * <a>Title: WebLogAspect </a>
// * <a>Author: Kapcb <a>
// * <a>Description: WebLogAspect <a>
// *
// * @author Kapcb
// * @version 1.0.0
// * @date 2021/11/7 14:05
// */
//@Slf4j
//@Aspect
//@Configuration
//public abstract class WebLogAspect {
//
//    @Resource
//    private Environment environment;
//
//    @Resource
//    private WebLogProperties webLogProperties;
//
//    @Resource
//    private HttpServletRequest httpServletRequest;
//
//    @Around("(@within(org.springframework.stereotype.Controller)) || @within(org.springframework.web.bind.annotation.RestController) && execution(public * com.kapcb.ccc..*.controller..*.*(..))")
//    public Object logAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        LocalDateTime now = LocalDateTime.now();
//        long startTime = System.currentTimeMillis();
//        Object returnValue = null;
//        Exception exception = null;
//        try {
//            returnValue = proceedingJoinPoint.proceed();
//            return returnValue;
//        } catch (Exception e) {
//            exception = e;
//            throw e;
//        } finally {
//            log.info(StringPool.SPILT_LINE.value());
//            if (webLogProperties.isRequestCostTime()) {
//                log.info(StringPool.PROCESS_COST_TIME.value(), System.currentTimeMillis() - startTime);
//            }
//            if (webLogProperties.isApplicationName()) {
//                log.info(StringPool.REQUEST_SERVER_NAME.value(), environment.getProperty(StringPool.SERVER_APPLICATION_NAME.value()));
//            }
//            if (webLogProperties.isRequestUri()) {
//                log.info(StringPool.REQUEST_URI.value(), httpServletRequest.getRequestURI());
//            }
//            if (webLogProperties.isRequestUrl()) {
//                log.info(StringPool.REQUEST_URL.value(), httpServletRequest.getRequestURL());
//            }
//            if (webLogProperties.isMethodName()) {
//                log.info(StringPool.PROCESS_METHOD_NAME.value(), proceedingJoinPoint.getSignature().getName());
//            }
//            if (webLogProperties.isClassName()) {
//                log.info(StringPool.PROCESS_CLASS_NAME.value(), proceedingJoinPoint.getTarget().getClass().getName());
//            }
//            if (webLogProperties.isRequestTime()) {
//                log.info(StringPool.PROCESS_LOCAL_DATE_TIME.value(), DateUtil.format(now, DateFormatPattern.NORM_DATETIME_PATTERN));
//            }
//            if (Objects.isNull(exception) && webLogProperties.isReturnValue()) {
//                log.info(StringPool.REQUEST_RETURN_VALUE.value(), returnValue);
//            }
//            if (Objects.nonNull(exception) && webLogProperties.isExceptionMessage()) {
//                log.error(StringPool.REQUEST_ERROR_MESSAGE.value(), exception.getMessage());
//            }
//            log.info(StringPool.SPILT_LINE.value());
//        }
//    }
//
//}
