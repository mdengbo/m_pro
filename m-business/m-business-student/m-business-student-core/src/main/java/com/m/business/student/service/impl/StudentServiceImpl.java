package com.m.business.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.m.business.student.service.StudentService;
import com.m.data.entity.Student;
import com.m.data.mapper.StudentDao;
import org.springframework.stereotype.Service;

/**
 * 学生表(Student)表服务实现类
 *
 * @author makejava
 * @since 2023-03-22 10:12:45
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

}

