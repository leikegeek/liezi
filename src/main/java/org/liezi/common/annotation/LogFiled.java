package org.liezi.common.annotation;

import java.lang.annotation.*;

/**
 * @author: lake.lei
 * @date: 2019/2/15 15:09
 * @description: 日志字段注解，被注解的字段抹去值不存入日志
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogFiled {
    String value() default "";
}
