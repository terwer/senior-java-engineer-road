-- show databases;
-- select version();
-- drop user 'terwer'@'%';
-- CREATE USER 'terwer'@'%' IDENTIFIED BY '123456';
-- GRANT ALL PRIVILEGES ON *.* TO 'terwer'@'%' WITH GRANT OPTION;
-- flush privileges;
-- create database test default character set utf8 collate utf8_general_ci;

-- user
create table if not exists user
(
    id       int auto_increment
        primary key,
    username varchar(50) null,
    password varchar(50) null,
    birthday varchar(50) null
)
    charset = utf8;

-- user data
INSERT INTO test.user (id, username, password, birthday)
VALUES (1, 'lisi', '123', '2019-12-12');
INSERT INTO test.user (id, username, password, birthday)
VALUES (2, 'tom', '123', '2019-12-12');
INSERT INTO test.user (id, username, password, birthday)
VALUES (8, '测试2', null, null);
INSERT INTO test.user (id, username, password, birthday)
VALUES (9, '测试3', null, null);
