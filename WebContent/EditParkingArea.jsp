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
			    </br>
			    <form action="${pageContext.request.contextPath}/ParkingAreaController?editAreaName" method="post">
			    <div class="row">
				<div class="form-group">
				<div class="col">
				<label>Parking Area:</label>
					<input id="txteditAreaName" type="text" class="form-control" name="txteditAreaName" value="<c:out value='${selectedAreaName}'/>"  maxlength="45">
			        <input disabled id="errorAreaMsg" class="form-control" name="errorAreaMsg"  value="<c:out value='${areanameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" >
			        <input id="btneditAreaName" class="btn btn-secondary" type="submit" value="Edit Area Name" />
			        <input name="action" value="editAreaName" type="hidden">
					<input id="txteditAreaNumber" name="txteditAreaNumber" value="<c:out value='${selectedAreaId}'/>" type="hidden">
				</div>
				</div>
				</div>       
			    </form>
			   	<div class="col">
					<c:if test="${isParkingAreaUpdate eq true}">
						<div id="msgParkingAreaUpdate" class="alert alert-success" role="alert"> Parking Area name updated.</div>
					</c:if>
				</div>	
			</div>
		</div>
			
		   </br>			
		   <div class="container">
			<div align="center">
					<table class="table table-bordered center_div">
						<tr>
							<th>Floor Number</th>
							<th>Permit Type</th>
							<th>Number of Spots</th>
<%-- 							<th></th> --%>
<%-- 							<th>Edit Permit Type</th> --%>
<%-- 							<th></th> --%>
						</tr>
						<c:forEach items="${allFloors}" var="ParkingAreaFloors">
						<tr>
						<form action="${pageContext.request.contextPath}/ParkingAreaController?getFloorSpots" method="post">	
							<td>${ParkingAreaFloors.floor_Number}</td>
							<td>${ParkingAreaFloors.permitType}</td>
							<td>${ParkingAreaFloors.no_Spots}</td>
							<td><input id="btnSearchSpotFloor${ParkingAreaFloors.floor_Number}${ParkingAreaFloors.permitType}" class="btn btn-secondary" type="submit" value="Select" /></td>
							<input type="hidden" name="selectedAreaId" value="${selectedArea.area_Id}">
							<input type="hidden" name="selectedFloorNumber" value="${ParkingAreaFloors.floor_Number}">
							<input type="hidden" name="selectedPermitType" value="${ParkingAreaFloors.permitType}">
							<input type="hidden" name="selectedSpots" value="${ParkingAreaFloors.no_Spots}">
							<input name="action" value="getFloorSpots" type="hidden">
			    		</form>
<%-- 			    		<form action="${pageContext.request.contextPath}/ParkingAreaController?editPermitType" method="post"> --%>
<%-- 			    			<td> --%>
<!-- 								<select class="form-control style-select" name="permitType" id="permitType"> -->
<%-- 					            <c:forEach items="${allPermitTypes}" var="permitTypeValue"> --%>
<%-- 					                <option value="${permitTypeValue}" --%>
<%-- 					                <c:if test="${permitTypeValue eq ParkingAreaFloors.permitType}">selected="selected"</c:if>> --%>
<%-- 					                    ${permitTypeValue} --%>
<!-- 					                </option> -->
<%-- 					            </c:forEach> --%>
<!-- 					        	</select> -->
<%-- 								</td> --%>
<%-- 			    			<td><input id="btnEditPermitType${ParkingAreaFloors.floor_Number}${ParkingAreaFloors.permitType}" class="btn btn-secondary" type="submit" value="Edit Permit Type" /></td> --%>
<%-- 			    			<input type="hidden" name="selectedAreaId" value="${selectedArea.area_Id}"> --%>
<%-- 							<input type="hidden" name="selectedFloorNumber" value="${ParkingAreaFloors.floor_Number}"> --%>
<%-- 							<input type="hidden" name="selectedPermitType" value="${ParkingAreaFloors.permitType}"> --%>
<%-- 							<input type="hidden" name="selectedSpots" value="${ParkingAreaFloors.no_Spots}"> --%>
<!-- 			    			<input name="action" value="editPermitType" type="hidden"> -->
<!-- 			    		</form>	 -->
					</tr>
					</c:forEach>
					</table>
					<div class="col">
						<c:if test="${isPermitUpdate eq true}">
							<div id="msgPermitUpdate" class="alert alert-success" role="alert"> Permit type updated.</div>
						</c:if>
					</div>	
					<div class="col">
						<c:if test="${isPermitError eq true}">
							<div id="msgPermitError" class="alert alert-danger" role="alert"> Conflict as Permit Type of same exists already on same floor.</div>
						</c:if>
					</div>	
			</div>				
		</div>

</div>
</jsp:body>
</t:_layout>