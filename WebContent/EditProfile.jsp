<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="data.UpdatedUserDetailsDAO" %>
<%@ page import="model.UpdatedUserDetails" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//get parameters from the request
String userName = request.getParameter("username");
List<UpdatedUserDetails> userList = UpdatedUserDetailsDAO.searchByUsername(userName);
UpdatedUserDetails updatedUserDetails = userList.get(0);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>User Details</title>
</head>

  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" /> 
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>     
  <link rel="stylesheet" href="/resources/demos/style.css" />   
  <style>
    .datepicker{
    } 
  </style>
  <script>
  $(function() {
    $( ".datepicker" ).datepicker({ 
    	dateFormat: 'yy-mm-dd'
    }).val();
  });
  </script>

<body>
<input name="errorMsg"  value="<c:out value='${updatedUserDetailsErrorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="formUserDetails" action="${pageContext.request.contextPath}/UpdateUserController?update" method="post">
    <table style="width: 1200px; ">
    
    <tr>
    <td> First Name (*): </td>
    <td> <input name="firstName" value="<c:out value="<%=updatedUserDetails.getFirstName()%>"/>" type="text" maxlength="45">  </td>
 	<td> <input name="firstnameError"  value="<c:out value='${updatedUserDetailsErrorMsgs.firstNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Middle Name : </td>
    <td> <input name="middleName" value="<c:out value="<%=updatedUserDetails.getMiddleName()%>"/>" type="text" maxlength="45">  </td>
  	<td> <input name="middlenameError"  value="<c:out value='${updatedUserDetailsErrorMsgs.middleNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last Name (*): </td>
    <td> <input name="lastName" value="<c:out value="<%=updatedUserDetails.getLastName()%>"/>" type="text" maxlength="45">  </td>
  	<td> <input name="lastnameError"  value="<c:out value='${updatedUserDetailsErrorMsgs.lastNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Username (*): </td>
    <td> <input name="username" value="<c:out value="<%=updatedUserDetails.getUserName()%>"/>" type="text" maxlength="45">  </td>
 	<td> <input name="usernameError"  value="<c:out value='${updatedUserDetailsErrorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Sex: </td>
    <td> 
    	<select name="sex" id="sex">
          <option><%=updatedUserDetails.getSex()%></option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
		</select>	
    </td>
  	<td> <input name="sexError"  value="<c:out value='${updatedUserDetailsErrorMsgs.sexError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Date of Birth (*): </td>
    <td> <input name="dob" value="<c:out value="<%=updatedUserDetails.getBirthDate()%>"/>" type= "text" class="datepicker" maxlength="16" readonly>  </td>
  	<td> <input name="birthDateError"  value="<c:out value='${updatedUserDetailsErrorMsgs.birthDateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
   </tr>
    
    <tr>
    <td> Address (*): </td>
    <td> <input name="address" value="<c:out value="<%=updatedUserDetails.getAddress()%>"/>" type="text" maxlength="45">  </td>
  	<td> <input name="addressError"  value="<c:out value='${updatedUserDetailsErrorMsgs.addressError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value="<%=updatedUserDetails.getEmail()%>"/>" type="text" maxlength="45">  </td>
  	<td> <input name="emailError"  value="<c:out value='${updatedUserDetailsErrorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Phone (*): </td>
    <td> <input name="phone" value="<c:out value="<%=updatedUserDetails.getPhone()%>"/>" type="text" maxlength="16">  </td>
  	<td> <input name="phoneError"  value="<c:out value='${updatedUserDetailsErrorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Driving License # (*): </td>
    <td> <input name="dlno" value="<c:out value="<%=updatedUserDetails.getDrivingLicenseNo()%>"/>" type="text" maxlength="16">  </td>
  	<td> <input name="dlnoError"  value="<c:out value='${updatedUserDetailsErrorMsgs.drivingLicenseError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    
    <tr>
    <td> Driving License Expiry Date (*): </td>
    <td> <input name="dlexpirydte" value="<c:out value="<%=updatedUserDetails.getDrivingLicenseExpiry()%>"/>" type= "text" class="datepicker" maxlength="16" readonly>  </td>
  	<td> <input name="dlexpirydteError"  value="<c:out value='${updatedUserDetailsErrorMsgs.drivingLicenseExpiry}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Registration # (*): </td>
    <td> <input name="regno" value="<c:out value="<%=updatedUserDetails.getRegistrationNumber()%>"/>" type="text" maxlength="45">  </td>
  	<td> <input name="regnoError"  value="<c:out value='${updatedUserDetailsErrorMsgs.regNumberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> UTA ID (*): </td>
    <td> <input name="utaid" value="<c:out value="<%=updatedUserDetails.getUta_Id()%>"/>" type="text" maxlength="16">  </td>
  	<td> <input name="utaidError"  value="<c:out value='${updatedUserDetailsErrorMsgs.utaIdError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
        <tr>
    <td> Password (*): </td>
    <td> <input name="hashpass" value="<c:out value="<%=updatedUserDetails.getHashedPassword()%>"/>" type="password" maxlength="16">  </td>
  	<td> <input name="passwordError"  value="<c:out value='${updatedUserDetailsErrorMsgs.hashedPasswordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Confirm Password (*): </td>
    <td> <input name="confirmpass" value="<c:out value="<%=updatedUserDetails.getConfirmPassword()%>"/>" type="password" maxlength="16">  </td>
  	<td> <input name="confirmPasswordError"  value="<c:out value='${updatedUserDetailsErrorMsgs.confirmPasswordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Role (*): </td>
    <td> 
    	<select name="role" id="role">
          <option><%=updatedUserDetails.getRole()%></option>
          <option value="ParkingUser">ParkingUser</option>
          <option value="ParkingManager">ParkingManager</option>
          <option value="Admin">Admin</option>
		</select>	
    </td>
  	<td> <input name="roleError"  value="<c:out value='${updatedUserDetailsErrorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Permit Type: </td>
    <td> 
		<select name="permitType" id="permitType">
          <option><%=updatedUserDetails.getPermitType()%></option>
          <option value="Basic">Basic</option>
          <option value="Midrange">Midrange</option>
          <option value="Premium">Premium</option>
          <option value="Access">Access</option>
		</select>	
	</td>
  	<td> <input name="permitTypeError"  value="<c:out value='${updatedUserDetailsErrorMsgs.permitTypeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    

    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="userId" value="<%=updatedUserDetails.getUserID()%>" type="hidden">
    <input name="action" value="update" type="hidden">
    <input type="submit" value="Update">
    </form>
</td>
</tr>
</table>
</body>
</html>
