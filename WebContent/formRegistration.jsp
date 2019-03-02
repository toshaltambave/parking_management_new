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
<t:_layout>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container center_div">
<form name="formRegistration" action="${pageContext.request.contextPath}/UsersController?saveUser" method="post">

<div class="row">    
<div class="form-group">
<div class="col">	
<div class="row"><input class="form-control" name="errorMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"></div>

<div class="row"><label> Username (*):</label></div> 
<div class="row"><input type="text" class="form-control" name="username" value="<c:out value='${user.username}'/>"  maxlength="45" ></div>
<div class="row"><input class="form-control center_div" name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"></div>    

<div class="row"><label> Password (*): </label></div>
<div class="row"><input class="form-control" name="hashedPassword" value="<c:out value='${user.hashedPassword}'/>" type="password" maxlength="16"></div>
<div class="row"><input class="form-control" name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"></div>

<div class="row"><label> Confirm Password (*): </label></div>
<div class="row"><input class="form-control" name="confirmPassword" value="<c:out value='${user.confirmPassword}'/>" type="password" maxlength="16"></div>
<div class="row"><input class="form-control" name="confirmPasswordError"  value="<c:out value='${errorMsgs.confirmpasswordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"></div>
 
<div class="row"><label> Role (*): </label></div>
    	<div class="row"><select class="form-control style-select" name="role" id="role">
          <option>Select User Role</option>
          <option value="ParkingUser">Parking User</option>
          <option value="ParkingManager">Parking Manager</option>
          <option value="Admin">Administrator</option>
		</select></div>	
<div class="row"><input class="form-control" name="roleError"  value="<c:out value='${errorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"></div>
	
<div class="row"><label> Permit Type: </label></div>
		<div class="row"><select class="form-control style-select" name="permitType" id="permitType">
          <option>Select Permit Type</option>
          <option value="Basic">Basic</option>
          <option value="Midrange">MidRange</option>
          <option value="Premium">Premium</option>
          <option value="Access">Access</option>
		</select></div>	
<div class="row"><input class="form-control" name="permitTypeError"  value="<c:out value='${errorMsgs.permitTypeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </div>

<div class="row"><label> Mandatory fields (*)</label></div>
    <input name="action" value="saveUser" type="hidden">
<div class="row"><input class="btn btn-secondary" type="submit" value="Register"></div>
</div>
</div>
</div>
    </form>
		</div>
    </jsp:body>
</t:_layout>
