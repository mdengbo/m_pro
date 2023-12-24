package com.m.business.classes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.m.business.classes.service.ClassesService;
import com.m.commons.web.apiversion.ApiVersion;
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
    @ApiVersion(1.0)
    public R getClsByCid(@NotNull Integer cId) {
        LambdaQueryWrapper<Classes> wrapper  = new LambdaQueryWrapper<>();
        final Supplier<Classes> classesSupplier = Classes::new;
        wrapper.eq(Classes::getClassName, "1");
        Classes one = classesService.getById(cId);
        System.out.println("class getById 1.0");
        return RUtils.succ(one);
    }

    @RequestMapping("/getById")
    @ApiVersion(2.0)
    public R getClsByCid2(@NotNull Integer cId) {
        Classes one = classesService.getById(cId);
        System.out.println("class getById 2.0");
        return RUtils.succ(one);
    }

    public static void main(String[] args) {
        new b();
        System.out.println("========");
        new b();

    }


}
