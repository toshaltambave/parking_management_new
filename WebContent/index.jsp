<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container">
			<form name="formRegistration"
				action="${pageContext.request.contextPath}/UsersController?Login"
				method="post">

				<div class="row">
					<div class="form-group">
					<label for="Username">Username</label>
					<div class="col">
						<input class="form-control" placeholder="Enter Username"
								name="username" value="<c:out value='${Users.Username}'/>"
								type="text" maxlength="45">
					</div>
					<div class="col">
						<input name="usernameError" class="form-control"
								value="<c:out value='${errorMsgs.usernameError}'/>" type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60">
					</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
					<label for="Password">Password</label>
					<div class="col">
						<input name="hashedPassword" placeholder="Enter Password"
						value="<c:out value='${Users.HashedPassword}'/>" type="password"
						maxlength="16" class="form-control">
					</div>
					<div class="col">
						<input name="passwordError"
						value="<c:out value='${errorMsgs.passwordError}'/>" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60" class="form-control">
						<span class="error">${errorMsgs.passwordError}</span>
					</div>
				</div>
				</div>
				<div class="row">
					<div class="form-group">
					<div class="col">
						<input name="action" value="Login" type="hidden">
						<input class="btn btn-secondary" type="submit" value="Login">
					</div>
					<div class="col">
						<a href="formRegistration.jsp" target="_top" class="btn btn-info">
							<span>New? Register Here!</span>
						</a>
					</div>
					<div class="col">
						<c:if test="${isSuccess eq true}">
							<div class="alert alert-success" role="alert"> Registered Successfully.</div>
						</c:if>
					</div>	
					</div>
				</div>
			</form>
	</div>
    </jsp:body>
</t:_layout>
