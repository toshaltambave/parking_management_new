<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="data.UpdatedUserDetailsDAO" %>
<%@ page import="model.UpdatedUserDetails" %>

<t:_layout>
	<jsp:attribute name="header">

  <link rel="stylesheet"
			href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" /> 
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>     
  <link rel="stylesheet" href="/resources/demos/style.css" />   
<style>
.datepicker {
	
}

input[type="text"] {
	width: 300px
}
</style>
  <script>
			$(function() {
				$(".datepicker").datepicker({
					dateFormat : 'yy-mm-dd'
				});
			});
		</script>
<title>User Details</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container center_div">
<form name="formUserDetails"
				action="${pageContext.request.contextPath}/UpdateUserController?update"
				method="post">
 
<div class="row">
<div class="form-group">
<div class="col">					      	
<input name="errorMsg" id="errorMsg"
								value="<c:out value='${updatedUserDetailsErrorMsgs.errorMsg}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								>
 </div>
 </div>
 </div>
 
   <table class="table table-bordered">
    
    <tr>
    <td> First Name (*): </td>
    <td> <input name="firstname" id="firstname"
							value="<c:out value='${updatedUserDetails.firstName}'/>" type="text"
							maxlength="45">  </td>
 	<td> <input name="firstnameError" id="firstnameError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.firstNameError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Middle Name : </td>
    <td> <input name="middlename" id="middlename"
							value="<c:out value='${updatedUserDetails.middleName}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input name="middlenameError" id="middlenameError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.middleNameError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last Name (*): </td>
    <td> <input name="lastname" id="lastname"
							value="<c:out value='${updatedUserDetails.lastName}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input name="lastnameError" id="lastnameError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.lastNameError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Username (*): </td>
    <td> <input name="username" id="username" value="<c:out value='${updatedUserDetails.userName}'/>" type="text" maxlength="45">  </td>
 	<td> <input name="usernameError" id="usernameError"  value="<c:out value='${updatedUserDetailsErrorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"    maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Sex: </td>
    <td> 
    	<select name="sex" id="sex">
    		<option>${updatedUserDetails.sex}</option>
    		<option value="Male">Male</option>
    		<option value="Female">Female</option>
    		<option value="Other">Other</option>
		</select>	
    </td>

    </tr>
    
    <tr>
    <td> Date of Birth (*): </td>
    <td> <input name="dob" id="dob"
							value="<c:out value='${updatedUserDetails.birthDate}'/>" type="text"
							class="datepicker" maxlength="16" readonly>  </td>
  	<td> <input name="birthDateError" id="birthDateError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.birthDateError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
   </tr>
    
    <tr>
    <td> Address (*): </td>
    <td> <input name="address" id="address"
							value="<c:out value='${updatedUserDetails.address}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input name="addressError" id="addressError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.addressError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Email (*): </td>
    <td> <input name="email" id="email"
							value="<c:out value='${updatedUserDetails.email}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input name="emailError" id="emailError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.emailError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Phone (*): </td>
    <td> <input name="phone" id="phone"
							value="<c:out value='${updatedUserDetails.phone}'/>" type="text"
							maxlength="16">  </td>
  	<td> <input name="phoneError" id="phoneError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.phoneError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Driving License # (*): </td>
    <td> <input name="dlno" id="dlno"
							value="<c:out value='${updatedUserDetails.drivingLicenseNo}'/>"
							type="text" maxlength="16">  </td>
  	<td> <input name="dlnoError" id="dlnoError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.drivingLicenseError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    
    <tr>
    <td> Driving License Expiry Date (*): </td>
    <td> <input name="dlexpirydte" id="dlexpirydte"
							value="<c:out value='${updatedUserDetails.drivingLicenseExpiry}'/>"
							type="text" class="datepicker" maxlength="16" readonly>  </td>
  	<td> <input name="dlexpirydteError" id="dlexpirydteError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.drivingLicenseExpiry}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Registration # (*): </td>
    <td> <input name="regno" id="regno"
							value="<c:out value='${updatedUserDetails.registrationNumber}'/>"
							type="text" maxlength="45">  </td>
  	<td> <input name="regnoError" id="regnoError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.regNumberError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> UTA ID (*): </td>
    <td> <input name="utaid" id="utaid"
							value="<c:out value='${updatedUserDetails.uta_Id}'/>" type="text"
							maxlength="16">  </td>
  	<td> <input name="utaidError" id="utaidError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.utaIdError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Password (*): </td>
    <td> <input name="hashpass" id="hashpass"
							value="<c:out value='${updatedUserDetails.hashedPassword}'/>" type="password"
							maxlength="16">  </td>
  	<td> <input name="hashPassError" id="hashPassError"
							value="<c:out value='${updatedUserDetailsErrorMsgs.hashedPasswordError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60"> </td>
	</tr>
    

    <tr>
    <td> Confirm Password (*): </td>
    <td> <input name="confirmpass" id="confirmpass" value="<c:out value='${updatedUserDetails.confirmPassword}'/>" type="password" maxlength="16">  </td>
  	<td> <input name="confirmPasswordError" id="confirmPasswordError" value="<c:out value='${updatedUserDetailsErrorMsgs.confirmPasswordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"    maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Role (*): </td>
    <td> 
    	<select name="role" id="role">
          <option>${updatedUserDetails.role}</option>
          <option value="ParkingUser">ParkingUser</option>
          <option value="ParkingManager">ParkingManager</option>
          <option value="Admin">Admin</option>
		</select>	
    </td>
  	<td> <input name="roleError" id="roleError" value="<c:out value='${updatedUserDetailsErrorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"    maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Permit Type: </td>
    <td> 
		<select name="permitType" id="permitType">
          <option>${updatedUserDetails.permitType}</option>
          <option value="Basic">Basic</option>
          <option value="Midrange">Midrange</option>
          <option value="Premium">Premium</option>
          <option value="Access">Access</option>
		</select>	
	</td>
  	<td> <input name="permitTypeError" id="permitTypeError"  value="<c:out value='${updatedUserDetailsErrorMsgs.permitTypeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"    maxlength="60"> </td>
    </tr>

    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
	<input name="userId" value='${updatedUserDetails.userID}' type="hidden">
    <input name="action" value="update" type="hidden">
	<input id="btnUpdateProfile" type="submit" value="Update">
    </form>
		</div>
    </jsp:body>
</t:_layout>
