package com.m.business.student.valid;

import com.m.business.student.input.StudentInput;
import com.m.commons.web.valid.CustomValid;
import com.m.commons.web.valid.MValid;
import org.springframework.stereotype.Component;

@Component
public class AgeValid implements MValid<StudentInput> {

    @Override
    public boolean isValid(CustomValid customValid, StudentInput data) {

        //具体业务处理
        System.out.println("自定义校验：" + data);

        return true;
    }
}
