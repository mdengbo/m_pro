package com.m.business.classes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.m.business.classes.service.ClassesService;
import com.m.data.entity.Classes;
import com.m.data.mapper.ClassesDao;
import org.springframework.stereotype.Service;

/**
 * 班级表(Classes)表服务实现类
 *
 * @author makejava
 * @since 2023-03-29 15:06:35
 */
@Service("classesService")
public class ClassesServiceImpl extends ServiceImpl<ClassesDao, Classes> implements ClassesService {

}

