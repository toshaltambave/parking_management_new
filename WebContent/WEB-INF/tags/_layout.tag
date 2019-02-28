<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="style.css" rel="stylesheet" type="text/css" />
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	  <div id="pageheader">
	    <jsp:invoke fragment="header"/>
	  </div>
	  <div id="body">
	    <jsp:doBody/>
	  </div>
	  <div id="pagefooter">
	    <jsp:invoke fragment="footer"/>
	  </div>
	</body>
</html>