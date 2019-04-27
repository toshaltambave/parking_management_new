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
			    <h2>Select a area</h2>
			    <form action="${pageContext.request.contextPath}/SpotSearchController?getSelectedArea" method="post">
			        Available Areas:&nbsp;
			        <select id="areaDropDrown" name="areaDropDrown">
			            <c:forEach items="${Areas}" var="ParkingArea">
			                <option value="${ParkingArea.area_Id}"
			                <c:if test="${ParkingArea.area_Id eq selectedAreaId}">selected="selected"</c:if>
			                    >
			                    ${ParkingArea.area_Name}
			                </option>
			            </c:forEach>
			        </select>
			        <input name="action" value="getSelectedArea" type="hidden">
			        <input id="btnGetAreaFloors" class="btn btn-secondary" type="submit" value="Submit" />
			    </form>
			</div>
<!-- 			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button> -->
		</div>
    </jsp:body>
</t:_layout>


