<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Documents view</title>
</head>
<body>
<h3>Available documents for project ${taskName}:</h3>
<form action="/savedocument" method="POST">
		
		<% int i = 0; %>
	
		<c:forEach var="item" items="${listOfEntities}">
			 <input type="checkbox" name="con<%= i++ %>" value="${item.docName}"  /> ${item.docName}<br />
	   	</c:forEach>
	   	<input type="hidden" name="teamName"  value="${teamName}" />
	   	<input type="hidden" name="contactsCount"  value="<%= i %>" />
       	<div><input type="submit" name="submit" value="add members"></div>
  	</form>
</body>
</html>