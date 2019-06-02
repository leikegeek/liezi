package org.liezi.datasources.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * @author: lake.lei
 * @date: 2019/2/24 17:29
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
