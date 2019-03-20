package org.liezi.common.annotation;

import java.lang.annotation.*;

/**
 * @author Lake
 * @description: 超级注解
 * @date 2019/3/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SuperFun {
    /**
     * 日志功能注解
     * 默认为空表示不做处理
     * 非空则当作此日志操作描述
     * @return
     */
    String logFun() default "";
    /**
     * 数据校验注解
     * 默认为false 不做校验
     * 为true则校验
     * @return
     */
    boolean validationFun() default false;
    /**
     * 加解密注解 默认为空 不做处理
     * @return
     */
    String cryptoFun() default "";

}
