<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Analyze receipts</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/jsp/header.jsp"/>

    <div class="content">
        <jsp:useBean id="myService" class="main.services.MyService"></jsp:useBean>

        <form name="analyze-receipts-form" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="analyze-receipts"/>
            Period
            <div class="styled-select">
                <select name="current-period">
                    <option class="my-option" value="-">-</option>
                    <option class="my-option" value="day">Receipts in current day</option>
                    <option class="my-option" value="month">Receipts in current month</option>
                    <option class="my-option" value="quarter">Receipts in current quarter</option>
                </select>
            </div>
            <br><br>
            <input class="btn1" type="submit" name="buttonAnalyzeReceipts" value="Submit"/>
            </p>
        </form>
        <br>All receipts
        <br>
        <table>
            <tr class="tr-h">
                <th>Name of receipt</th>
                <th>Date of receipt</th>
                <th>Name of customer</th>
                <th>Surname of customer</th>
            </tr>
            <c:forEach var="eachReceipt" items="${sessionScope.chosenReceipts}">
                <tr class="tr1">
                    <td>${eachReceipt.receiptService.name}</td>
                    <td>${eachReceipt.date}</td>
                    <td>${eachReceipt.receiptCustomer.name}</td>
                    <td>${eachReceipt.receiptCustomer.surname}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:include page="/jsp/footer.jsp"/>
</div>

</body>
</html>
