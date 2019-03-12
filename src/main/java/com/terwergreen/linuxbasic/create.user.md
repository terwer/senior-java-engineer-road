# linux下创建用户，给用户设置密码，给用户授权

## linux下的用户是属于组的，所以需要创建一个组，划分给用户。创建命令：

在root下执行
```bash
groupadd  ver     
```
创建一个组ver

## 创建用户

```bash
useradd -m -g ver terwer     # 新增用户terwer
```

# 设置用户密码
```bash
passwd  terwer     # 换行输密码
```

# 切换到home目录，在root用户下给terwer授权
```bash
chmod 775 -R terwer
```