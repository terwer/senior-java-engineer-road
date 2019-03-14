# 安装镜像

# 列出所有镜像

```
docker images
```

# 拉起镜像
```
docker pull ngnix
```

# 删除镜像

先删除引用了该镜像的容器
```
# 查看所有container
docker ps -a
# 删除容器
docker rm container_id
```
或者先用``docker rmi imageid``进行删除，会报错被哪个容器引用，然后使用``docker rm container_id``删除掉该容器即可

# 删除所有中间镜像

```
docker image prune -a
```
