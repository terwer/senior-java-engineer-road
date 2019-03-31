use testbugucms;
create table comments
(
  id       int auto_increment comment '主键ID'
    primary key,
  post_id  int                                 not null comment '文章ID',
  p_id     int                                 null comment '父评论id',
  content  text                                not null comment '评论内容',
  name     varchar(255)                        null comment '名称',
  email    varchar(255)                        null comment '邮箱',
  website  varchar(255)                        null comment '主页',
  agree    int       default 0                 not null comment '通过原因',
  disagree int       default 0                 not null comment '拒绝原因',
  ip       varchar(255)                        null comment 'ip',
  agent    varchar(255)                        null comment '来源',
  created  timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
  comment '评论表';

INSERT INTO testbugucms.comments (id, post_id, p_id, content, name, email, website, agree, disagree, ip, agent, created) VALUES (1, 1, null, '## 测试评论
这是我的网址[jvue](http://www.terwergreen.com)', 'terwer', '920049380@qq.com', 'http://www.terwergreen.com', 1, 0, '0.0.0.1', '', '2019-03-15 22:20:11');