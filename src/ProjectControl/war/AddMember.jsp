<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new member</title>
</head>
<body>
	<form action="/addmeber" method="POST">
		<label>Insert member account name:</label> 
		<input type="text" name="memberAcc">
		<input type="submit" name="submit" value="Submit">
	</form>
</body>
</html>