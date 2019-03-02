<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    $( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
  });
  </script>

<body>
<input name="errorMsg"  value="<c:out value='${userDetailsErrorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="formUserDetails" action="${pageContext.request.contextPath}/UserDetailsController?saveUserDetails" method="post">
    <table style="width: 1200px; ">
    
    <tr>
    <td> First Name (*): </td>
    <td> <input name="firstname" value="<c:out value='${userdetails.firstName}'/>" type="text" maxlength="45">  </td>
 	<td> <input name="firstnameError"  value="<c:out value='${userDetailsErrorMsgs.firstNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Middle Name : </td>
    <td> <input name="middlename" value="<c:out value='${userdetails.middleName}'/>" type="text" maxlength="45">  </td>
  	<td> <input name="middlenameError"  value="<c:out value='${userDetailsErrorMsgs.middleNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last Name (*): </td>
    <td> <input name="lastname" value="<c:out value='${userdetails.lastName}'/>" type="text" maxlength="45">  </td>
  	<td> <input name="lastnameError"  value="<c:out value='${userDetailsErrorMsgs.lastNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Sex: </td>
    <td> 
    	<select name="sex" id="sex">
          <option>Select Sex</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
		</select>	
    </td>
  	<td> <input name="sexError"  value="<c:out value='${userDetailsErrorMsgs.sexError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Date of Birth (*): </td>
    <td> <input name="dob" value="<c:out value='${userdetails.birthDate}'/>" type= "text" class="datepicker" maxlength="16" readonly>  </td>
  	<td> <input name="birthDateError"  value="<c:out value='${userDetailsErrorMsgs.birthDateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
   </tr>
    
    <tr>
    <td> Address (*): </td>
    <td> <input name="address" value="<c:out value='${userdetails.address}'/>" type="text" maxlength="45">  </td>
  	<td> <input name="addressError"  value="<c:out value='${userDetailsErrorMsgs.addressError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value='${userdetails.email}'/>" type="text" maxlength="45">  </td>
  	<td> <input name="emailError"  value="<c:out value='${userDetailsErrorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Phone (*): </td>
    <td> <input name="phone" value="<c:out value='${userdetails.phone}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="phoneError"  value="<c:out value='${userDetailsErrorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Driving License # (*): </td>
    <td> <input name="dlno" value="<c:out value='${userdetails.drivingLicenseNo}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="dlnoError"  value="<c:out value='${userDetailsErrorMsgs.drivingLicenseError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    
    <tr>
    <td> Driving License Expiry Date (*): </td>
    <td> <input name="dlexpirydte" value="<c:out value='${userdetails.drivingLicenseExpiry}'/>" type= "text" class="datepicker" maxlength="16" readonly>  </td>
  	<td> <input name="dlexpirydteError"  value="<c:out value='${userDetailsErrorMsgs.drivingLicenseExpiry}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Registration # (*): </td>
    <td> <input name="regno" value="<c:out value='${userdetails.registrationNumber}'/>" type="text" maxlength="45">  </td>
  	<td> <input name="regnoError"  value="<c:out value='${userDetailsErrorMsgs.regNumberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> UTA ID (*): </td>
    <td> <input name="utaid" value="<c:out value='${userdetails.uta_Id}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="utaidError"  value="<c:out value='${userDetailsErrorMsgs.utaIdError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    

    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="saveUserDetails" type="hidden">
    <input type="submit" value="Submit">
    </form>
</td>
</tr>
</table>
</body>
</html>