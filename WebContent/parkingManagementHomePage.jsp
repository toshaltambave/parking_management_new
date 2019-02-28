<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>Parking Management HomePage</title>
</head>
<body>
<h1>PARKING MANAGEMENT HOMEPAGE</h1>
<div>

<br><a href="#" onclick="return false;">Create profile</a>
<br><a href="#" onclick="return false;">Update profile</a>
<br><a href="userSearch.jsp">Search for User</a>
<br><a href="#" onclick="return false;">View spaces by type and time</a>
<br><a href="#" onclick="return false;">View parking spot details</a>
<br><a href="#" onclick="return false;">View parking user details</a>
<br><a href="#" onclick="return false;">Delete a reservation</a>
<br><a href="#" onclick="return false;">Edit a reservation</a>
<br><a href="#" onclick="return false;">Make Spot Available</a>
<br><a href="#" onclick="return false;">Set no-show</a>
<br><a href="#" onclick="return false;">Set Overdue</a>
<br><a href="#" onclick="return false;">Add parking area</a>
<br><a href="#" onclick="return false;">Edit parking area</a>

<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input type="submit" value="Logout" />
</form>
</div>
</body>
</html>
