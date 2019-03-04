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
			    <form action="/" method="post">
			        <h3>Floor: ${selectedFloorNumber} </h3>
			        <h3>Permit Type: ${selectedPermitType} </h3>
			        <label>Select Spot to Reserve </label>
						<table>
							<tr>
								<th>Spot ID</th>
							</tr>
							<c:forEach items="${spotsList}" var="ParkingSpots" begin="0" end="${fn:length(spotsList)}">
								<tr>
									<td><input class="btn btn-secondary" type="submit" value="${ParkingSpots.spot_Id}" /></td>
								</tr>
								<input type="hidden" name="selectedSpotId" value="${ParkingSpots.spot_Id}">
							</c:forEach>
						</table>
						<input name="action" value="" type="hidden">
			    </form>
			</div>
			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button>			
		</div>
    </jsp:body>
</t:_layout>



