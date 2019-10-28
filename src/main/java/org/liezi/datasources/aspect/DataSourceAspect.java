
package org.liezi.datasources.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.liezi.common.utils.SystemConstants;
import org.liezi.datasources.annotation.DataSource;
import org.liezi.datasources.config.DynamicContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 * @author: lake.lei
 * @date: 2019/2/24 17:29
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(org.liezi.datasources.annotation.DataSource) " +
            "|| @within(org.liezi.datasources.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class targetClass = point.getTarget().getClass();
        Method method = signature.getMethod();

        DataSource targetDataSource = (DataSource)targetClass.getAnnotation(DataSource.class);
        DataSource methodDataSource = method.getAnnotation(DataSource.class);
        if(targetDataSource != null || methodDataSource != null){
            String value;
            if(methodDataSource != null){
                String methodValue = methodDataSource.value();
                //参数取值优先级1
                if(methodValue.equals(SystemConstants.DS_PARAMS)){
                    Object[] inParams = point.getArgs();
                    Object requstBody = inParams[0];
                    if(requstBody instanceof String  && null != requstBody){
                        String paramValue = (String)requstBody;
                        value = paramValue;
                    }else {
                        value = methodValue;
                    }
                }else{
                    // 方法注解优先级2
                    value = methodValue;
                }
            }else {
                // 类注解优先级3
                value = targetDataSource.value();
            }
            DynamicContextHolder.push(value);
            logger.debug("set datasource is {}", value);
        }

        try {
            return point.proceed();
        } finally {
            DynamicContextHolder.poll();
            logger.debug("clean datasource");
        }
    }
}