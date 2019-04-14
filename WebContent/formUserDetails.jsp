<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				action="${pageContext.request.contextPath}/UserDetailsController?saveUserDetails"
				method="post">
 
<div class="row">
<div class="form-group">
<div class="col">					      	
<input disabled id="errorMsg" name="errorMsg"
								value="<c:out value='${userDetailsErrorMsgs.errorMsg}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								  >
 </div>
 </div>
 </div>
 
   <table class="table table-bordered">
    
    <tr>
    <td> First Name (*): </td>
    <td> <input id="firstname" name="firstname"
							value="<c:out value='${userdetails.firstName}'/>" type="text"
							maxlength="45">  </td>
 	<td> <input disabled id="firstnameError" name="firstnameError"
							value="<c:out value='${userDetailsErrorMsgs.firstNameError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Middle Name : </td>
    <td> <input id="middlename" name="middlename"
							value="<c:out value='${userdetails.middleName}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input disabled id="middlenameError" name="middlenameError"
							value="<c:out value='${userDetailsErrorMsgs.middleNameError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last Name (*): </td>
    <td> <input id="lastname" name="lastname"
							value="<c:out value='${userdetails.lastName}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input disabled id="lastnameError" name="lastnameError"
							value="<c:out value='${userDetailsErrorMsgs.lastNameError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Sex: </td>
    <td> 
    	<select name="sex" id="sex">
    	 <c:forEach var="sexValue" items="${allSex}">
			   <option value="${sexValue}"
										<c:if test="${sexValue eq selectedsex}">selected="selected"</c:if>>
			        ${sexValue}
			    </option>
     	</c:forEach>
		</select>	
    </td>
    </tr>
    
    <tr>
    <td> Date of Birth (*): </td>
    <td> <input name="dob" id= "dob"
							value="<c:out value='${userdetails.birthDate}'/>" type="text"
							class="datepicker" maxlength="16">  </td>
  	<td> <input disabled name="birthDateError" id="birthDateError"
							value="<c:out value='${userDetailsErrorMsgs.birthDateError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
   </tr>
    
    <tr>
    <td> Address (*): </td>
    <td> <input name="address" id="address"
							value="<c:out value='${userdetails.address}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input disabled name="addressError" id="addressError"
							value="<c:out value='${userDetailsErrorMsgs.addressError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Email (*): </td>
    <td> <input name="email" id="email"
							value="<c:out value='${userdetails.email}'/>" type="text"
							maxlength="45">  </td>
  	<td> <input disabled name="emailError" id="emailError"
							value="<c:out value='${userDetailsErrorMsgs.emailError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Phone (*): </td>
    <td> <input name="phone" id="phone"
							value="<c:out value='${userdetails.phone}'/>" type="text"
							maxlength="16">  </td>
  	<td> <input disabled name="phoneError" id= "phoneError"
							value="<c:out value='${userDetailsErrorMsgs.phoneError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Driving License # (*): </td>
    <td> <input name="dlno" id="dlno"
							value="<c:out value='${userdetails.drivingLicenseNo}'/>"
							type="text" maxlength="16">  </td>
  	<td> <input disabled name="dlnoError" id="dlnoError"
							value="<c:out value='${userDetailsErrorMsgs.drivingLicenseError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    
    <tr>
    <td> Driving License Expiry Date (*): </td>
    <td> <input name="dlexpirydte" id="dlexpirydte"
							value="<c:out value='${userdetails.drivingLicenseExpiry}'/>"
							type="text" class="datepicker" maxlength="16">  </td>
  	<td> <input disabled name="dlexpirydteError" id="dlexpirydteError"
							value="<c:out value='${userDetailsErrorMsgs.drivingLicenseExpiry}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Registration # (*): </td>
    <td> <input name="regno" id="regno"
							value="<c:out value='${userdetails.registrationNumber}'/>"
							type="text" maxlength="45">  </td>
  	<td> <input disabled name="regnoError" id="regnoError"
							value="<c:out value='${userDetailsErrorMsgs.regNumberError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> UTA ID (*): </td>
    <td> <input name="utaid" id="utaid"
							value="<c:out value='${userdetails.uta_Id}'/>" type="text"
							maxlength="16">  </td>
  	<td> <input disabled name="utaidError" id="utaidError"
							value="<c:out value='${userDetailsErrorMsgs.utaIdError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							   maxlength="60"> </td>
    </tr>
    

    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="saveUserDetails" type="hidden">
    <input id="btnuserdetailssubmit" class="btn btn-secondary" type="submit" value="Submit">

    </form>
		</div>
    </jsp:body>
</t:_layout>