
<%@page import="java.util.ArrayList"%>
<%@page import="org.cvut.wa2.projectcontrol.entities.Team"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
</head>
<body>
	<%
		String err = (String)request.getAttribute("err");
	%>
	<h3>
		<label>Error: <%= err %></label>
	</h3>
	<br>
	
	<a href="/controlproject" >back</a>
</body>
</html>