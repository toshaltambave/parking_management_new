<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
<title>Revoke Users</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
	<div class="container center_div">
	<div class="row">
	<div class="form-group">
	<div class="col">
		<h2>Select User(s) to Revoke</h2>
	<form
		action="${pageContext.request.contextPath}/UserDetailsController?revoke"
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
		<select name=value  id="two">
		</select>
		</div>
		</div>
		</div>
		<input disabled name="revokeError"
			   value="<c:out value='${revokeError}'/>" type="text"
			   style="background-color: white; color: red; border: none;"
			  maxlength="60" class="form-control">
		<div class="row">
		<div class="form-group">
		<div class="col">
		<label>Comment (*):</label>
		<div class="row"><input id="txtComment" type="text" class="form-control" name="txtComment" value="comment"  maxlength="45" ></div>
		<div class="row"><input disabled id="commentError" class="form-control center_div" name="commentError"  value="<c:out value='${revokeerrorMsgs.commentError}'/>" type="text"  style ="background-color: white; color: red; border: none"></div>    
		<input name="action" value="revoke" type="hidden">
		<div class="row"><input id="btnRevoke" class="btn btn-secondary" type="submit" value="Revoke" /></div>
		</div>
		</div>
		</div>
		<div class="col">
			<c:if test="${isSuccess eq true}">
			<div id="msgRevSuccess" class="alert alert-success" role="alert">User has Been Revoked.</div>
			</c:if>
		</div>	
	</form>
	</div>
	</div>
	</div>
	</div>
	
    </jsp:body>
</t:_layout>