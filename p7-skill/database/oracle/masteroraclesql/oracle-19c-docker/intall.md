https://github.com/steveswinsburg/oracle19c-docker

# Config
1.edit `OracleDatabase/SingleInstance/dockerfiles/19.3.0/dbca.rsp.tmpl` , and change `totalMemory=2048` to `totalMemory=4000` or whatever value you want.

2.In Docker Desktop, update the allocated memory to a value more than the value above.

# Building

```
./buildContainerImage.sh -v 19.3.0 -e
```

# Run

```
docker run \
--name oracle19c \
-p 1521:1521 \
-p 5500:5500 \
-p 23:23 \
-e ORACLE_SID=ORCLCDB \
-e ORACLE_PDB=ORCL \
-e ORACLE_PWD=123456 \
-e ORACLE_MEM=4000 \
-v /Users/terwer/Documents/workspace/senior-java-engineer-road/p7-skill/database/oracle/masteroraclesql/oracle-19c-docker/oradata/:/opt/oracle/oradata \
-d \
oracle/database:19.3.0-ee
```

# Getting a shell on the container
First run docker ps to get the container ID. Then run: `docker exec -it <container id> /bin/bash`

Or as root: `docker exec -u 0 -it <container id> /bin/bash`

# 开启远程ssh登录

```
# 进入容器
docker exec -u 0 -it oracle19c /bin/bash

# 安装必备包
yum install vim passwd openssh-server

vim /etc/ssh/sshd_config

# Replace port 22 with a port between 1024 and 65536

# 开启root远程登录
# PermitRootLogin yes

# 开启端口映射

# 后台启动
/usr/sbin/sshd -D &

# 生成秘钥
ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key
ssh-keygen -t ecdsa -f /etc/ssh/ssh_host_ecdsa_key
ssh-keygen -t ed25519 -f /etc/ssh/ssh_host_ed25519_key
```

# 远程链接
```
ssh root@127.0.0.1 -p 23
```

https://localhost:5500/em