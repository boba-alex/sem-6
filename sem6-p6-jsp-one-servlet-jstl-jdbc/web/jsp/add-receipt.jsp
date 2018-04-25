<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add receipt</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<jsp:useBean id="myService" class="main.services.MyService"></jsp:useBean>

<form name="add-receipt-form" action="${pageContext.request.contextPath}/controller" method="POST">
    <input type="hidden" name="command" value="add-receipt"/>
    Service
    <div class="styled-select">
        <select name="service-id">
            <c:forEach var="eachService" items="${myService.receiptServices}">
                <option class="my-option"
                        value="${eachService.id}">${eachService.name}
                </option>
            </c:forEach>
        </select>
    </div>
    <br><br>
    Receipt customer
    <div class="styled-select">
        <select name="receipt-customer-id">
            <c:forEach var="eachCustomer" items="${myService.receiptCustomers}">
                <option class="my-option"
                        value="${eachCustomer.id}">${eachCustomer.name}-${eachCustomer.surname}
                </option>
            </c:forEach>
        </select>
    </div>
    <p>
        <input class="btn1" type="submit" name="buttonAddReceipt" value="true"/>
    </p>
</form>
<br>All receipts
<br>
<table>
    <c:forEach var="eachReceipt" items="${myService.allReceipts}">
        <tr>
            <td>${eachReceipt.receiptService.name}</td>
            <td>${eachReceipt.date}</td>
            <td>${eachReceipt.receiptCustomer.name}</td>
            <td>${eachReceipt.receiptCustomer.surname}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
