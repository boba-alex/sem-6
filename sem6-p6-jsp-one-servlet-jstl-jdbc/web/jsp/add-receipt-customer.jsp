<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add receipt customer</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>

<form name="add-receipt-customer-form" action="${pageContext.request.contextPath}/controller" method="GET">
    <input type="hidden" name="command" value="add-receipt-customer"/>
    Name<input type="text" name="name" value=${sessionScope.name != null ? sessionScope.name : ''}>
    Surname<input type="text" name="surname" value=${sessionScope.surname != null ? sessionScope.surname : ''}>
    <br><br>
    <p>
        <input class="btn1" type="submit" name="buttonAddCustomer" value="true"/>
    </p>
</form>
<jsp:useBean id="myService" class="main.services.MyService"></jsp:useBean>
<br>All customers
<br>
<c:forEach var="eachCustomer" items="${myService.receiptCustomers}">
    <c:out value="${eachCustomer.name}"></c:out>
    <c:out value="${eachCustomer.surname}"></c:out>
    <br>
</c:forEach>

</body>
</html>
