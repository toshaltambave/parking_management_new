<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Users Role</title>
</head>
<body>
	<center>
		<h1>Select User/s to Change Role</h1>
	</center>
	<form
		action="${pageContext.request.contextPath}/UserDetailsController?role"
		method="GET">
		<script type="text/javascript">
			function chg() {
				var val = document.getElementById('one').value;
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState === 4 && xhttp.status === 200) {
						document.getElementById('two').innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "UserDetailsController?action=" + val, true)
				xhttp.send();
			}
		</script>
		<h1>Search by</h1>
		<select id="one" name=type onchange="chg()">
			<option>Select</option>
			<option value="UserName">UserName</option>
			<option value="LastName">LastName</option>
		</select> <br></br>
		<div>
			<select name=value id="two">
			</select>
		</div><br></br>
		<div>
			<select name=role>
				<option>Select</option>
				<option value="Admin">Admin</option>
				<option value="ParkingUser">ParkingUser</option>
				<option value="ParkingManager">ParkingManager</option>
			</select>
		</div>
		<input name="action" value="role" type="hidden"> <input
			type="submit" value="Submit" />
	</form>
</body>
</html>