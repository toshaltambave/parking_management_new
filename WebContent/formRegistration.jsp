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
			<input name="errorMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
			<table>
			  <tr>
			   <td>
			    <form name="formRegistration" action="${pageContext.request.contextPath}/UsersController?saveUser" method="post">
			    <table style="width: 1200px; ">
			    
			    <tr>
			    <td> Username (*): </td>
			    <td> <input name="username" value="<c:out value='${user.username}'/>" type="text" maxlength="45">  </td>
			 	<td> <input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
			    </tr>
			    
			    <tr>
			    <td> Password (*): </td>
			    <td> <input name="hashedPassword" value="<c:out value='${user.hashedPassword}'/>" type="password" maxlength="16">  </td>
			  	<td> <input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
			    </tr>
			
			    <tr>
			    <td> Confirm Password (*): </td>
			    <td> <input name="confirmPassword" value="<c:out value='${user.confirmPassword}'/>" type="password" maxlength="16">  </td>
			  	<td> <input name="confirmPasswordError"  value="<c:out value='${errorMsgs.confirmpasswordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
			    </tr>
			    
			    <tr>
			    <td> Role (*): </td>
			    <td> 
			    	<select name="role" id="role">
			          <option>Select User Role</option>
			          <option value="ParkingUser">ParkingUser</option>
			          <option value="ParkingManager">ParkingManager</option>
			          <option value="Admin">Admin</option>
					</select>	
			    </td>
			  	<td> <input name="roleError"  value="<c:out value='${errorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
			    </tr>
			    
			    <tr>
			    <td> Permit Type: </td>
			    <td> 
					<select name="permitType" id="permitType">
			          <option>Select Permit Type</option>
			          <option value="Basic">Basic</option>
			          <option value="Midrange">Midrange</option>
			          <option value="Premium">Premium</option>
			          <option value="Access">Access</option>
					</select>	
				</td>
			  	<td> <input name="permitTypeError"  value="<c:out value='${errorMsgs.permitTypeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
			    </tr>
			
			    <tr>
			    <td colspan="2">(*) Mandatory field</td>
			    </tr>
			    </table>

				</div>
				
			    <input name="action" value="saveUser" type="hidden">
			    <input type="submit" value="Register">
			    </form>
			</td>
			</tr>
			</table>
			
		</div>
    </jsp:body>
</t:_layout>




