
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Team</title>
</head>
<body>
	<h1>
		<label>Team: ${team.name}</label>
	</h1>
	</br>
	<table>
		<c:forEach var="member" items="${team.members }">
			<tr>
				<td>
					${member.name}
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<form action="/addmember" method="POST">
				<input type="hidden" name="teamName" value="${team.name}">
				<button action="submit">Add</button>
			</form>
			</td>
		</tr>
	</table>
	<a href="/teams">Back</a>
</body>
</html>