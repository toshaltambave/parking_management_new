<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
<jsp:attribute name="header">
<title>Admin HomePage</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
<div class="container center_div">	
<h2> ADMIN HOMEPAGE </h2>
<div class="row">
<div class="form-group">
<br><div class="col"><a class="btn btn-info" href="#" onclick="return false;">Edit user profile</a></div>
<br><div class="col"><a class="btn btn-info" href="ChangeUserRole.jsp">Change user role</a></div>
<br><div class="col"><a class="btn btn-info" href="UserSearch.jsp">Search for user</a></div>
<br><div class="col"><a class="btn btn-info" href="RevokeUser.jsp">Revoke user</a></div>
<br><div class="col"><a class="btn btn-info" href="UnrevokeUser.jsp">Unrevoke user</a></div>
</div>
</div>
<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input class="btn btn-secondary" type="submit" value="Logout" />
</form>
</div>
</jsp:body>
</t:_layout>
