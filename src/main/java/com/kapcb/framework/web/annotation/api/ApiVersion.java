package com.kapcb.framework.web.annotation.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <a>Title: ApiVersion </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ApiVersion <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/29 23:40
 * @since 1.0
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ApiVersion {

    /**
     * Restful Api 接口版本号
     * 最近优先原则 : 在方法上的 {@link ApiVersion} 可覆盖在类上面的 {@link ApiVersion}，如下 :
     * 类上面的 value 值 = 1.1,
     * 方法上面的 value 值 = 2.1,
     * 最终效果：v2.1
     *
     * @return double
     */
    double value() default 1;

    /**
     * 是否废气版本接口
     * 若请求废弃版本接口将会抛出业务异常
     *
     * @return boolean
     */
    boolean deprecated() default false;

}
