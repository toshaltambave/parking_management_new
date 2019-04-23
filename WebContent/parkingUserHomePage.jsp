<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<t:_layout>
	<jsp:attribute name="header">
<title>Parking User HomePage</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
<div class="container center_div">	
<h2>PARKING USER HOMEPAGE</h2>
<br>
<c:if test="${isRevoked eq true}">
<div id="msgIsRevoked" class="alert alert-danger" role="alert"> Your Account has been revoked please contact manager, reason: <c:out value="${User.comment}"/>.</div>
</c:if>
<c:if test="${isMax eq true}">
<div id="msgIsMaxReservation" class="alert alert-danger" role="alert"> Only 3 Reservations allowed in a day.</div>
</c:if>
<div class="row">
<div class="form-group">
<div class="col">
<c:if test="${isReservationSuccessful eq true}">
	<div id="msgisReservation" class="alert alert-success" role="alert"> Reservation has been made successfully.</div>
</c:if>
</div><br>

<div class="col">
	<c:if test="${isSuccessful eq true}">
		<div id="msgUserUpdSuccess" class="alert alert-success" role="alert">
			Users profile has been updated.
		</div>
	</c:if>
</div>
<div class="col"><a id="lnkUpdateProfile" class="btn btn-info" href="${pageContext.request.contextPath}/UpdateUserController?action=getList">Update profile</a> </div> <br>
<div class="col"><a id="lnkViewUserReservation" class="btn btn-info"href="${pageContext.request.contextPath}/ModifyController?action=viewReservations">View My reservation</a> </div> <br> <!-- TODO ADITYA -->
<div class="col"><a id="lnkRequestReservation" class="btn btn-info" href="${pageContext.request.contextPath}/ReservationsController">Request a reservation</a> </div> <br>
<div class="col"><a id="lnkModifyReservation" class="btn btn-info" href="${pageContext.request.contextPath}/ModifyReservationController?action=editReservation">Edit a reservation</a> </div> <br> <!-- TODO TOSHAL -->
<div class="col"><a id="lnkDeleteReservation" class="btn btn-info" href="${pageContext.request.contextPath}/ModifyReservationController">Cancel a reservation</a> </div> <br> <!-- TODO TOSHAL -->
<div class="col"><a id="lnkUserViolations" class="btn btn-info" href="${pageContext.request.contextPath}/ModifyController?action=viewNoShowViolation">View no-shows and violations</a> </div> <br> <!-- TODO ADITYA -->
<div class="col"><a id="lnkViewParkingSpots" class="btn btn-info" href="${pageContext.request.contextPath}/SpotSearchController">View parking spots</a> </div>
</div>
</div>
<form action="${pageContext.request.contextPath}/UsersController?logout"
				method="post">
	<input name="action" value="logout" type="hidden">
    <input id="btnLogout" class="btn btn-secondary" type="submit" value="Logout" />
</form>
</div>
</jsp:body>
</t:_layout>
