<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<div align="center">
    <h2>Select an Area to Park</h2>
    <form action="${pageContext.request.contextPath}/SpotSearchController?getSelectedArea" method="post">
        Available Areas:&nbsp;
        <select name="areaDropDrown">
            <c:forEach items="${Areas}" var="ParkingArea">
                <option value="${ParkingArea.area_Id}"
                <c:if test="${ParkingArea.area_Id eq selectedAreaId}">selected="selected"</c:if>
                    >
                    ${ParkingArea.area_Name}
                </option>
            </c:forEach>
        </select>
        <input name="action" value="getSelectedArea" type="hidden">
        <input type="submit" value="Submit" />
    </form>
</div>
<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>