<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="/jsp/error.jsp" %>
<html>
<head>
    <title>Third page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>

<%
    System.out.println("THIRD");
    String button3 = request.getParameter("button3");
    if (button3 != null) {
        System.out.println("null button3");
        if (button3.equals("prev")) {
            System.out.println("button3");
            request.getRequestDispatcher("/secondStep").forward(request, response);
        } else {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String street = request.getParameter("street");

            session.setAttribute("name", name);
            session.setAttribute("surname", surname);
            session.setAttribute("street", street);

            Pattern p = Pattern.compile("[a-zA-Z]+");
            Matcher matcher = p.matcher(name);
            if (!matcher.matches()) {
                throw new RuntimeException("Please, write correct name!");
            }

            p = Pattern.compile("[a-zA-Z]+");
            matcher = p.matcher(surname);
            if (!matcher.matches()) {
                throw new RuntimeException("Please, write correct surname!");
            }

            if (street != null && name.length() > 0 && surname.length() > 0 && street.length() > 0) {
                request.getRequestDispatcher("/finishStep").forward(request, response);
            } else {
                throw new RuntimeException("Please, write correct not null data :)!");
            }
        }
    }
%>
<form name="thirdform" action="/thirdStep" method="GET">
    Name<input type="text" name="name" value=${sessionScope.name != null ? sessionScope.name : ''}>
    Surname<input type="text" name="surname" value=${sessionScope.surname != null ? sessionScope.surname : ''}>
    Street<input type="text" name="street" value=${sessionScope.street != null ? sessionScope.street : ''}>
    <br><br>
    <p>
        <input class="btn1" type="submit" name="button3" value="prev"/>
        <input class="btn1" type="submit" name="button3" value="next"/>
    </p>
</form>
</body>
</html>
