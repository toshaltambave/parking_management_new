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

<div class="col">
	<c:if test="${isSuccessful eq true}">
		<div id="msgUserUpdSuccess" class="alert alert-success" role="alert">
			Users profile has been updated.
		</div>
	</c:if>
</div>

<br><div class="col"><a id="lnkEditUserProfile" class="btn btn-info"href="UpdateSelect.jsp">Edit user profile</a></div>
<br><div class="col"><a id="lnkChangeUserRole" class="btn btn-info"href="ChangeUserRole.jsp">Change user role</a></div>
<br><div class="col"><a id="lnkUserSearch" class="btn btn-info"href="UserSearch.jsp">Search for user</a></div>
<br><div class="col"><a id="lnkRevokeUser" class="btn btn-info"href="RevokeUser.jsp">Revoke user</a></div>
<br><div class="col"><a id="lnkUnRevokeUser" class="btn btn-info"href="UnrevokeUser.jsp">Unrevoke user</a></div>
</div>
</div>

<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input id="btnLogout" class="btn btn-secondary" name="logout" type="submit" value="Logout" />
</form>
</div>
</jsp:body>
</t:_layout>