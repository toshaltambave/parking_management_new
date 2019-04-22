<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
<jsp:attribute name="header">
<title>Edit Existing Parking Area</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
<div class="container center_div">	

    	<div class="container">
			<div align="center">
			    <h2>Select area</h2>
			    <form action="${pageContext.request.contextPath}/ParkingAreaController?getAreaFloors" method="post">
			        Available Areas:&nbsp;
			        <select name="areaDropDrown" id="areaDropDown">
			            <c:forEach items="${Areas}" var="ParkingArea">
			                <option value="${ParkingArea.area_Id}"
			                <c:if test="${ParkingArea.area_Id eq selectedAreaId}">selected="selected"</c:if>>
			                    ${ParkingArea.area_Name}
			                </option>
			            </c:forEach>
			        </select>
			        <input name="action" value="getAreaFloors" type="hidden">
			        <input id="btngetSelectedArea" class="btn btn-secondary" type="submit" value="Select Area" />
			    </form>
			</div>
		</div>
		
		    	<div class="container">
			<div align="center">
			    <form action="${pageContext.request.contextPath}/ParkingAreaController?getFloorSpots" method="post">
					<table class="table table-bordered center_div">
						<tr>
							<th>Floor Number</th>
							<th>Permit Type</th>
							<th>Number of Spots</th>
						</tr>
						<c:forEach items="${allFloors}" var="ParkingAreaFloors">
							<tr>
								<td>${ParkingAreaFloors.floor_Number}</td>
								<td>${ParkingAreaFloors.permitType}</td>
								<td>${ParkingAreaFloors.no_Spots}</td>
								<td><input id="btnSearchSpotFloor${ParkingAreaFloors.floor_Number}${ParkingAreaFloors.permitType}" class="btn btn-secondary" type="submit" value="Select" /></td>
							</tr>
							<input type="hidden" name="selectedAreaId" value="${selectedArea.area_Id}">
							<input type="hidden" name="selectedFloorNumber" value="${ParkingAreaFloors.floor_Number}">
							<input type="hidden" name="selectedPermitType" value="${ParkingAreaFloors.permitType}">
						</c:forEach>
					</table>
					<input name="action" value="getFloorSpots" type="hidden">
			    </form>
			</div>				
		</div>

</div>
</jsp:body>
</t:_layout>