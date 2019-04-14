<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/datepicker.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
		<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container">
	    	<div class="row">
	    	
	    	<form name="formReservation"
				action="${pageContext.request.contextPath}/ReservationsController?Search"
				method="post">
				<div class="form-group">
					<label for="start_time">Start Time</label>
					<div class="col">
					    <div class="input-group date" id="datetimepicker" data-target-input="nearest">
		                    <input id="starttime" type="text" name="start_time" class="form-control datetimepicker-input" data-target="#datetimepicker"/>
		                    <div class="input-group-append" data-target="#datetimepicker" data-toggle="datetimepicker">
		                        <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
		                    </div>
		                </div>
					</div>
				    <label for="end_time">End Time</label>
					<div class="col">
					    <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
		                    <input id="endtime" type="text" name="end_time" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
		                    <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
		                        <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
		                    </div>
	
		                </div>
				    </div>
				    		                    <div><input disabled name="starttimeError" id="starttimeError"
								value="<c:out value='${startTimeError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								 maxlength="60">
							</div>
								                    <div><input disabled name="endtimeError" id="endtimeError"
								value="<c:out value='${endTimeError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								 maxlength="60">
							</div>
				    <div><input disabled name="compareError" id="compareError"
							value="<c:out value='${compareError}'/>"
							type="text"
							style="background-color: white; color: red; border: none; width: 800px"
							 maxlength="60">
					</div>
					<div class="col">
						<div align="center">
				    		<h2>Select an Area to Park</h2>
					        <select name="areaDropDrown" id="areaDropDrown">
					            <c:forEach items="${Areas}" var="ParkingArea">
					                <option value="${ParkingArea.area_Id}"
					                <c:if test="${ParkingArea.area_Id eq selectedAreaId}">selected="selected"</c:if>
					                    >
					                    ${ParkingArea.area_Name}
					                </option>
					            </c:forEach>
					        </select>
			        
						</div>
						<input name="action" value="Search" type="hidden">
						<input id="btnSearch" class="btn btn-secondary" type="submit" value="Search">
					</div>
			        <script type="text/javascript">
			            $('#timepicker1').timepicker();
			        </script>
				</div>
			</form>
		</div>
	</div>	
    </jsp:body>
</t:_layout>
