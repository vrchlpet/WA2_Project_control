<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new member</title>
</head>
<body>

	
	
	<h1>Team ${teamName}</h1>
	
	<h3>Available contacts</h3>
	
	<form action="/savemember" method="POST">
		
		<% int i = 0; %>
	
		<c:forEach var="item" items="${contacts}">
			 <input type="checkbox" name="con<%= i++ %>" value="${item}"  /> ${item}<br />
	   	</c:forEach>
	   	<input type="hidden" name="teamName"  value="${teamName}" />
	   	<input type="hidden" name="contactsCount"  value="<%= i %>" />
       	<div><input type="submit" name="submit" value="add members"></div>
  	</form>
	

</body>
</html>