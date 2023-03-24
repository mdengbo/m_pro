package com.m.business.student.controller;

import com.m.business.student.input.StudentInput;
import com.m.business.student.service.StudentService;
import com.m.data.entity.Student;
import com.m.data.r.R;
import com.m.data.r.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/stu")
@Slf4j
@Validated
public class StuController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private StudentService studentService;


    @RequestMapping("/list")
    public R list() {
        //手动设置page
        List<Student> list = studentService.list();
        System.out.println("学生列表:" + dbUrl);
        return RUtils.succ( list);
    }


    /**
     * 参数校验 配合类上的 validated 生效
    * */
    @RequestMapping("/login")
    public R login(@NotBlank(message = "用户名为空") String userName,
                        @NotEmpty(message = "密码不能为空") @Length(min = 6, max = 10, message = "密码长度6-10位") String pwd) {
        log.info("学生登录ruserName:{}， pwd:{}", userName, pwd);
        return RUtils.succ();
    }

    @RequestMapping("/insert")
    public R insert(@Valid @RequestBody StudentInput stu) {

        log.info("[stu insert] 学生info:{}", stu);

        Student saveDo = new Student();
        BeanUtils.copyProperties(stu, saveDo);

        studentService.save(saveDo);

        return RUtils.succ();
    }
}
