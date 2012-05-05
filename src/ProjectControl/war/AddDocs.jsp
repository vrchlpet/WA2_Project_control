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
			
			 <input type="checkbox" name="con<%= i %>" value="${item.docName}"  /> <a href="${item.href}">${item.docName}</a><br />
			<input type="hidden" name="href<%=i++%>" value="${item.href}"/>	   	
	   	</c:forEach>
	   	<input type="hidden" name="taskName"  value="${taskName}" />
	   	<input type="hidden" name="documentCount"  value="<%= i %>" />
       	<div><input type="submit" name="submit" value="add documents"></div>
  	</form>
</body>
</html>