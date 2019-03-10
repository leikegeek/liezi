package org.liezi.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author: Lake
 * @Date: 2018/7/17 09:53
 * @Description: validator配置类
 */
@Configuration
public class ValidatorConfig {
    /**
     * @Author Lake
     * @Description 快速失败模式，校验到失败即返回
     * @Date 2018/7/17 9:55
     * @Param []
     **/
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}
