package com.m.commons.web.valid;

import com.m.commons.web.utils.ApplicationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验 需实现ConstraintValidator<配合使用的注解， 校验对象类型>
 * */
public class CustomValidHandler  implements ConstraintValidator<CustomValid, Object> {

    private CustomValid customValid;

    /**
     * 获取当前注解信息
     * */
    @Override
    public void initialize(CustomValid constraintAnnotation) {
        this.customValid = constraintAnnotation;
    }

    /**
     * @param value 校验对象
     * @return true 通过校验
     * */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        System.out.println("校验开始,传入值:" + value);
        if (null != value) {

            //获取自定义处理器实际类型
            Class<? extends MValid> hander = customValid.hander();

            //容器中获取对象
            MValid mValid = ApplicationUtils.getBean(hander);

            return mValid.isValid(customValid, value);
        }

        return true;
    }
}
