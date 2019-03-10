package org.liezi.common.annotation;

import java.lang.annotation.*;

/**
 * @author: lake.lei
 * @date: 2019/2/15 15:09
 * @description: 系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
