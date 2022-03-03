# Java SE 第一讲：Java SE平台与JDK

Java SE：Java Standard Edition       
Java ME: Java Micro Edition    
Java EE：Java Enterprise Edition    

Java是由Sun公司推出的（2010年初被Oracle公司收购）。

收购价格：74亿美金

J2SE、J2ME、J2EE

JDK：Java Development Kit （Java开发必备）            
JRE：Java Runtime Environment （Java执行环境）

JDK包含了JRE。

Jdk 1.4, jdk 1.5(5.0), jdk 1.6(6.0)

jdk 1.5(5.0)：Tiger，老虎        
jdk 1.6(6.0)：Mustang，野马             
jdk 1.7(7.0): Dolphin，海豚            
jdk 1.8(8.0) LTS    
java 11 LTS     
java 17 LTS      

版本历史

https://zh.wikipedia.org/zh-cn/Java%E7%89%88%E6%9C%AC%E6%AD%B7%E5%8F%B2#JDK_Alpha_%E5%92%8C_Beta

https://www.wdbyte.com/java/java-17/

下载JDK      
安装JDK     
设定环境变量（可以是用户变量，也可以是系统变量），指向JDK安装目录中的bin目录        
通过运行，输入cmd打开命令行窗口，输入java –version，显示出Java版本信息

接下来就可以编写Java程序了。

可以直接使用windows记事本来编写Java程序，也可以使用Editplus，UltraEdit等高级文本编辑工具编写Java程序，还可以使用专业的IDE（Integrated Development Environment）编写。

第一节课，我们使用记事本实现一个Hello World的Java程序。

所有的Java代码，其后缀都是以java结尾。

Java程序的执行过程分为两步：        
编译       
执行         

Class文件是字节码文件，程序最终执行的就是这个字节码（bytecode）文件。        
编译命令：`java Test.java`       
执行命令：`java Test`（注意，Test后面没有.class）      

Java是跨平台的语言，真正执行的不是二进制代码，而是字节码。

JVM（Java Virtual Machine，Java虚拟机）

Java是跨平台的，而JVM不是跨平台的（JVM是由C语言编写的）

Java之所以能够做到跨平台，本质原因在于JVM不是跨平台的。


