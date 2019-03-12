```bash
ssh root@terwergreen.com
ssh docker@terwergreen.com
```

# 删除已安装的Docker

```
# Uninstall installed docker
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-selinux \
                  docker-engine-selinux \
                  docker-engine
```

# 配置阿里云Docker Yum源

```
# Set up repository
sudo yum install -y yum-utils device-mapper-persistent-data lvm2

# Use Aliyun Docker
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

# 安装 Docker CE

更新 yum 软件源缓存，并安装 docker-ce。

```
sudo yum makecache fast
sudo yum install docker-ce
```

启动Docker服务

```
# Start docker service
systemctl enable docker
systemctl start docker
```

# 建立 docker 用户组

```bash
sudo groupadd docker
```

将当前用户加入 ``docker`` 组：

```bash
sudo usermod -aG docker $USER
```

或者创建一个新用户

```bash
useradd -m -g docker docker
passwd docker
# Changing password for user docker.
# New password:Changing password for user docker.
# New password:
# Retype new password:
# passwd: all authentication tokens updated successfully.
```

# 测试 ``Docker`` 安装是否正确

```bash
docker run hello-world
# Unable to find image 'hello-world:latest' locally
# latest: Pulling from library/hello-world
# 1b930d010525: Pull complete
# Digest: sha256:2557e3c07ed1e38f26e389462d03ed943586f744621577a99efb77324b0fe535
# Status: Downloaded newer image for hello-world:latest

# Hello from Docker!
# This message shows that your installation appears to be working correctly.

# To generate this message, Docker took the following steps:
# 1. The Docker client contacted the Docker daemon.
# 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
#    (amd64)
# 3. The Docker daemon created a new container from that image which runs the
#    executable that produces the output you are currently reading.
# 4. The Docker daemon streamed that output to the Docker client, which sent it
#    to your terminal.

# To try something more ambitious, you can run an Ubuntu container with:
# $ docker run -it ubuntu bash

# Share images, automate workflows, and more with a free Docker ID:
# https://hub.docker.com/

# For more examples and ideas, visit:
# https://docs.docker.com/get-started/
```

# CentOS7.6 配置镜像加速器
[阿里云加速器](https://cr.console.aliyun.com/cn-hangzhou/mirrors)

 /etc/docker/daemon.json 中写入如下内容（如果文件不存在请新建该文件）
```json
{
  "registry-mirrors": [
    "https://registry.docker-cn.com"
  ]
}
```

之后重新启动服务。

```bash
sudo systemctl daemon-reload
sudo systemctl restart docker
```

# 检查加速器是否生效

命令行执行 ``docker info``，如果从结果中看到了如下内容，说明配置成功。

```bash
Registry Mirrors:
 https://registry.docker-cn.com/
```

