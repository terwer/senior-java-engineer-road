Java SE流程控制

1.    条件运算符（三元表达式），其形式为：
      type d = a ? b : c;  具体化形式为： int d = 2 < 1 ? 3 : 4;
2.    轻量级的文本编辑器：UltraEdit 、Editplus 、vi 、vim 、gvim
3.    流程控制语句 if 的用法为：

第一种形式：

if(布尔表达式)
{
//待执行的代码
}

第二种形式：

if(布尔表达式)
{
//待执行的代码
}
else
{
//待执行的代码
}

第三种形式：

if(布尔表达式)
{
//待执行的代码
}
else if(布尔表达式)
{
//待执行的代码
}
else if(布尔表达式)
{
//待执行的代码
}
else
{
//待执行的代码
}
4.    switch 语句，使用形式为：

switch(变量) //此处的变量类型就目前所学内容来看，只能为4 种类型： byte, short, int, char。
{
case  常量 1:
//待执行的代码
break;
case  常量 2:
//待执行的代码
break;
case  常量 3:
//待执行的代码
break;
default：
//待执行的代码
}

虽然 case 语句中的break 是可选的， 但在绝大多数情况下， 如果没有break，程序的逻 辑就会发生错误，因此， 通常情况下都需要加上 break。
