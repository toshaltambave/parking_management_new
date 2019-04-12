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
			        <label>Select spot to make spot available/unavailable</label>
						<table class="table table-bordered center_div">
							<tr>
								<th>Spot ID</th>
								<th>Blocked?</th>
							</tr>
							<c:forEach items="${spotsList}" var="ParkingSpots" begin="0" end="${fn:length(spotsList)}">
								<tr>
								<form action="${pageContext.request.contextPath}/ParkingSpotsController?toggleBlock" method="post">
									<td>${ParkingSpots.spot_Id}</td>
									<td>${ParkingSpots.isBlocked}</td>
									<td><input id="btnBlockUnblock" class="btn btn-secondary" type="submit" value="Block/Unblock" /></td>
								</tr>
								<input type="hidden" name="selectedSpotUId" value="${ParkingSpots.spot_UID}">
								<input type="hidden" name="isBlocked" value="${ParkingSpots.isBlocked}">
														<input name="action" value="toggleBlock" type="hidden">
																	    </form>
							</c:forEach>
						</table>

						<div class="col">
						<c:if test="${isblocksuccess eq true}">
							<div id="msgblocksuccess" class="alert alert-success" role="alert"> spot (un)blocked successfully.</div>
						</c:if>
					</div>	

			</div>
<!-- 			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button>			 -->
		</div>
    </jsp:body>
</t:_layout>



