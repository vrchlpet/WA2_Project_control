<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tasks view</title>
</head>
<body>



	<h1>Tasks</h1>
	
	<h3>Available tasks</h3>
	
	<table>
	
		<c:forEach var="item" items="${listOfTasks}">
			<tr>
				<td>${item.taskName}</td><td>${item.owner.name}</td>
				<td>
						<form action="/edittask" method="POST">
							<input type="hidden" name="taskName" value="${item.taskName} }">
							<button action="submit">Edit</button>
						</form>
				</td>
			</tr>
	   	</c:forEach>
	
	</table>
	
	<form action="/createtask" method="POST">
       	<div><input type="submit" name="submit" value="add task"></div>
  	</form>


</body>
</html>