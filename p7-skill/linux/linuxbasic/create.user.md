# linux下创建用户，给用户设置密码，给用户授权

## linux下的用户是属于组的，所以需要创建一个组，划分给用户。创建命令：

在root下执行
```bash
groupadd  ver     
```
创建一个组ver

## 创建用户

```bash
useradd -m -g root terwer     # 新增用户terwer
```

# 设置用户密码
```bash
passwd  terwer     # 换行输密码
```

# 切换到home目录，在root用户下给terwer授权
```bash
chmod 777 -R terwer
```

# 把terwer添加道sudouser

1、切换到root用户，运行visudo命令

```
visudo
```

2、找到root ALL=(ALL) ALL，在下面添加一行 xxx ALL=(ALL) ALL 其中xxx是要加入的用户名称

```
terwer ALL=(ALL) ALL
```
  
