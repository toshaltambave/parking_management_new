<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:_layout>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container">
			<div align="center">
			    <h2>${selectedArea.area_Name}</h2>
			    
			        <h3>Floor: ${selectedFloorNumber} </h3>
			        <h3>Permit Type: ${selectedPermitType} </h3>
			        <h3>Time: ${selectedStartTime} To ${selectedEndTime } </h3>
			        <label>Select Spot to Reserve </label>
						<table class="table table-bordered center_div">
							<tr>
								<th>Spot ID</th>
							</tr>
							<c:forEach items="${spotsList}" var="ParkingSpots" begin="0" end="${fn:length(spotsList)}">
								<tr>
									<form action="${pageContext.request.contextPath}/ReservationsController?startReservation" method="post">
										<td><input id="btnReserveSpotID" class="btn btn-secondary" type="submit" value="${ParkingSpots.spot_Id}"></td>
										<input type="hidden" name="selectedAreaId" value="${selectedArea.area_Id}">
										<input type="hidden" name="selectedFloorNumber" value="${selectedFloorNumber}">
										<input type="hidden" name="selectedPermitType" value="${selectedPermitType}">
										<input type="hidden" name="selectedSpotId" value="${ParkingSpots.spot_Id}">
										<input type="hidden" name="selectedSpotUID" value="${ParkingSpots.spot_UID}">
										<input type="hidden" name="selectedStartTime" value="${selectedStartTime}">
										<input type="hidden" name="selectedEndTime" value="${selectedEndTime}">
										<input name="action" value="startReservation" type="hidden">
										
									</form>
								</tr>
							</c:forEach>
						</table>
						
			    
			</div>
<!-- 			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button>			 -->
		</div>
    </jsp:body>
</t:_layout>



