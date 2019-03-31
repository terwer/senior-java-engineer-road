use testbugucms;
create table options
(
  option_id    bigint unsigned auto_increment comment '配置ID'
    primary key,
  option_group varchar(64) default '' not null comment '配置组',
  option_name  varchar(64) default '' not null comment '配置名',
  option_value longtext               not null comment '配置值',
  constraint option_name
    unique (option_name)
)
  comment '站点配置表' charset = utf8mb4;

INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (11, 'siteConfig', 'domain', 'localhost:8081');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (12, 'siteConfig', 'weburl', 'http://localhost:8081');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (13, 'siteConfig', 'webtheme', 'default');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (14, 'siteConfig', 'webname', '远方的灯塔');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (15, 'siteConfig', 'webslogen', '专注于服务端技术分享');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (16, 'siteConfig', 'keywords', '软件架构、服务端开发、Java、Spring、Dubbo、Zookeeper、微服务');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (17, 'siteConfig', 'description', '远方的灯塔是关注与分享互联网及服务端开发技术的个人博客，致力于Java后端开发及服务端技术、软件架构、微服务技术分享。同时也记录个人的一路点滴，所蕴含的包括前端、后端、数据库等知识，欢迎您关注我们。');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (18, 'siteConfig', 'debug', 'false');
INSERT INTO testbugucms.options (option_id, option_group, option_name, option_value) VALUES (19, 'siteConfig', 'beianinfo', '粤ICP备18023717号-1');