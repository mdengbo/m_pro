# 班级表
create table classes (
     id bigint primary key auto_increment comment '主键',
     class_name varchar(10) not null unique comment '班级名称',
     class_num int not null comment '班级人数',
     create_time datetime comment '创建时间',
     update_time datetime comment '更新时间',
     status tinyint default 0 comment '状态 0-可用， 1-不可用',
     del_flag tinyint default 0 comment '删除标志 0-可用， 1删除'
) comment '班级表';

insert into classes values(null, '一年一班', 5, now(), now(), 0, 0);
insert into classes values(null, '一年二班', 5, now(), now(), 0, 0);

#学生表
create table student (
     id bigint primary key auto_increment comment '主键',
     cid bigint not null  comment '班级id',
     name varchar(10) not null unique comment '姓名',
     age tinyint not null comment '年龄',
     email varchar(30) not null comment '邮箱',
     sex tinyint not null comment '性别 0-男， 1-女',
     birthday date not null comment '生日',
     create_time datetime comment '创建时间',
     update_time datetime comment '更新时间',
     status tinyint default 0 comment '状态 0-可用， 1-不可用',
     del_flag tinyint default 0 comment '删除标志 0-可用， 1删除'
) comment '学生表';

insert into student values(null, '1', 'xiaohong', 10, 'xh@qq.com', 0, '2012-08-09', now(), now(), 0, 0);
insert into student values(null, '2', 'Ww', 10, 'Ww@qq.com', 0, '2012-08-09', now(), now(), 0, 0);