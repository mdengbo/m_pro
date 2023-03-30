package com.m.business.student.controller;

import com.m.business.student.input.StudentInput;
import com.m.business.student.service.StudentService;
import com.m.commons.web.apiversion.ApiVersion;
import com.m.commons.web.apiversion.PlatFormTypeEnum;
import com.m.data.entity.Student;
import com.m.data.r.R;
import com.m.data.r.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope //刷新远程配置信息
public class StuController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${m.name}")
    private String name;

    @Autowired
    private StudentService studentService;


    @RequestMapping("/getById")
    @ApiVersion(1.0)
    public R getById(Integer sId) {

        Student stu = studentService.getById(sId);
        //feign 服务调用  查询班级信息
        System.out.println("stu getById 1.0");

        return RUtils.succ(stu);
    }

    @RequestMapping("/getById")
    @ApiVersion(2.0)
    public R getById2(Integer sId) {

        Student stu = studentService.getById(sId);
        //feign 服务调用  查询班级信息
        System.out.println("stu getById 2.0");

        return RUtils.succ(stu);
    }

    @RequestMapping("/test")
    public R test() {
        //手动设置page
        System.out.println("学生name:" + name);
        return RUtils.succ("默认1.0 pc");
    }


    @RequestMapping("/list")
    @ApiVersion(1.0)
    public R list0() {
        //手动设置page
        List<Student> list = studentService.list();
        System.out.println("学生列表:" + dbUrl);
        System.out.println("学生name:" + name);
        return RUtils.succ("默认1.0 pc");
    }


    @RequestMapping("/list")
    @ApiVersion(value = 1.0, srcType = PlatFormTypeEnum.ANDROID)
    public R list3() {
        //手动设置page
        List<Student> list = studentService.list();
        System.out.println("学生列表:" + dbUrl);
        return RUtils.succ("1.0 ANDROID");
    }

    @RequestMapping("/list")
    @ApiVersion(value = 1.0, srcType = PlatFormTypeEnum.IOS)
    public R list4() {
        //手动设置page
        List<Student> list = studentService.list();
        System.out.println("学生列表:" + dbUrl);
        return RUtils.succ("1.0 ISO");
    }

    @RequestMapping("/list")
    @ApiVersion(value = 2.0)
    public R list2() {
        //手动设置page
        List<Student> list = studentService.list();
        System.out.println("学生列表:" + dbUrl);
        return RUtils.succ("2.0");
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
