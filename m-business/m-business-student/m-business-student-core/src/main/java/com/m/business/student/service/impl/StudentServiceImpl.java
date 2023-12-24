package com.m.business.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.m.business.feign.classes.ClsFeign;
import com.m.business.student.service.StudentService;
import com.m.commons.redis.utils.RedisUtils;
import com.m.data.domain.StudentVo;
import com.m.data.entity.Classes;
import com.m.data.entity.Student;
import com.m.data.mapper.StudentDao;
import com.m.data.r.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 学生表(Student)表服务实现类
 *
 * @author makejava
 * @since 2023-03-22 10:12:45
 */
@Service("studentService")
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Autowired
    private ClsFeign clsFeign;

    @Autowired
    StudentDao studentDao;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public Student getById(Serializable id) {

        Student stu = super.getById(id);

        redisUtils.set("redis_test", stu);
        redisUtils.setEx("redis_test_expire", stu, 30, TimeUnit.SECONDS);

        //远程调用 class
        R<Classes> clsByCid = clsFeign.getClsByCid(Math.toIntExact(stu.getCid()));
        log.info("[feign class] classs_info:{}", clsByCid.getData());
        System.out.println("[feign class] classs_info:" + clsByCid.getData());
        return super.getById(id);
    }

    public StudentVo getStuInfo(String id) {
        return studentDao.getBYPk(id);
    }

}

