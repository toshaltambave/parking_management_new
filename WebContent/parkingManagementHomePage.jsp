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
<br><div class="col"><a class="btn btn-info"href="UpdateSelect.jsp">Update profile</a></div>
<br><div class="col"><a class="btn btn-info"href="UserSearch.jsp">Search for User</a></div> 
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/SpotSearchController" >View parking spot details</a></div>
<br><div class="col"><a class="btn btn-info"href="UserSearch.jsp">View parking user details</a></div>
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Delete a reservation</a></div> <!-- TODO TOSHAL -->
<br><div class="col"><a class="btn btn-info"href="#" onclick="return false;">Edit a reservation</a></div> <!-- TODO TOSHAL -->
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/SetNoShowController">Set no-show</a></div>
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/SetOverdueController">Set overdue</a></div>
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/ParkingSpotsController">Make spot unavailable</a></div>
<br><div class="col"><a class="btn btn-info"href="${pageContext.request.contextPath}/ParkingAreaController">Add parking area</a></div>
</div>
</div>
<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input class="btn btn-secondary" type="submit" value="Logout" />
</form>
</div>
</jsp:body>
</t:_layout>
