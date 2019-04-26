<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
<jsp:attribute name="header">
<title>Parking Area</title>
</jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
<div class="container center_div">	

<form name="formAddingParkingArea" action="${pageContext.request.contextPath}/ParkingAreaController?addtoList" method="post">

<div class="row">    
<div class="form-group">
<div class="col">	
<div class="row"><input disabled id="errorMsg" class="form-control" name="errorMsg"  value="<c:out value='${parkingAreaError.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" ></div>

<div class="row"><label> Parking Area Name (*):</label></div> 
<div class="row"><input id="parkingareaname" type="text" class="form-control" name="parkingareaname" value="<c:out value='${parkingArea.areaname}'/>"  maxlength="20" ></div>
<div class="row"><input disabled id="parkingareanameError" class="form-control center_div" name="parkingareanameError"  value="<c:out value='${parkingAreaError.areaNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   maxlength="60"></div>    

<div class="row"><label> Permit Type: </label>
</div>
<div class="row"><select class="form-control style-select" name="permitType" id="permitType">
    	 <c:forEach var="permitTypeValue" items="${allPermitTypes}">
			   <option value="${permitTypeValue}"
			           <c:if test="${permitTypeValue eq selectedpermitType}">selected="selected"</c:if>
			     >
			        ${permitTypeValue}
			    </option>
     	</c:forEach>
		</select>
</div>	

<div class="row"><label> Floor Number (*): </label></div>
<div class="row">
<input id="floornumber" class="form-control" type="number" pattern="\d*" maxlength="2" name="floornumber" value="<c:out value='${parkingArea.floornumber}'/>">
</div>
<div class="row"><input disabled id="floornumberError" class="form-control" name="floornumberError"  value="<c:out value='${parkingAreaError.floorNumberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"    maxlength="60"></div>
 
		
<div class="row"><label> No. of Spots (*): </label></div>
<div class="row">
<input class="form-control" type="number" pattern="\d*" maxlength="3" name="numberofSpots" value="<c:out value='${parkingArea.numberofspots}'/>">
</div>
<div class="row"><input disabled id="numberofSpotsError" class="form-control" name="numberofSpotsError"  value="<c:out value='${parkingAreaError.numberofSpotsError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"    maxlength="60"></div>
 
<div class="row"><label> Mandatory fields (*)</label></div>
<input name="action" value="addtoList" type="hidden">
<div class="row"><input id="btnAddAreaList" class="btn btn-secondary" type="submit" value="Add Area to lists."></div>


<table class="table table-bordered center_div">
<caption>Area Information</caption>
<tr>
<th>Area Name</th>
<th>Floor No.</th>
<th>Permit Type</th>
<th>No. Of Spots</th>
</tr>
<c:forEach items="${areastobeadded}" var="AreasDetails">	
   <tr>  
    	<td>${AreasDetails.areaname}</td>
    	<td>${AreasDetails.floornumber}</td>
    	<td>${AreasDetails.permittype}</td>
    	<td>${AreasDetails.numberofspots}</td>
    </tr>
</c:forEach>
</table>

 </div>
 </div>
 </div>
 </form>
 
<form name="formSaveParkingArea" action="${pageContext.request.contextPath}/ParkingAreaController?saveArea" method="post">
<input name="action" value="saveArea" type="hidden">
<div class="row"><input id="btnSaveArea" class="btn btn-secondary" type="submit" value="Save Area"></div>
					<div class="col">
						<c:if test="${isAreaAdded eq true}">
							<div id="msgAreaSuccess" class="alert alert-success" role="alert"> Area(s) added successfully.</div>
						</c:if>
					</div>	
					
					<div class="col">
						<c:if test="${isAreaListEmpty eq true}">
							<div id="msgAreaException" class="alert alert-danger" role="alert"> Add Area(s) to the list.</div>
						</c:if>
					</div>	

</form>


</div>
</jsp:body>
</t:_layout>
