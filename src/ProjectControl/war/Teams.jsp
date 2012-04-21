<%@page import="org.cvut.wa2.projectcontrol.entities.Team"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		Collection<Team> listOfTeams = (Collection<Team>) request.getAttribute("teams");
	%>
	<h1>
		<label>Teams View</label>
	</h1>
	</br>
	<table>
	<%
		for (Team team : listOfTeams) {
	%>
	
	<tr>
		<td>
			<%=team.getName() %>
		</td>
		<td>
			<button action="EditTeam.jsp" value="<%=team.getName()%>" name="teamName">Edit</button>
		</td>
	</tr>
	

	<%
		}
	%>
	<tr>
		<td>
		</td>
		<td>
			<button action="AddNewTeam.jsp">Add</button>
		</td>
	</table>


<a href="/TeamsTask.jsp">Back</a>
<a href="/CreateTeam.jsp">Create Team</a>
</body>
</html>