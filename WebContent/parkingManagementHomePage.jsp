<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
<jsp:attribute name="header">
<title>Parking Management HomePage</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
<div class="container center_div">	
<h2>PARKING MANAGEMENT HOMEPAGE</h2>
<div class="row">
<div class="form-group">
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Create profile</a> </div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Update profile</a></div>
<br><div class="col"><a class="btn btn-info"href="userSearch.jsp">Search for User</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">View spaces by type and time</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">View parking spot details</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">View parking user details</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Delete a reservation</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Edit a reservation</a></div>
<<<<<<< HEAD
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Make Spot Available</a></div>
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/SetNoShowController">Set no-show</a></div>
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/SetOverdueController">Set Overdue</a></div>
=======
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/ReservationsController">Make Spot Available</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Set no-show</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Set Overdue</a></div>
>>>>>>> ac9543dfa6440030308f7db524e1efa28b404f51
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Add parking area</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Edit parking area</a></div>
</div>
</div>
<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input class="btn btn-secondary" type="submit" value="Logout" />
</form>
</div>
</jsp:body>
</t:_layout>
