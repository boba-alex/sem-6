<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
    </style>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<%
    System.out.println("START");
    String buttonStart = request.getParameter("buttonStart");
    if (buttonStart != null) {
        if (buttonStart.equals("START")) {
            session.removeAttribute("service");
            session.removeAttribute("quick");
            session.removeAttribute("name");
            session.removeAttribute("surname");
            session.removeAttribute("street");
            request.getRequestDispatcher("/firstStep").forward(request, response);
        }
    }
%>
<p>Click button START to build receipt</p>
<form name="startform" action="/" method="GET">
    <input class="btn1" type="submit" name="buttonStart" value="START"/>
</form>
</body>
</html>
