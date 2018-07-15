<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>List of deals</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="/resources/text" var="bundle"/>
<div class="wrapper">
    <jsp:include page="/jsp/header.jsp"/>

    <div class="content">
        <jsp:useBean id="dealService" class="main.services.DealService"/>
        <jsp:useBean id="localeManager" class="main.controller.LocaleManager" scope="session"/>
        <br>
        <table>
            <caption><fmt:message key="deals" bundle="${bundle}"/></caption>
            <thead>
            <tr>
                <th>Id</th>
                <th>Type</th>
                <th>Name</th>
                <th>Date</th>
                <th>Sum</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="eachDeal" items="${dealService.deals}">
                <tr class="tr1">
                    <td>${eachDeal.id}</td>
                    <td>${eachDeal.type}</td>
                    <td>${eachDeal.name}</td>
                    <td>${eachDeal.dateOfDeal}</td>
                    <td>${eachDeal.sum}</td>
                </tr>
            </c:forEach>
            <tr>
                <td>Количество</td>
                <td>${dealService.countAllDeals()}</td>
                <td>Сумма</td>
                <td>${dealService.sumAllDeals()}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
