# 创建数据库，指定编码为utf8mb4
# create database jdbc_simple default character set utf8mb4 collate utf8mb4_general_ci;

# table user
create table if not exists user(
    id   int         null,
    name varchar(50) null
);

# user data
INSERT INTO jdbc_simple.user (id, name) VALUES (1, '唐有炜');
INSERT INTO jdbc_simple.user (id, name) VALUES (2, 'tyw');
INSERT INTO jdbc_simple.user (id, name) VALUES (3, '张月');
