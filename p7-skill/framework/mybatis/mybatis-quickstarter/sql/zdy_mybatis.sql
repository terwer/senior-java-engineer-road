# 创建数据库，指定编码为utf8mb4
# create database zdy_mybatis default character set utf8mb4 collate utf8mb4_general_ci;

# table user
create table if not exists user(
    id      int         null,
    usename varchar(50) null
);

# user data
insert into user (id, username)
values (1, 'tyw'), (2, '张月'), (4, 'haha'), (5, 'dali');
