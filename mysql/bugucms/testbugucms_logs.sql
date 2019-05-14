use testbugucms;
create table logs
(
  id      int auto_increment comment '日志表'
    primary key,
  action  varchar(255)                        null comment '操作',
  data    text                                null comment '数据',
  message varchar(255)                        null comment '信息',
  type    varchar(255)                        null comment '类型',
  ip      varchar(255)                        null comment 'ip',
  user_id int                                 null comment '用户ID',
  created timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
  comment '日志表';

