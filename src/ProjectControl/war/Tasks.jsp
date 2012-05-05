<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
<<<<<<< HEAD
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import org.cvut.wa2.projectcontrol.entities.Status %>
=======
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
>>>>>>> 81499935f9062002610953f302192a53b6702329
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
				<th>Task owner</th>
			</tr>
			<tr>
				<td>${item.taskName}</td>
				<td>${item.owner}</td>
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
					<form action="/addsubtask" method="POST">
						<input type="hidden" name="taskName" value="${item.taskName}">
						<button action="submit">add subtask</button>
					</form>
				</td>
			</tr>

			<tr>
				<td></td>
				<td colspan="3">
					<table>
						<c:forEach var="subtask" items="${item.subtasks}">

							<tr>
								<td>${subtask.taskName }</td>
								<td>${subtask.taskStatus }</td>
								<td>${subtask.responsible }</td>
								<td>
									<c:if test="${ subtask.taskStatus == Status.finished }">
										<lable>FINISHED</lable>
									</c:if>
									<c:if test="${ subtask.taskStatus == Status.processing }">
										<form action="/finishtask" method="POST">
											<input type="hidden" name="taskName" value="${subtask.taskName}">
											<button action="submit">finish</button>
										</form>
									</c:if>
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
			<option value="statusProcessing">Processing</option>
			<option value="statusFinished">Finished</option>
		</select>
		<input type="submit" name="submit" value="Filter!">
	</form>
	<a href="/TeamsTask.jsp">Back</a>

</body>
</html>