<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>User Search</title>
</head>
<body>
	<center>
		<h1>User Search</h1>
	</center>
	<form action="${pageContext.request.contextPath}/UserDetailsController?search"
		method="GET">
		<input type="text" name="query" /> <select name="type">
			<option value="UserName">UserName</option>
			<option value="LastName">LastName</option>
		</select> <input name="action" value="search" type="hidden"> <input
			type="submit" value="Search" />
	</form>
<br>
<br>
<table>
<tr>
<th>First Name</th>
<th>Last Name</th>
<th>Address</th>
<th>Phone</th>
<th>Address</th>
<th>Phone</th>
<th>Email</th>
<th>Date of Birth</th>
<th>Sex</th>
<th>Driving License No.</th>
<th>Driving License Expiry Date</th>
<th>Registration No.</th>
<th>UTA ID</th>

</tr>
<c:forEach items="${details}" var="UserDetails">	
   <tr>  
    	<td>${UserDetails.firstName}</td>
    	<td>${UserDetails.lastName}</td>
    	<td>${UserDetails.address}</td>
    	<td>${UserDetails.phone}</td>
    	<td>${UserDetails.email}</td>
    	<td>${UserDetails.birthDate}</td>
    	<td>${UserDetails.sex}</td>
    	<td>${UserDetails.drivingLicenseNo}</td>
    	<td>${UserDetails.drivingLicenseExpiry}</td>
    	<td>${UserDetails.registrationNumber}</td>
    	<td>${UserDetails.uta_Id}</td>   
    </tr>
</c:forEach>
</table>
<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>