package org.liezi.common.annotation.utils;

import org.liezi.common.annotation.CheckStringArray;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: lake.lei
 * @date: 2018/8/13 21:03
 * @description:
 */
public class StringArrayValidator implements ConstraintValidator<CheckStringArray, String[]> {
    private Pattern pattern ;
    @Override
    public void initialize(CheckStringArray checkField) {
        if(null == pattern){
            pattern = Pattern.compile(checkField.value());
        }
    }

    @Override
    public boolean isValid(String[] fields, ConstraintValidatorContext constraintValidatorContext) {
        if(null == fields || fields.length<1){
            return true;
        }
        for(String field:fields){
            if(StringUtils.isNotEmpty(field)){
                Matcher matcher = pattern.matcher(field);
                if(!matcher.matches()){
                    return false;
                }
            }
        }
        return true;
    }
}