<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
<title>Select User</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
		<h1>Select Users Profile to Update</h1>
	<form
		action="${pageContext.request.contextPath}/UpdateUserController?updateSelect"
		method="POST">
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
		<h1>Search by</h1>
		<select id="one" name=type onchange="chg()">
			<option value="" disabled selected style="display: none;">Select</option>
			<option value="UserName">UserName</option>
		</select> <br></br>
		<div>
			<select name=value id="two">
			</select>
		</div>
		<br></br> <input name="action" value="updateSelect" type="hidden"> 
		<input id="btnUpdateUser" type="submit" value="Submit" />
	</form>
    </jsp:body>
</t:_layout>