use testbugucms;
create table metas
(
  id   int auto_increment comment '主键ID'
    primary key,
  name varchar(255) not null comment '属性名',
  type varchar(45)  not null comment '属性类型'
)
  comment '属性表';

INSERT INTO testbugucms.metas (id, name, type) VALUES (1, 'java', 'tag');
INSERT INTO testbugucms.metas (id, name, type) VALUES (2, 'server', 'category');