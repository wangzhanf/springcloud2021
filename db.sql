drop database if exists spring_cloud_test;
create database if not exists spring_cloud_test;

use spring_cloud_test;

# 创建数据表
create table department
(
    did       int auto_increment comment '部门编号'
        primary key,
    dname     varchar(50) null comment '部门名称',
    db_source varchar(50) null comment '数据源'
)
    comment '部门表';

# 插入测试数据
insert into department (dname, db_source) VALUES ('教学部',DATABASE()),
                                                 ('学生部',DATABASE()),
                                                 ('后勤部',DATABASE()),
                                                 ('财务部',DATABASE()),
                                                 ('运输部',DATABASE()),
                                                 ('采购部',DATABASE())
                                                 ;



drop database if exists spring_cloud_test_2;
create database if not exists spring_cloud_test_2;

use spring_cloud_test_2;

# 创建数据表
create table department
(
    did       int auto_increment comment '部门编号'
        primary key,
    dname     varchar(50) null comment '部门名称',
    db_source varchar(50) null comment '数据源'
)
    comment '部门表';

# 插入测试数据
insert into department (dname, db_source) VALUES ('教学部',DATABASE()),
                                                 ('学生部',DATABASE()),
                                                 ('后勤部',DATABASE()),
                                                 ('财务部',DATABASE()),
                                                 ('运输部',DATABASE()),
                                                 ('采购部',DATABASE())
;


drop database if exists spring_cloud_test_3;
create database if not exists spring_cloud_test_3;

use spring_cloud_test_3;

# 创建数据表
create table department
(
    did       int auto_increment comment '部门编号'
        primary key,
    dname     varchar(50) null comment '部门名称',
    db_source varchar(50) null comment '数据源'
)
    comment '部门表';

# 插入测试数据
insert into department (dname, db_source) VALUES ('教学部',DATABASE()),
                                                 ('学生部',DATABASE()),
                                                 ('后勤部',DATABASE()),
                                                 ('财务部',DATABASE()),
                                                 ('运输部',DATABASE()),
                                                 ('采购部',DATABASE())
;
