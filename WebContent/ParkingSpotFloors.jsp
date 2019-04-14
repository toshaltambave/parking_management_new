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
			    	<table class="table-bordered center_div">
						<tr>
							<th>Floor Number</th>
							<th>Permit Type</th>
							<th>Number of Spots</th>
						</tr>
						<c:forEach items="${allFloors}" var="ParkingAreaFloors">
							<tr>
							<form action="${pageContext.request.contextPath}/ParkingSpotsController?getSpotsForFloor" method="post">
				
								<td>${ParkingAreaFloors.floor_Number}</td>
								<td>${ParkingAreaFloors.permitType}</td>
								<td>${ParkingAreaFloors.no_Spots}</td>
								<td><input id="btnSelectParkingAreaFloors${ParkingAreaFloors.floor_Number}${ParkingAreaFloors.permitType}" class="btn btn-secondary" type="submit" value="Select" /></td>
							</tr>
							<input id="selectedAreaId" type="hidden" name="selectedAreaId" value="${selectedArea.area_Id}">
							<input id="selectedFloorNumber" type="hidden" name="selectedFloorNumber" value="${ParkingAreaFloors.floor_Number}">
							<input id="selectedPermitType" type="hidden" name="selectedPermitType" value="${ParkingAreaFloors.permitType}">
												<input name="action" value="getSpotsForFloor" type="hidden">
			    </form>
						</c:forEach>
					</table>

			</div>
<!-- 			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button> -->
				
		</div>
    </jsp:body>
</t:_layout>


