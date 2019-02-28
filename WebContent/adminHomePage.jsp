<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>Admin HomePage</title>
</head>
<body>
<center><h1> ADMIN HOMEPAGE </h1></center>
<div>

<br><a href="#" onclick="return false;">Create profile</a>
<br><a href="#" onclick="return false;">Edit user profile</a>
<br><a href="#" onclick="return false;">Change user role</a>
<br><a href="userSearch.jsp">Search for user</a>
<br><a href="#" onclick="return false;">Revoke user</a>
<br><a href="#" onclick="return false;">Unrevoke user</a>

<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input type="submit" value="Logout" />
</form>
</div>
</body>
</html>
