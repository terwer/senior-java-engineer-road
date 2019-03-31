use testbugucms;
create table middles
(
  id   int auto_increment comment '主键ID'
    primary key,
  p_id int not null comment '父级属性ID',
  m_id int not null comment '属性ID'
)
  comment '文章属性关联表';

INSERT INTO testbugucms.middles (id, p_id, m_id) VALUES (1, 1, 1);
INSERT INTO testbugucms.middles (id, p_id, m_id) VALUES (2, 1, 2);