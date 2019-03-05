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
<div class="row">
<div class="form-group">
					<div class="col">
						<c:if test="${isReservationSuccessful eq true}">
							<div class="alert alert-success" role="alert"> Reservation has been made successfully.</div>
						</c:if>
					</div><br>
<div class="col"><a class="btn btn-info" href="${pageContext.request.contextPath}/UpdateUserController?action=getList">Update profile</a> </div> <br>
<div class="col"><a class="btn btn-info" href="#" onclick="return false;">View a reservation</a> </div> <br> <!-- TODO ADITYA -->
<div class="col"><a class="btn btn-info" href="${pageContext.request.contextPath}/ReservationsController">Request a reservation</a> </div> <br>
<div class="col"><a class="btn btn-info" href="#" onclick="return false;">Edit a reservation</a> </div> <br> <!-- TODO TOSHAL -->
<!-- <div class="col"><a class="btn btn-info" href="#" onclick="return false;">View reservation status</a> </div> <br> -->
<div class="col"><a class="btn btn-info" href="#" onclick="return false;">Cancel a reservation</a> </div> <br> <!-- TODO TOSHAL -->
<div class="col"><a class="btn btn-info" href="#" onclick="return false;">View no-shows and violations</a> </div> <br> <!-- TODO ADITYA -->
<div class="col"><a class="btn btn-info" href="${pageContext.request.contextPath}/SpotSearchController">View parking spots</a> </div>
</div>
</div>
<form action="${pageContext.request.contextPath}/UsersController?logout"
				method="post">
	<input name="action" value="logout" type="hidden">
    <input class="btn btn-secondary" type="submit" value="Logout" />
</form>
</div>
</jsp:body>
</t:_layout>
