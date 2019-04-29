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
			<form action="${pageContext.request.contextPath}/UserDetailsController?fakepost" method="post">
				<input id="faketest" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/UpdateUserController?fakepost" method="post">
				<input id="faketestupdate" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/UpdateUserController?fakepost" method="get">
				<input id="faketestclick" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/ModifyReservationController?fakepost" method="get">
				<input id="faketest2" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/ModifyReservationController?fakepost" method="post">
				<input id="faketest3" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/ParkingSpotsController?fakepost" method="post">
				<input id="faketest4" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/ParkingAreaController?fakepost" method="get">
				<input id="faketest5" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/ParkingAreaController?fakepost" method="post">
				<input id="faketest6" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/SpotSearchController?fakepost" method="post">
				<input id="faketest7" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/UsersController?fakepost" method="post">
				<input id="faketest8" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/ReservationsController?fakepost" method="post">
				<input id="faketest9" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/HomeController?fakepost" method="get">
				<input id="faketest10" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/UpdateUserController?fakepost" method="post">
				<input id="faketest11" class="btn btn-secondary" type="submit" value="Select" />
				<input name="action" value="fakepost" type="hidden">
			</form>
		</div>
    </jsp:body>
</t:_layout>
