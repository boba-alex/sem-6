<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add receipt</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/jsp/header.jsp"/>

    <div class="content">
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
                <input class="btn1" type="submit" name="buttonAddReceipt" value="Submit"/>
            </p>
        </form>
        <br>All receipts
        <br>
        <table>
            <tr class="tr-h">
                <th class="td1">Name of receipt</th>
                <th>Date of receipt</th>
                <th>Name of customer</th>
                <th>Surname of customer</th>
                <th>Action</th>
            </tr>
            <c:forEach var="eachReceipt" items="${myService.allReceipts}">
                <c:url var="deleteLink" value="${pageContext.request.contextPath}/controller">
                    <c:param name="receipt-id" value="${eachReceipt.id}"/>
                    <c:param name="command" value="delete-receipt"/>
                </c:url>
                <tr class="tr1">
                    <td class="td1">${eachReceipt.receiptService.name}</td>
                    <td>${eachReceipt.date}</td>
                    <td>${eachReceipt.receiptCustomer.name}</td>
                    <td>${eachReceipt.receiptCustomer.surname}</td>
                    <td><a href="${deleteLink}"
                           onclick="if (!(confirm('Are u sure u want to delete this receipt?'))) return false">Delete
                    </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:include page="/jsp/footer.jsp"/>
</div>

</body>
</html>
