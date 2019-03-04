<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<title>Admin HomePage</title>
</head>
<body>
	<center>
		<h1>ADMIN HOMEPAGE</h1>
	</center>
	<div>

		<br>
		<a href="UpdateSelect.jsp">Edit user profile</a> <br>
		<a href="ChangeUserRole.jsp">Change user role</a> <br>
		<a href="UserSearch.jsp">Search for user</a> <br>
		<a href="RevokeUser.jsp">Revoke user</a> <br>
		<a href="UnrevokeUser.jsp">Unrevoke user</a>

		<form
			action="${pageContext.request.contextPath}/UsersController?logout"
			method="post">
			<input name="action" value="logout" type="hidden"> <input
				type="submit" value="Logout" />
		</form>
	</div>
	<div class="col">
		<c:if test="${isSuccessful eq true}">
			<div class="alert alert-success" role="alert">
				User:<%=request.getParameter("username")%> profile has been updated.
			</div>
		</c:if>
	</div>
</body>
</html>
