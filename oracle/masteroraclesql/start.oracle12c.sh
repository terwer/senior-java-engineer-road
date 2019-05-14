#!/usr/bin/env bash
ssh oracle@39.104.66.135
#123456

# env
ORACLE_BASE=/orcl/app/oracle
ORACLE_HOME=$ORACLE_BASE/product/12.1.0/db_1
ORACLE_SID=orcl
export ORACLE_BASE ORACLE_HOME ORACLE_SID
PATH=$ORACLE_HOME/bin:$PATH
export PATH

# 简单启动方法
export ORACLE_HOME_LISTNER=$ORACLE_HOME
cd $ORACLE_HOME/bin
dbstart $ORACLE_HOME_LISTNER
dbshut $ORACLE_HOME_LISTNER
lsnrctl status

# 另一种启动方法
lsnrctl status
echo $ORACLE_SID
export ORACLE_SID=orcl
lsnrctl start   打开监听
sqlplus /nolog    进入sql命令行
SQL> conn / as sysdba    切换权限
SQL> startup    启动数据库
SQL> shutdown immediate 停止数据库

sqlplus hr@orcl