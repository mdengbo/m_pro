package com.m.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.m.data.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 学生表(Student)表实体类
 *
 * @author makejava
 * @since 2023-03-22 10:12:42
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class Student extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //邮件
    private String email;
    //生日
    private Date birthday;
    //性别 0-男， 1-女
    private Integer sex;

    }

