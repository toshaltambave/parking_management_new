<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    input[type="text"] {
width:300px
}

input[type="password"]{
width:300px
}
</style>
<t:_layout>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>

	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container center_div" >
			<form name="formRegistration"
				action="${pageContext.request.contextPath}/UsersController?Login"
				method="post">
				
				<div class="row"><input class="form-control" name="errorMsg" id="errorMsg"  value="<c:out value='${loginerrorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled></div>				
				<div class="row">
					<div class="col">
					<label for="Username">Username</label>
					<input class="form-control" placeholder="Enter Username"
							name="username" id="username" value="<c:out value='${Users.Username}'/>"
							type="text" maxlength="45">
					<input name="usernameError" id="usernameError" class="form-control"
							value="<c:out value='${loginerrorMsgs.usernameError}'/>" type="text"
							style="background-color: white; color: red; border: none;"
							 maxlength="60" disabled>
					</div>
					<div class="col">
					<label for="Password">Password</label>
					<input name="hashedPassword" id="password" placeholder="Enter Password"
						value="<c:out value='${Users.HashedPassword}'/>" type="password"
						maxlength="16" class="form-control">
<!-- 					<input name="passwordError" id="passwordError" -->
<%-- 						value="<c:out value='${loginerrorMsgs.passwordError}'/>" type="text" --%>
<!-- 						style="background-color: white; color: red; border: none;" -->
<!-- 						 maxlength="60" class="form-control" disabled> -->
					</div>
				</div>
				<div class="row">
					<div class="form-group">
					<div class="col">
						<input name="action" value="Login" type="hidden">
						<input id="btnLogin" class="btn btn-secondary" type="submit" value="Login">
						<a href="${pageContext.request.contextPath}/UsersController" target="_top" class="btn btn-info">
							<span id="registeruser">New? Register Here!</span>
						</a>
					</div>
					<div class="col">
						<c:if test="${isSuccessful eq true}">
							<div id="msgRegSuccess" class="alert alert-success" role="alert"> Registered Successfully.</div>
						</c:if>
					</div>	
					</div>
				</div>
			</form>
	</div>
    </jsp:body>
</t:_layout>
