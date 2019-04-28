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
					<div class="col">
						<c:if test="${isNoShow eq true}">
							<div id="msgNoShow" class="alert alert-success" role="alert"> Marked No Show Successfully.</div>
						</c:if>
						<c:if test="${isNoShow eq false}">
							<div id="msgNoShowFailed" class="alert alert-error" role="alert"> Marked No Show Failed.</div>
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
						<c:set var="count" value="2" scope="page" />
						<c:forEach items="${allreservations}" var="AllReservation">
							<tr>
							 <form action="${pageContext.request.contextPath}/ModifyController?setNoshow" method="post">
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
								<td><input id="btnNoShow${AllReservation.reservationID}" class="btn btn-secondary" type="submit" value="Select" /></td>
								<input name="action" value="setNoshow" type="hidden">
								<input type="hidden" name="reservationID" value="${AllReservation.reservationID}">
								<input type="hidden" name="selectedUsername" value="${AllReservation.userName}">
							 </form>
							</tr>
						<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</table>

			</div>
				
		</div>
    </jsp:body>
</t:_layout>
