<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Footer - Team 1 - CSE 6329</p>
    </jsp:attribute>
    <jsp:body>
        <t:Navbar></t:Navbar>
        <p>Body Starts Here</p>
    </jsp:body>
</t:_layout>