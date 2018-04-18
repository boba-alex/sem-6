<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Second page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<%
    System.out.println("SECOND");
    String button2 = request.getParameter("button2");
    if (button2 != null) {
        if (button2.equals("prev")) {
            request.getRequestDispatcher("/firstStep").forward(request, response);
        } else {
            String quick = request.getParameter("quick");
            if (quick != null && quick.length() > 0) {
                request.getSession().setAttribute("quick", quick);
                request.getRequestDispatcher("/thirdStep").forward(request, response);
            }
        }
    }
%>
<p>Do u need quick service?</p>
<form name="secondform" action="/secondStep" method="GET">
    YES<input type="radio" name="quick" value="yes" ${sessionScope.quick == 'yes' ? 'checked' : ''}>
    NO<input type="radio" name="quick" value="no" ${sessionScope.quick == 'yes'? '' : 'checked'}>
    <br><br>
    <p>
        <input class="btn1" type="submit" name="button2" value="prev"/>
        <input class="btn1" type="submit" name="button2" value="next"/>
    </p>
</form>
</body>
</html>
