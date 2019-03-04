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
			    <h2>${selectedArea.area_Name}</h2>
			    <h3>From: ${startTime} To:  ${endTime}</h3>
			    
					<table>
						<tr>
							<th>Floor Number</th>
							<th>Permit Type</th>
							<th>Available Spots</th>
						</tr>
						<c:forEach items="${allFloors}" var="ParkingAreaFloors">
						<form action="${pageContext.request.contextPath}/ReservationsController?getSpotsForFloor" method="post">
							<tr>
								<td>${ParkingAreaFloors.floor_Number}</td>
								<td>${ParkingAreaFloors.permitType}</td>
								<td>${ParkingAreaFloors.no_Spots}</td>
								<td><input type="submit" value="Select" /></td>
							</tr>
							<input type="hidden" name="selectedAreaId" value="${selectedArea.area_Id}">
							<input type="hidden" name="selectedFloorNumber" value="${ParkingAreaFloors.floor_Number}">
							<input type="hidden" name="selectedPermitType" value="${ParkingAreaFloors.permitType}">
							<input type="hidden" name="selectedStartTime" value="${startTime}">
							<input type="hidden" name="selectedEndTime" value="${endTime}">
							<input name="action" value="getSpotsForFloor" type="hidden">
						</form>
						</c:forEach>
					</table>
			</div>
			<button type="button" name="back" onclick="history.back()">Back</button>
				
		</div>
    </jsp:body>
</t:_layout>


