<%@page import="org.cvut.wa2.projectcontrol.entities.TeamMember"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.cvut.wa2.projectcontrol.entities.Team"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Team</title>
</head>
<body>
	<%
		Team team = (Team) request.getAttribute("team");
		String teamName = team.getName();
	%>
	<h1>
		<label>Team: <%=teamName%></label>
	</h1>
	</br>
	<table>
	<%
		ArrayList<TeamMember> listOfMemeber = team.getMembers();
		for (TeamMember member : listOfMemeber) {
	%>
	
	<tr>
		<td>
			<%=member.getName() %>
		</td>
		<td>
			<button action="EditMember.jsp" value="<%=member%>" name="member">Edit</button>
		</td>
	</tr>
	

	<%
		}
	%>
	<tr>
		<td>
		</td>
		<td>
			<button action="AddNewMebeber.jsp" value"<%=team.getName()%> name="teamName">Add</button>
		</td>
	</table>
</body>
</html>