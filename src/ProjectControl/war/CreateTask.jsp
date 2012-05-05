<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Task</title>

<script type="text/javascript">
	function validateForm() {
		var day = document.getElementById("day").value;
		var month = document.getElementById("month").value;
		var year = document.getElementById("year").value;
		
		switch month {
			case 1:
				return true; 
				break;
			case 2:
				if (year%4 == 0 && year%100 == 0) {
					if (day > 29) return false;
				} else {
					if (day > 28) return false;
				}
				
				return true;
				break;
			case 3:
				return true;
				break;
			case 4:
				if (day > 30) return false;
				break;
			case 5:
				return true;
				break;
			case 6:
				if (day > 30) return false;
				break;
			case 7:
				return true;
				break;
			case 8:
				return true;
				break;
			case 9:
				if (day > 30) return false;
				break;
			case 10:
				return true;
				break;
			case 11:
				if (day > 30) return false;
				break;
			case 12:
				return true;
				break;
		}
		
	}

</script>

</head>
<body>
	<form action="/addtask" method="POST" onsubmit="return validateForm()">
		<label>Owner: ${owner}</label><br>
		<label>Insert task name:</label><input type="text" name="taskName"></br>
		
		<script type="text/javascript">
			document.write("day: <select name=\"day\">")
			for (i=1; i <= 31; i++) {
				document.write("<option value=\"" + i +"\">" + i + "</option>");
			}
			document.write("</select>");
			document.write("month: <select name=\"month\">")
			for (i=1; i <= 12; i++) {
				document.write("<option value=\"" + i +"\">" + i + "</option>");
			}
			document.write("</select>");
			document.write("year: <select name=\"year\">")
			for (i=2012; i <= 2100; i++) {
				document.write("<option value=\"" + i +"\">" + i + "</option>");
			}
			document.write("</select>");
		</script>
		
		<select name="teamsdropdown">
			<c:forEach var="item" items="${listOfTeams}">
				<option value="${item.name}">${item.name}</option>
			</c:forEach>	
		</select></br>
		<input type="submit" name="submit" value="Submit">
	</form>
</body>
</html>