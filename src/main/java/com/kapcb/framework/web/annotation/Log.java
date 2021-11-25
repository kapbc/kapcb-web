package com.kapcb.framework.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: Log </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Log <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/25 21:50
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String value() default "";

    int type() default 0;

}
