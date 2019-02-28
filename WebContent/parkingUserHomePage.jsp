<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>Parking User HomePage</title>
</head>
<body>
<h1>PARKING USER HOMEPAGE</h1>
<div>

<br><a href="#" onclick="return false;">Create profile</a>
<br><a href="#" onclick="return false;">Update profile</a>
<br><a href="#" onclick="return false;">View a reservation</a>
<br><a href="#" onclick="return false;">Request a reservation</a>
<br><a href="#" onclick="return false;">Edit a reservation</a>
<br><a href="#" onclick="return false;">View reservation status</a>
<br><a href="#" onclick="return false;">Cancel a reservation</a>
<br><a href="#" onclick="return false;">View no-shows and violations</a>
<br><a href="${pageContext.request.contextPath}/SpotSearchController">View parking spots</a>

<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input type="submit" value="Logout" />
</form>
</div>
</body>
</html>
