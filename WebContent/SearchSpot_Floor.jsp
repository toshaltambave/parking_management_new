<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>Select Floor</title>
</head>
<body>
<div align="center">
    <h2>${selectedArea.area_Name}</h2>
    <form action="${pageContext.request.contextPath}/SpotSearchController?getSpotsForFloor" method="post">
		<table>
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
					<td><input type="submit" value="Select" /></td>
				</tr>
				<input type="hidden" name="selectedAreaId" value="${selectedArea.area_Id}">
				<input type="hidden" name="selectedFloorNumber" value="${ParkingAreaFloors.floor_Number}">
				<input type="hidden" name="selectedPermitType" value="${ParkingAreaFloors.permitType}">
			</c:forEach>
		</table>
		<input name="action" value="getSpotsForFloor" type="hidden">
    </form>
</div>
<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>