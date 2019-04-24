see 
[Orascle12c](https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance)

# 快速安装

```
docker pull sath89/oracle-xe-11g
docker run --name orcl -d -p 8080:8080 -p 1521:1521 -v ./data/oracle:/u01/app/oracle sath89/oracle-xe-11g
```

# 使用```SQL*Plus``

```bash
docker exec -it orcl /bin/bash
sqlplus system/oracle
```

# 登录

```
sqlplus system/oracle
```

# Oracle Enterprise Manager Express 12c

```
https://www.terwergreen.com:5500/em/login
```