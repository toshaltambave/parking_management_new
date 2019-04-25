<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container">
			<div align="center">
			<table class="table table-bordered center_div">
						<tr>
							<th>Reservation ID</th>
							<th>User Name</th>
							<th>Last Name</th>
							<th>Area Name</th>
							<th>Floor Number</th>
							<th>Spot ID</th>
							<th>Start Time</th>
							<th>End Time</th>
							<th>No Show</th>
							<th>Over Stay</th>
							
						</tr>
						<c:forEach items="${allreservations}" var="AllReservation">
							<tr>
							 <form>
								<td>${AllReservation.reservationID}</td>
								<td>${AllReservation.userName}</td>
								<td>${AllReservation.lastName}</td>
								<td>${AllReservation.areaName}</td>
								<td>${AllReservation.floor_Number}</td>
								<td>${AllReservation.spot_Id}</td>
								<td>${AllReservation.start_Time}</td>
								<td>${AllReservation.end_Time}</td>
								<td>${AllReservation.isNoShow}</td>
								<td>${AllReservation.isOverStay}</td>
							 </form>
							</tr>
						</c:forEach>
					</table>
			
			
    </jsp:body>
</t:_layout>
			