<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" uri="customtags" %>
<%@page errorPage="/jsp/error.jsp" %>

<html>
<head>
    <title>Finish page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<%
    System.out.println("FINISH");
%>
<p>Your receipt</p>
<br>Type of service: ${empty sessionScope.service ? '': sessionScope.service}
<br>Quick? <c:out value="${empty sessionScope.quick ? '' : sessionScope.quick}"/>
<my:my-customer-info-tag name="${empty sessionScope.name ? '-' : sessionScope.name}"
                         surname="${empty sessionScope.surname ? '-' : sessionScope.surname}">
    ${empty sessionScope.street ? '-' : sessionScope.street}
</my:my-customer-info-tag>
<form name="finishform" action=/thirdStep method="GET">
    <br><br>
    <p>
        <input class="btn1" type="submit" value="PREVIOUS"/>
    </p>
</form>
</body>
</html>
