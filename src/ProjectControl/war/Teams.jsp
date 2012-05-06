<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teams view</title>
</head>
<body>
	<h1>
		<label>Teams View</label>
	</h1>
	</br>
	<table>
	
	<c:forEach var="item" items="${teams}">
			 <tr>
				<td>
					${item.name}
				</td>
				<td>
					<form action="/editteam" method="POST">
						<input type="hidden" name="teamName" value="${item.name}">
						<button action="submit">Edit</button>
					</form>
				</td>
			</tr>
	</c:forEach>

	<tr>
		<td>
		</td>
		<td>
			<form action="/CreateTeam.jsp" method="POST">
				<button action="submit">Add</button>
			</form>
		</td>
	</table>


<a href="/TeamsTask.jsp">Back</a>
</body>
</html>