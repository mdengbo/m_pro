package com.m.business.student.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.m.business.student.valid.AgeValid;
import com.m.commons.web.valid.CustomValid;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@CustomValid(message = "生日年龄不匹配", hander = AgeValid.class)
public class StudentInput implements Serializable {

    @NotEmpty(message = "name 为空")
    private String name;

    @Min(value = 10, message = "age 不能小于10")
    @Max(value = 20,message = "age 不能超过20")
    @NotNull
    private Integer age;

    @Email(message = "邮箱格式不对")
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @NotNull(message = "生日不能为空")
    @Past(message = "生日范围不对") //今天之前的日期
    @DateTimeFormat(pattern = "yyyy-MM-dd") //表单提交
    @JsonFormat(pattern = "yyyy-MM-dd") //对@requestBody
    private Date birthday;

    @NotNull(message = "性别不能为空")
    private String sex;

}
