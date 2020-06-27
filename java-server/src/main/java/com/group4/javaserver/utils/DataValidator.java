package com.group4.javaserver.utils;

import com.group4.javaserver.exception.DataException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 数据校验类
 */
@Component
public class DataValidator {

    /**
     * 获取验证器
     */
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证数据的方法-不带分组
     */
    public void validate(Object data) {
        //
        Set<ConstraintViolation<Object>> errors = validator.validate(data);
        if(errors.size() != 0){
            throw new DataException(3001,errors.iterator().next().getMessage());
        }
    }

    /**
     * 验证数据的方法-分组
     */
    public void validate(Object data, Class<?> clazz) {
        //
        Set<ConstraintViolation<Object>> errors = validator.validate(data, clazz);
        if(errors.size() != 0){
            throw new DataException(3001,errors.iterator().next().getMessage());
        }
    }
}
