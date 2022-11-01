<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Struts2.3 Login3</title>
</head>
<body>
	<s:form action="login3">
		<s:textfield name="username" label="username"></s:textfield>
		<s:textfield name="password" label="password"></s:textfield>
		<s:submit label="submit"></s:submit>
	</s:form>
</body>
</html>