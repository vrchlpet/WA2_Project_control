<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create subtask</title>
</head>
<body>

	
	
	<h1>Parrent task ${taskName}</h1>
	
	<form action="/savetsubtask" method="POST">
		<label>subtask name: </label><input type="text" name="subtaskname"><br>
		<label>responsibility: </label>
		
		<select name="responsible">
			<c:forEach var="item" items="${emails}">
				<option value="${item}">${item}</option>
			</c:forEach>	
		</select></br>	
	
		<input type="hidden" name="taskName" value="${taskName}">
		<div><input type="submit" name="submit" value="save subtask"></div>
	</form>

</body>
</html>