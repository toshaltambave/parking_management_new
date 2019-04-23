<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
<title>Change Users Role</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
	<div class="container center_div">
	<div class="row">
	<div class="form-group">
	<div class="col">
		<h2>Select User(s) to Change Role</h2>
	<form
		action="${pageContext.request.contextPath}/UserDetailsController?role"
		method="GET">
		<script type="text/javascript">
			function chg() {
				var val = document.getElementById('one').value;
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
		<h2>Search by</h2>
		<div class ="form-group">
		<div class="row">
		<div class="col">
		<label> Select the user by the following:</label>
		<select id="one" name=type onchange="chg()">
			<option value="" disabled selected style="display: none;">Select Search Users by</option>
			<option value="UserName">UserName</option>
		</select> 
		</div>
		</div>
		<div class="row">
		<div class="col">
		<label> Users:</label>
			<select name=value id="two">
			</select>
		</div>
		</div>
		<div class="row">
		<div class="col">
		<label> Set to the following roles</label>
			<select name="role" id="role">
				<option>Select Role</option>
				<option value="Admin">Admin</option>
				<option value="ParkingUser">ParkingUser</option>
				<option value="ParkingManager">ParkingManager</option>
			</select>
		</div>
		</div>
		</div>
		<input name="roleupdError" id="roleupdError"
			  value="<c:out value='${roleupdError}'/>" type="text"
			 style="background-color: white; color: red; border: none;"
			  maxlength="60" class="form-control">
		<div class="row">
		<div class="form-group">
		<div class="col">
		<input name="action" value="role" type="hidden"> 
		<input id="btnupdateuserRole" class="btn btn-secondary" type="submit" value="Submit" />
		</div>
		</div>
		</div>
		<div class="col">
			<c:if test="${isSuccess eq true}">
			<div id="msgupdroleSuccess" class="alert alert-success" role="alert">Role has changed for User.</div>
			</c:if>
		</div>	
	</form>
	</div>
	</div>
	</div>
	</div>	
    </jsp:body>
</t:_layout>
