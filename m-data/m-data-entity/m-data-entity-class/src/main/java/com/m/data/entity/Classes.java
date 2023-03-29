package com.m.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.m.data.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description   班级实体类 class
 * @author  Mdengbo
 * @date    2023/3/29
*/
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class Classes extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Long id;
    //姓名
    private String className;
    //年龄
    private Integer classNum;

    }

