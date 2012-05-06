<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.cvut.wa2.projectcontrol.entities.Status" %>
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
				<th>Task name</th>
				<th colspan="3">${item.taskName}</th>
			</tr>
			
			<tr>
				<td>Team</td>
				<td colspan="2">${item.owner}</td>
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
				<td>
					<form action="/createsubtask" method="POST">
						<input type="hidden" name="taskName" value="${item.taskName}">
						<button action="submit">add subtask</button>
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<c:forEach var="doc" items="${item.docEntity}">
						<p><a href="${doc.href}">${doc.docName}</a></p>
					</c:forEach>
				</td>
			</tr>

			<tr>
				<td colspan="5">
					<table border="1">
						<tr>
							<th>task name</th><th>status</th><th>responsible</th>
						</tr>
						<c:forEach var="subtask" items="${item.subtasks}">

							<tr>
								<td>${subtask.taskName }</td>
								<td>${subtask.taskStatus }</td>	
								<td>${subtask.responsible }</td>
								<td>
										<form action="/finishtask" method="POST">
											<input type="hidden" name="subtaskName" value="${subtask.taskName}">
											<input type="hidden" name="taskName" value="${item.taskName}">
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
		<div>
			<input type="submit" name="submit" value="add task">
		</div>
	</form>
	<h4>Filters:</h4>
	<form action="/filterbystatus" method="POST">
		<select name="statusdropdown">
			<option value="Processing">Processing</option>
			<option value="Finished">Finished</option>
		</select>
		<input type="submit" name="submit" value="Filter!">
	</form>
	<a href="/TeamsTask.jsp">Back</a>

</body>
</html>