<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Struts2.3 convert test</title>
</head>
<body>

	<h3 style="color:red;">使用逗号将点的两个坐标分隔开</h3>
	<s:form name="pointConverter">
		<s:textfield name="point" label="point"></s:textfield>
		<s:textfield name="age" label="age"></s:textfield>
		<s:textfield name="username" label="username"></s:textfield>
		<s:textfield name="date" label="birthday"></s:textfield>
		
		<s:submit value="Submit"></s:submit>
	</s:form>
</body>
</html>