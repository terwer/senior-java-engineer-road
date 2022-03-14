# 创建数据库，指定编码为utf8mb4
# create database zdy_mybatis default character set utf8mb4 collate utf8mb4_general_ci;

# table user
create table if not exists user(
    id      int         null,
    usename varchar(50) null
);

# user data
INSERT INTO user(id, usename) VALUES (1, '唐有炜');
INSERT INTO user(id, usename) VALUES (2, 'tyw');
INSERT INTO user (id, usename) VALUES (3, '张月');
