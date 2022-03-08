# 深入Java虚拟机_ClassLoader
# Java类加载器深入剖析

1、 ClassLoader（类加载器）

## Java程序的生命周期

![](media/16422631095955/16422633075047.jpg)

## 类的加载、连接与初始化

![](media/16422631095955/16422634230987.jpg)

![](media/16422631095955/16422634661654.jpg)

## 类加载陷阱实例

```
/**
 * @name: Singleton
 * @author: terwer
 * @date: 2022-02-12 13:51
 **/
class Singleton {
    private static Singleton singleton = new Singleton();
    public static int counter1;
    public static int counter2 = 0;

    private Singleton() {
        counter1++;
        counter2++;
    }

    public static Singleton getInstance() {
        return singleton;
    }
}

public class MyTest {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1 = " + singleton.counter1);
        System.out.println("counter2 = " + singleton.counter2);
    }
}
```

结果是

![](media/16422631095955/16446459522537.jpg)

如果调整顺序，结果如下

![](media/16422631095955/16446474351446.jpg)

原因分析



## Java对类的使用方式

![](media/16422631095955/16422634765332.jpg)

![](media/16422631095955/16422634843794.jpg)

![](media/16422631095955/16422635053463.jpg)


## 类的加载是什么

![](media/16422631095955/16422634951285.jpg)

## 加载类的方式

![](media/16422631095955/16422635156962.jpg)

![](media/16422631095955/16422635251804.jpg)

## 类的加载结果

![](media/16422631095955/16424433226869.jpg)

## 类加载器的类型

![](media/16422631095955/16424433775674.jpg)




# 我的独立博客

http://120.25.179.230/post/java-lei-jia-zai-qi-shen-ru-pou-xi.html