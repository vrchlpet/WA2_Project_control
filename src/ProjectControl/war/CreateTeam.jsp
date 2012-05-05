<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new team</title>
</head>
<body>
	<form action="/createteam" method="POST">
		<label>Insert team name:</label> <input type="text" name="teamName">
		<input type="submit" name="submit" value="Submit">
	</form>
	<a href="/teams">Back</a>
</body>
</html>