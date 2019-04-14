<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/datepicker.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
		<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<title>User Search</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container center_div">
    	<h1> User Search</h1>
	<form
		action="${pageContext.request.contextPath}/UserDetailsController?search"
		method="GET">
		<script type="text/javascript">
			function chg() {
				var val = document.getElementById('type').value;
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState === 4 && xhttp.status === 200) {
						document.getElementById('two').innerHTML = xhttp.responseText;
					}
				};

				xhttp.open("POST", "UserDetailsController?action=" + val, true)
				xhttp.send();

			}
		</script>
		<h1>Search by</h1>
		<select id="type" name=type onchange="chg();">
			<option value="" disabled selected style="display: none;">Select</option>
			<option value="UserName">UserName</option>
			<option value="LastName">LastName</option>
		</select> <br></br>
		<div>
			<select name=query id="two">
			</select>
		</div>
		<br></br> <input name="action" value="search" type="hidden">
		 <input id="btnUserSearch" type="submit" value="Submit" />
	</form>
	<br>
	<br>
	<div align="center">
		<h2>Users Information</h2>
	</div>
	<div align="center">
		<table class="table table-bordered center_div" border="1" cellpadding="5">
			<tr>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Sex</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Address</th>
				<th>DOB</th>
				<th>DrivingLicenseNo</th>
				<th>DrivingLicenseExpiry</th>
				<th>RegistrationNumber</th>
				<th>uta_Id</th>
				<th>UserName</th>
			</tr>
			<c:forEach var="UserDetails" items="${details}">
				<tr>
					<td><c:out value="${UserDetails.firstName}" /></td>
					<td><c:out value="${UserDetails.middleName}" /></td>
					<td><c:out value="${UserDetails.lastName}" /></td>
					<td><c:out value="${UserDetails.sex}" /></td>
					<td><c:out value="${UserDetails.email}" /></td>
					<td><c:out value="${UserDetails.phone}" /></td>
					<td><c:out value="${UserDetails.address}" /></td>
					<td><c:out value="${UserDetails.birthDate}" /></td>
					<td><c:out value="${UserDetails.drivingLicenseNo}" /></td>
					<td><c:out value="${UserDetails.drivingLicenseExpiry}" /></td>
					<td><c:out value="${UserDetails.registrationNumber}" /></td>
					<td><c:out value="${UserDetails.uta_Id}" /></td>
					<td><c:out value="${UserDetails.username}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
<!-- 	<button type="button" name="back" onclick="history.back()">Back</button> -->
</div>
    </jsp:body>
</t:_layout>