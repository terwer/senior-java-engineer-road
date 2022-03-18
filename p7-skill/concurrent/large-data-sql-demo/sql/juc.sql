# create database juc default character set utf8mb4 collate utf8mb4_general_ci;
create table user
(
    id       int auto_increment,
    username varchar(200) not null,
    password varchar(200) null,
    age      int          null,
    sex      int          null,
    constraint user_pk
        primary key (id)
)comment '用户信息';
