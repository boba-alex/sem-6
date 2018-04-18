<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First Page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<%
    System.out.println("FIRST");

    String button1 = request.getParameter("button1");
    if (button1 != null) {
        if (button1.equals("prev")) {
            request.getRequestDispatcher("/").forward(request, response);
        } else {
            String service = request.getParameter("service");
            if (service != null && service.length() > 0) {
                request.getSession().setAttribute("service", service);
                request.getRequestDispatcher("/secondStep").forward(request, response);
            }
        }
    }
%>

<p>Choose service</p>
<form name="firstform" action="/firstStep" method="GET">
    <div class="styled-select">
        <select name="service">
            <option class="my-option"
                    value="KNITTING" ${sessionScope.service == 'KNITTING' ? 'selected="selected"' : ''}>KNITTING
            </option>
            <option class="my-option"
                    value="REPAIR_DRESSES" ${sessionScope.service == 'REPAIR_DRESSES' ? 'selected="selected"' : ''}>
                REPAIR_DRESSES
            </option>
            <option class="my-option"
                    value="REPAIR_SHOES" ${sessionScope.service == 'REPAIR_SHOES' ? 'selected="selected"' : ''}>
                REPAIR_SHOES
            </option>
        </select>
    </div>
    <br><br>
    <p>
        <input class="btn1" type="submit" name="button1" value="prev"/>
        <input class="btn1" type="submit" name="button1" value="next"/></p>
</form>

</body>
</html>
