use testbugucms;
create table users
(
  id           int auto_increment comment '用户ID'
    primary key,
  username     varchar(45)                         not null comment '用户名',
  password_md5 varchar(45)                         not null comment '密码',
  email        varchar(45)                         null comment '邮箱',
  screen_name  varchar(45)                         null comment '昵称',
  created      timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  logged       timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '是否登录',
  constraint username
    unique (username)
)
  comment '用户表';

INSERT INTO testbugucms.users (id, username, password_md5, email, screen_name, created, logged) VALUES (1, 'jvue', '3e6693e83d186225b85b09e71c974d2d', '', 'admin', '2019-03-15 22:20:11', '2019-03-15 22:20:11');