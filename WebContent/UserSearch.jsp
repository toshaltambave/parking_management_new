<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<title>User Search</title>
</head>
<body>
	<center>
		<h1>User Search</h1>
	</center>
	<form
		action="${pageContext.request.contextPath}/UserDetailsController?search"
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
			<select name=query id="two">
			</select>
		</div>
		<br></br> <input name="action" value="search" type="hidden">
		<input type="submit" value="Submit" />
	</form>
	<br>
	<br>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption><h2>Users Information</h2></caption>
			<tr>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Sex</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Address</th>
				<th>DOB</th>
				<th>DrivingLicenseNo</th>
				<th>DrivingLicenseExpiry</th>
				<th>RegistrationNumber</th>
				<th>uta_Id</th>
				<th>username</th>
			</tr>
			<c:forEach var="UserDetails" items="${details}">
				<tr>
					<td><c:out value="${UserDetails.firstName}" /></td>
					<td><c:out value="${UserDetails.middleName}" /></td>
					<td><c:out value="${UserDetails.lastName}" /></td>
					<td><c:out value="${UserDetails.sex}" /></td>
					<td><c:out value="${UserDetails.email}" /></td>
					<td><c:out value="${UserDetails.phone}" /></td>
					<td><c:out value="${UserDetails.address}" /></td>
					<td><c:out value="${UserDetails.birthDate}" /></td>
					<td><c:out value="${UserDetails.drivingLicenseNo}" /></td>
					<td><c:out value="${UserDetails.drivingLicenseExpiry}" /></td>
					<td><c:out value="${UserDetails.registrationNumber}" /></td>
					<td><c:out value="${UserDetails.uta_Id}" /></td>
					<td><c:out value="${UserDetails.username}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>