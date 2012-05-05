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
	
	<c:forEach var="item" items="${listOfTasks}">
	<table border="2">
	
			<tr>
				<th>Task name</th><th>Task owner</th>
			</tr>
			<tr>
				<td>${item.taskName}</td><td>${item.owner}</td>
				<td>
						<form action="/adddoc" method="POST">
							<input type="hidden" name="taskName" value="${item.taskName}">
							<button action="submit">add doc</button>
						</form>
				</td>
				<td>
					<form action="/removetask" method="POST">
						<input type="hidden" name="taskName" value="${item.taskName}">
						<button action="submit">remove</button>
					</form>
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td colspan="3">
					<table>
					<c:forEach var="subtask" items="${item.subtasks}">
						
							<tr>
								<td>${subtask.taskName }</td><td>${subtask.taskStatus }</td><td>${subtask.responsible }</td>
								<td>
									<form action="/finishtask" method="POST">
										<input type="hidden" name="taskName" value="${subtask.taskName}">
										<button action="submit">finish</button>
									</form>
								</td>
							</tr>
						
					
					</c:forEach>
					</table>
				<td>
			<tr>
	   	
	
	</table>
	</c:forEach>
	
	<form action="/createtask" method="POST">
       	<div><input type="submit" name="submit" value="add task"></div>
  	</form>


</body>
</html>