# jvm学习脉络

![](media/16461898544999/16461898943909.jpg)

# class文件格式

![](media/16461898544999/16461986232744.jpg)

![](media/16461898544999/16462222165309.jpg)

![JVM](media/16461898544999/JVM.png)

* 类和接口不一定定义在文件里，也可以通过类加载器直接生成
* class文件是一组以8位字节为基础单位的二进制流，16位、32位、64位分别通过构造2个、4个、8个连续的8位字节来表示
* 必须严格按顺序存放，不能有间隔
* class文件格式采用一种类似于C语言结构体的伪结构来存储数据，这种伪结构中只有两种数据类型：无符号数和表。
* class文件中的数据要么是单个值，要么是二维表。
* 表的每一项长度不固定
* 可以使用`java.io.DataInput`、`java.io.DataInputStream`、`java.io.DataOutput`、`java.io.DataOutputStream`来访问这种格式的文件
## class文件的组织结构

### 文件结构
* 魔数    
  唯一作用是确保文件能被虚拟机接受，固定值不变，是 `oxCAFEBABE`
* 本文件的版本信息    
  主版本号(major_version)和副版本号(minor_version)共同构成了class文件的版本号

![](media/16461898544999/16462105358710.jpg)

常见的jdk1.8的版本就是52.0,就是44+8.0来的

* 常量池           
  主要保存字符串常量、类名、接口名、
  常量池以 `1~constant_pool_count-1` 为索引，第一个字节为类型标记，用于标记格式，称为tagbyte
* 访问标志

![](media/16461898544999/16462114252502.jpg)

由标志构成的掩码，用于表示类或者接口的访问权限
* 类索引      
  必须是常量表的有效索引       
  CONSTANTS_Class_info结构体
* 父类索引       
  必须是常量表的有效索引      
  0或者CONSTANT_Class_info结构体类型
* 接口索引集合          
  0~interfaces_count
* 字段表集合          
  field_info结构
* 方法表集合       
  method_info结构
* 属性表集合       
  attribute_info结构

![](media/16461898544999/16461978984173.jpg)

### 字段描述符

![](media/16461898544999/16462728690392.jpg)

### 方法描述符

0个或者多个参数描述符，以及一个返回值描述符。V 表示方法没有返回值。

### 常量池

常量池的通用结构

![](media/16461898544999/16462730287860.jpg)

![](media/16461898544999/16462730848528.jpg)

#### CONSTANT_Class_info

![](media/16461898544999/16462732085660.jpg)

数组类型的描述符中描述的数组，维度必须小于255。

#### CONSTANT_Fieldref_info、CONSTANT_Methodref_info和CONSTANT_InterfaceMethodref_info

![](media/16461898544999/16462734448522.jpg)

#### CONSTANT_String_info

![](media/16461898544999/16462736089196.jpg)

成员必须是 CONSTANT_Utf8_info 结构

#### CONSTANT_Integer_info和CONSTANT_Float_info

![](media/16461898544999/16462739241191.jpg)

* Integer采用高位优先存储
* Float按照IEEE 754标准存储

![](media/16461898544999/16462740679542.jpg)

#### CONSTANT_Long_info和CONSTANT_Double_info

![](media/16461898544999/16462744578726.jpg)

所有的成员都占2个项的空间。备注：历史设计原因。

long的表示

![](media/16461898544999/16462761062501.jpg)

double的表示，同样是IEEE 754标准

![](media/16461898544999/16462761549527.jpg)

#### CONSTANT_NameAndType_info

tag最大值为CONSTANT_NameAndType(12)

![](media/16461898544999/16462769040985.jpg)

#### CONSTANT_Utf8_info

![](media/16461898544999/16462769818970.jpg)

tag值为CONSTANT_Utf8(1)

Utf8标注具体规则如下

![](media/16461898544999/16462771814705.jpg)


![](media/16461898544999/16462772127953.jpg)

![](media/16461898544999/16462772411325.jpg)

![](media/16461898544999/16462772595994.jpg)

![](media/16461898544999/16462772972002.jpg)

#### CONSTANT_MethodHandle_info

![](media/16461898544999/16462773778119.jpg)

tag最大值CONSTANT_MethodHandle(15)

#### CONSTANT_MethodType_info

![](media/16461898544999/16462775926192.jpg)

tag的最大值为CONSTANT_MethodType(16)

#### CONSTANT_InvodeDynamic_info

![](media/16461898544999/16462779067036.jpg)

tag的最大值为CONSTANT_InvodeDynamic(18)

### 字段

每个字段都是 `field_info` 结构定义的

![](media/16461898544999/16462781182748.jpg)

![](media/16461898544999/16462781733230.jpg)

