package com.m.business.classes.controller;

import com.m.business.classes.service.ClassesService;
import com.m.data.entity.Classes;
import com.m.data.r.R;
import com.m.data.r.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Description 学生
 * @Author Mdengbo
 * @Date 2023/3/29 下午2:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/class")
@Slf4j
@Validated
public class ClsController {

    @Autowired
    private ClassesService classesService;

    @RequestMapping("/getById")
    public R getClsByCid(@NotNull Integer cId) {
        Classes one = classesService.getById(cId);
        return RUtils.succ(one);
    }

}
