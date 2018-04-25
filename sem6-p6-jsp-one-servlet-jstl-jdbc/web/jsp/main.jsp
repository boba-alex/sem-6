<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3>Welcome!</h3>
<hr>
<c:out value="${user}, Hello!"/>
<hr>
<a href="controller">Return to login page</a>

</body>
</html>
