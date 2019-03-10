package org.liezi.common.annotation;

import org.liezi.common.annotation.utils.StringArrayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *
 * @author: lake.lei
 * @date: 2018/8/13 21:01
 * @description:字段规则校验
 */
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringArrayValidator.class)
@Documented
public @interface CheckStringArray {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();
}
