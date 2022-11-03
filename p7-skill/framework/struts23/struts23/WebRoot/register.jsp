<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h2 style="color:blue;">用户注册</h2>

	<s:actionerror cssStyle="color:red;" />

	----------------------------------------------------------------------

	<%--	
	<form action="register.action">
		username: <input type="text" name="username" size="20" /><br/>
		password: <input type="password" name="password" size="20" /><br/>
		repassword: <input type="password" name="repassword" size="20" /><br/>
		age: <input type="text" name="age" size="20" /><br/>
		birthday: <input type="text" name="birthday" size="20"/><br/>
		geaduation: <input type="text" name="graduation" size="20"/><br/>
		
		<input type="submit" value="submit"/>
	</form>
	 --%>

	<s:fielderror cssStyle="color:blue;"></s:fielderror>
	<s:form action="register" theme="simple">
		username:<s:textfield name="username" label="username"></s:textfield><br/>
		password: <s:password name="password" label="password"></s:password><br/>
		repassword: <s:password name="repassword" label="repassword"></s:password><br/>
		age: <s:textfield name="age" label="age"></s:textfield><br/>
		birthday: <s:textfield name="birthday" label="birthday"></s:textfield><br/>
		geaduation: <s:textfield name="graduation" label="graduation"></s:textfield><br/>
		
		<s:submit value="submit"></s:submit>
	</s:form>
	
	<%--
	<s:form action="register">
		<s:textfield name="username" label="username"></s:textfield>
		<s:password name="password" label="password"></s:password>
		<s:password name="repassword" label="repassword"></s:password>
		<s:textfield name="age" label="age"></s:textfield>
		<s:textfield name="birthday" label="birthday"></s:textfield>
		<s:textfield name="graduation" label="graduation"></s:textfield>
		
		<s:submit value="submit"></s:submit>
	</s:form>
	--%>
</body>
</html>