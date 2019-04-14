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
#permitType{
   width:250px
   }
#role{
   width:250px
   }
</style>
<t:_layout>
<jsp:attribute name="header">
<title>Registration</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container center_div">
<form name="formRegistration" action="${pageContext.request.contextPath}/UsersController?saveUser" method="post">

<div class="row">    
<div class="form-group">
<div class="col">	
<div class="row"><input disabled id="errorMsg" class="form-control" name="errorMsg"  value="<c:out value='${registererrorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" ></div>

<div class="row"><label> Username (*):</label></div> 
<div class="row"><input id="username" type="text" class="form-control" name="username" value="<c:out value='${user.username}'/>"  maxlength="45" ></div>
<div class="row"><input disabled id="usernameError" class="form-control center_div" name="usernameError"  value="<c:out value='${registererrorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px" maxlength="60"></div>    

<div class="row"><label> Password (*): </label></div>
<div class="row"><input id="hashedPassword" class="form-control" name="hashedPassword" value="<c:out value='${user.hashedPassword}'/>" type="password" maxlength="16"></div>
<div class="row"><input disabled id="passwordError" class="form-control" name="passwordError"  value="<c:out value='${registererrorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  maxlength="60"></div>

<div class="row"><label> Confirm Password (*): </label></div>
<div class="row"><input id="confirmPassword" class="form-control" name="confirmPassword" value="<c:out value='${user.confirmPassword}'/>" type="password" maxlength="16"></div>
<div class="row"><input disabled id="confirmPasswordError" class="form-control" name="confirmPasswordError"  value="<c:out value='${registererrorMsgs.confirmpasswordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  maxlength="60"></div>
 
<div class="row"><label> Role (*): </label></div>
    	<div class="row"><select class="form-control style-select" name="role" id="role">
    	 <c:forEach var="roleValue" items="${allRoles}">
			   <option value="${roleValue}"
			           <c:if test="${roleValue eq selectedrole}">selected="selected"</c:if>
			     >
			        ${roleValue}
			    </option>
     	</c:forEach>
		</select></div>	
<div class="row"><input class="form-control" id="roleError" name="roleError"  value="<c:out value='${registererrorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"></div>
	
<div class="row"><label> Permit Type: </label></div>
		<div class="row"><select class="form-control style-select" name="permitType" id="permitType">
    	 <c:forEach var="permitTypeValue" items="${allPermitTypes}">
			   <option value="${permitTypeValue}"
			           <c:if test="${permitTypeValue eq selectedpermitType}">selected="selected"</c:if>
			     >
			        ${permitTypeValue}
			    </option>
     	</c:forEach>
		</select></div>	
<div class="row"><input class="form-control" id="permitTypeError" name="permitTypeError"  value="<c:out value='${registererrorMsgs.permitTypeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </div>

<div class="row"><label> Mandatory fields (*)</label></div>
    <input name="action" value="saveUser" type="hidden">
<div class="row"><input id="btnRegister" name="btnRegister" class="btn btn-secondary" type="submit" value="Register"></div>
</div>
</div>
</div>
    </form>
		</div>
    </jsp:body>
</t:_layout>
