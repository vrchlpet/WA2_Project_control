<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Task</title>
</head>
<body>
	<form action="/addtask" method="POST">
		<label>Owner: ${owner}</label>
		<label>Insert task name:</label><input type="text" name="taskName"></br>
		<label>Due date (DD.MM.YYYY):</label><input type="text" name="dueDate"></br>
		<select name="temasdropdown">
			<c:forEach var="item" items="${listOfTeams}">
				<option value="${item.name}">${item.name}</option>
			</c:forEach>	
		</select>
		<input type="submit" name="submit" value="Submit">
	</form>
</body>
</html>