### 方法

所有的方法都是由 `method_info` 结构来定义

![](media/16461898544999/16462782470461.jpg)

![](media/16461898544999/16462842496792.jpg)

### 属性

![](media/16461898544999/16462842754788.jpg)

![](media/16461898544999/16462843578111.jpg)

![](media/16461898544999/16462843843954.jpg)

![](media/16461898544999/16462844191710.jpg)


![](media/16461898544999/16462844520353.jpg)

![](media/16461898544999/16462844799592.jpg)

![](media/16461898544999/16462845033648.jpg)

![](media/16461898544999/16462845258243.jpg)

![](media/16461898544999/16462845505391.jpg)

#### ConstantValue属性

![](media/16461898544999/16462847352444.jpg)

![](media/16461898544999/16462847499611.jpg)

#### Code属性

![](media/16461898544999/16462848057268.jpg)

#### StackMapTable属性

![](media/16461898544999/16462855839429.jpg)

![](media/16461898544999/16462856488815.jpg)


#### Exceptions属性

![](media/16461898544999/16462857006058.jpg)

#### InnerClass属性

![](media/16461898544999/16462857266320.jpg)

![](media/16461898544999/16462859128500.jpg)

#### EnclosingMethod属性

![](media/16461898544999/16462859543425.jpg)

#### Synthetic属性

![](media/16461898544999/16462860172624.jpg)

#### Signature属性

![](media/16461898544999/16462860446828.jpg)

![](media/16461898544999/16462861339860.jpg)

#### SourceFile属性

![](media/16461898544999/16462861826337.jpg)

#### SourceDebugExtension

![](media/16461898544999/16462863501238.jpg)

#### LineNumberTable属性

![](media/16461898544999/16462863882395.jpg)

#### LocalVariableTable属性

![](media/16461898544999/16462864844269.jpg)

#### LocalVariableTypeTable属性

![](media/16461898544999/16462872938654.jpg)

#### Deprecated属性

![](media/16461898544999/16462873270570.jpg)

#### RuntimeVisiableAnnotations属性

![](media/16461898544999/16462873868529.jpg)

![](media/16461898544999/16462874079174.jpg)

![](media/16461898544999/16462874308831.jpg)

![](media/16461898544999/16462874509630.jpg)

#### RuntimeInvisiableAnnotation属性

![](media/16461898544999/16462875041241.jpg)

#### RuntimeVisiableParameterAnnotions属性

![](media/16461898544999/16462875456969.jpg)

#### RuntimeinvisiableAnnotations属性

![](media/16461898544999/16462875867766.jpg)

#### RuntimeVisiableTypeAnnotations属性

![](media/16461898544999/16462876296065.jpg)

![](media/16461898544999/16462876540810.jpg)

![](media/16461898544999/16462876760868.jpg)

![](media/16461898544999/16462877038416.jpg)

![](media/16461898544999/16462877168272.jpg)

![](media/16461898544999/16462877343250.jpg)

##### target_info联合体

![](media/16461898544999/16462878910442.jpg)

![](media/16461898544999/16462882535287.jpg)

![](media/16461898544999/16462882807340.jpg)

![](media/16461898544999/16462882954764.jpg)

![](media/16461898544999/16462883149777.jpg)

![](media/16461898544999/16462883321399.jpg)

![](media/16461898544999/16462883465099.jpg)

![](media/16461898544999/16462883644861.jpg)

![](media/16461898544999/16462883859249.jpg)

![](media/16461898544999/16462884040031.jpg)



##### type_path机构体

![](media/16461898544999/16462878321978.jpg)

![](media/16461898544999/16462884357307.jpg)

#### RuntimeInvisiableTypeAnnotayions属性

![](media/16461898544999/16462885503738.jpg)

#### AnnotationDefault属性

![](media/16461898544999/16462887232512.jpg)

#### BootstrapMethods属性

![](media/16461898544999/16462888790912.jpg)

#### MethodParameters属性

![](media/16461898544999/16462889341654.jpg)

## 格式检查

* 前四个字节必须是正确的魔数
* 所有属性必须符合合适的长度
* class文件的内容不能缺失，尾部也不能有多余的字节
* 常量池必须符合约束

## 代码约束

### 静态约束

静态约束确定了虚拟机指令在Code数组中是如何排列的

### 结构化约束

Code数组上的结构化约束是为了限制Java虚拟机指令直接的关系

## 文件校验

### 类型检查验证

* 版本号大于50的必须使用类型检查验证。既jdk版本必须大于jdk1.6

### 类型推导验证

## 虚拟机限制

# Java虚拟机结构

# Java虚拟机编译器

# 加载链接与初始化

# 虚拟机指令集

