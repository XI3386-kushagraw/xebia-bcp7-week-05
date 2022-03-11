<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
</head>
<body>
	<% String val = request.getParameter("uname121"); %>
	<% String val1 = request.getParameter("pass121"); %>
	Today's date: <%= (new java.util.Date()).toLocaleString()%>
</body>
<h2>Username is : -</h2><%=val%>
<h2>Password is : -</h2><%=val1%>

</html>