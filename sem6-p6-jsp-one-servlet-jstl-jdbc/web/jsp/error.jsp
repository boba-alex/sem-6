<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h3>Error</h3>
<%=
    (request.getAttribute("errorMessage") != null) ?
    (String) request.getAttribute("errorMessage") : "unknown error"
%>
<hr>
<a href="controller">Return to login page</a>

</body>
</html>
