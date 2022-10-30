<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Struts2.3 Result</title>
</head>
<body>
	username:${requestScope.username}<br /> 
	password:${requestScope.password }
	
	<%--
	<br/>
	username:<%=request.getParameter("username") %><br /> 
	password:<%=request.getParameter("password") %>
	--%>
</body>
</html>