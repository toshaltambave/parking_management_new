<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>Select Parking Spot</title>
</head>
<body>
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
						<td><input type="submit" value="${ParkingSpots.spot_Id}" /></td>
					</tr>
					<input type="hidden" name="selectedSpotId" value="${ParkingSpots.spot_Id}">
				</c:forEach>
			</table>
			<input name="action" value="" type="hidden">
    </form>
</div>
<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>