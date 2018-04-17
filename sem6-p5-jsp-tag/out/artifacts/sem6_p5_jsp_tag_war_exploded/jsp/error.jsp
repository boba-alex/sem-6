<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error page</title>
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div id="content">
    <div id="content-header">
        <h2 class="header-text">Ошибка:</h2>
    </div>
    <div id="content-main">
        <div id="form" class="infobox">
            <p>Status code: ${pageContext.errorData.statusCode}</p>
            <p>From: ${pageContext.errorData.requestURI}</p>
            <p>Message: ${pageContext.errorData.throwable.message}</p>
            Back:<a href=${pageContext.request.contextPath}${pageContext.errorData.requestURI}>-></a>
        </div>
    </div>
</div>
</body>
</html>