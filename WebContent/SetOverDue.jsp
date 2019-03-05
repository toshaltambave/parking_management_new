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
					<div class="col">
						<c:if test="${isOverDue eq true}">
							<div class="alert alert-success" role="alert"> Marked No Show Successfully.</div>
						</c:if>
						<c:if test="${isOverDue eq false}">
							<div class="alert alert-error" role="alert"> Marked No Show Failed.</div>
						</c:if>
												
					</div>	
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
							 <form action="${pageContext.request.contextPath}/SetOverdueController?setOverDue" method="post">
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
								<td><input class="btn btn-secondary" type="submit" value="Select" /></td>
								<input name="action" value="setOverDue" type="hidden">
								<input type="hidden" name="reservationID" value="${AllReservation.reservationID}">
								<input type="hidden" name="selectedUsername" value="${AllReservation.userName}">
							 </form>
							</tr>
						</c:forEach>
					</table>

			</div>
			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button>
				
		</div>
    </jsp:body>
</t:_layout>
