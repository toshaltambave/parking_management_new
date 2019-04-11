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
			    <h2>${resselectedArea.area_Name}</h2>
			    <form action="${pageContext.request.contextPath}/ReservationsController?checkout" method="post">
			        <h3>Floor: ${resselectedFloorNumber} </h3>
			        <h3>Permit Type: ${resselectedPermitType} </h3>
			        <h3>Time: ${resselectedStartTime} To ${resselectedEndTime } </h3>
			        <h3>Spot ID : ${resspotID} </h3>
			</div>
			<div>
			        <label>Select Extras</label>
						<div class="form-group">
							<h6>Need Cart ? Adds $${cartPrice} </h6>
							<label class="radio-inline"><input id="cart" type="radio" name="cart" value="true">Yes</label>
							<label class="radio-inline"><input id="cart" type="radio" name="cart" value="false" checked>No</label>
						</div>
						<div class="form-group">
							<h6>Select Camera ? Adds $${cameraPrice}</h6>
							<label class="radio-inline"><input id="camera" type="radio" name="camera" value="true">Yes</label>
							<label class="radio-inline"><input id="camera" type="radio" name="camera" value="false" checked>No</label>
						</div>
						<div class="form-group">
							<h6>Select History ? Adds $${historyPrice}</h6>
							<label class="radio-inline"><input id="history" type="radio" name="history" value="true">Yes</label>
							<label class="radio-inline"><input id="history" type="radio" name="history" value="false" checked>No</label>
						</div>
						<input type="hidden" name="cart_price" value="${cartPrice}">
						<input class="btn btn-secondary" name="action" value="checkout" type="hidden">
						<input id="btnOptions" class="btn btn-secondary" type="submit" value="Finish">
			    </form>
			</div>
<!-- 			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button>			 -->
				
		</div>
    </jsp:body>
</t:_layout>
