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

<div class="col">
	<c:if test="${isSuccessful eq true}">
		<div id="msgUserUpdSuccess" class="alert alert-success" role="alert">
			Users profile has been updated.
		</div>
	</c:if>
</div>


<br><div class="col"><a id="lnkUpdateProfile" class="btn btn-info"href="UpdateSelect.jsp">Update profile</a></div>
<br><div class="col"><a id="lnkUserSearch" class="btn btn-info"href="UserSearch.jsp">Search for User and view details</a></div> 
<br><div class="col"><a id="lnkViewParkingSpotDetails" class="btn btn-info"href="${pageContext.request.contextPath}/SpotSearchController" >View parking spot details</a></div>
<!-- <br><div class="col"><a id="lnkViewParkingUserDetails" class="btn btn-info"href="UserSearch.jsp">View parking user details</a></div> -->
<br><div class="col"><a id="lnkDeleteReservation" class="btn btn-info"href="${pageContext.request.contextPath}/ModifyReservationController">Delete a reservation</a></div> <!-- TODO TOSHAL -->
<br><div class="col"><a id="lnkModifyReservation" class="btn btn-info"href="${pageContext.request.contextPath}/ModifyReservationController?action=editReservation">Edit a reservation</a></div> <!-- TODO TOSHAL -->
<br><div class="col"><a id="lnkSetNoShow" class="btn btn-info"href="${pageContext.request.contextPath}/ModifyController?action=setNoshow">Set no-show</a></div>
<br><div class="col"><a id="lnkSetOverdue" class="btn btn-info"href="${pageContext.request.contextPath}/ModifyController?action=setOverdue">Set overdue</a></div>
<br><div class="col"><a id="lnkSetSpotUnavailable" class="btn btn-info"href="${pageContext.request.contextPath}/ParkingSpotsController">Make spot unavailable</a></div>
<br><div class="col"><a id="lnkAddParkingArea" class="btn btn-info"href="${pageContext.request.contextPath}/ParkingAreaController">Add parking area</a></div>
<br><div class="col"><a id="lnkEditParkingArea" class="btn btn-info"href="${pageContext.request.contextPath}/ParkingAreaController?action=editParkingArea">Edit existing parking area</a></div>
</div>
</div>
<form action="${pageContext.request.contextPath}/UsersController?logout" method="post">
	<input name="action" value="logout" type="hidden">
    <input id="btnLogout" class="btn btn-secondary" type="submit" value="Logout" />
</form>
</div>
</jsp:body>
</t:_layout>
