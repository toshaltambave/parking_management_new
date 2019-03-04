<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">

<title>User Search</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
<div class="container center_div">
<div class="row">
<div class="form-group">
<div class="col">
	<h2>User Search</h2>
	<form action="${pageContext.request.contextPath}/UserDetailsController?search"
		method="GET">
		<div class="row">
		<div class="form-group">
		<div class="col">
		<input type="text" name="query" /> 
		</div>
		</div>
		</div>
		<div class="row">
		<div class="form-group">
		<div class="col">
		<select name="type">
			<option value="UserName">UserName</option>
			<option value="LastName">LastName</option>
		</select> 
		</div>
		</div>
		</div>
		<input name="action" value="search" type="hidden"> 
		<div class="row">
		<div class="form-group">
		<div class="col">
		<input class="btn btn-secondary" type="submit" value="Search" />
		</div>
		</div>
		</div>
	</form>
</div>

		</div>
		</div>
		</div>
<table class="table table-bordered center_div">
<tr>
<th>First Name</th>
<th>Middle Name</th>
<th>Last Name</th>
<th>Address</th>
<th>Phone</th>
<th>Email</th>
<th>Date of Birth</th>
<th>Sex</th>
<th>Driving License No.</th>
<th>Driving License Expiry Date</th>
<th>Registration No.</th>
<th>UTA ID</th>

</tr>
<c:forEach items="${details}" var="UserDetails">	
   <tr>  
    	<td>${UserDetails.firstName}</td>
    	<td>${UserDetails.middleName}</td>
    	<td>${UserDetails.lastName}</td>
    	<td>${UserDetails.address}</td>
    	<td>${UserDetails.phone}</td>
    	<td>${UserDetails.email}</td>
    	<td>${UserDetails.birthDate}</td>
    	<td>${UserDetails.sex}</td>
    	<td>${UserDetails.drivingLicenseNo}</td>
    	<td>${UserDetails.drivingLicenseExpiry}</td>
    	<td>${UserDetails.registrationNumber}</td>
    	<td>${UserDetails.uta_Id}</td>   
    </tr>
</c:forEach>
</table>
		<div class="row">
		<div class="form-group">
		<div class="col">
<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button>
    	</div>
		</div>
		</div>
    
    </jsp:body>
</t:_layout>