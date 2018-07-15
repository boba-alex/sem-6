<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<fmt:setLocale value = "${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources/text" var="bundle"/>
<div class="topnav">
    <c:url var="changeEn" value="${pageContext.request.contextPath}/controller">
        <c:param name="locale-name" value="en"/>
        <c:param name="command" value="change-language"/>
    </c:url>
    <c:url var="changeRu" value="${pageContext.request.contextPath}/controller">
        <c:param name="locale-name" value="ru"/>
        <c:param name="command" value="change-language"/>
    </c:url>
    <a href="${changeEn}"><fmt:message key = "english" bundle="${bundle}"/></a>
    <a href="${changeRu}"><fmt:message key = "russian" bundle="${bundle}"/></a>
</div>
