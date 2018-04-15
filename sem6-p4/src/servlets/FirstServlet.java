package servlets;

import beans.Receipt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by User on 08.04.2018.
 */
@WebServlet(name = "FirstServlet", urlPatterns = "/firstStep")
public class FirstServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("FIRST");

        String button1 = request.getParameter("button1");
        if (button1 != null) {
            if (button1.equals("prev")) {
                request.getRequestDispatcher("/").forward(request, response);
            } else {
                String service = request.getParameter("service");
                if (service != null && service.length() > 0) {
                    Receipt receipt = (Receipt) session.getAttribute("receipt");
                    receipt.setService(service);
                    System.out.println(session.getAttribute("receipt"));
                    request.getRequestDispatcher("/secondStep").forward(request, response);
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>First Page</title>");
        out.println("<style type=\"text/css\">" +
                "body{\n" +
                "    text-color: brown;\n" +
                "    font-size: 30px;\n" + "  " +
                "  font-color: red;" + "    background-color: antiquewhite;\n" +
                "    text-align: center;" +
                "}\n" +
                ".btn1{\n" +
                "font-size: 50px;\n" +
                "color: blue;" +
                "}\n" +
                "select{\n" +
                " width: 300px;   \n" +
                " heigth: 35px;   \n" +
                "}\n" +
                "} </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("Choose service");
        out.println("<form name=\"firstform\" action=/firstStep method=\"GET\">\n" +
                "<select name=\"service\">\n");
        if (((Receipt) (session.getAttribute("receipt"))).getService() != null &&
                ((Receipt) (session.getAttribute("receipt"))).getService().equals("KNITTING")) {
            out.println("<option selected value=\"KNITTING\">" + "KNITTING" + "</option>\n");
        } else {
            out.println("<option value=\"KNITTING\">" + "KNITTING" + "</option>\n");
        }

        if (((Receipt) (session.getAttribute("receipt"))).getService() != null &&
                ((Receipt) (session.getAttribute("receipt"))).getService().equals("REPAIR_DRESSES")) {
            out.println("<option selected value=\"REPAIR_DRESSES\">\n" + "REPAIR_DRESSES" + "</option>\n");
        } else {
            out.println("<option value=\"REPAIR_DRESSES\">\n" + "REPAIR_DRESSES" + "</option>\n");
        }

        if (((Receipt) (session.getAttribute("receipt"))).getService() != null &&
                ((Receipt) (session.getAttribute("receipt"))).getService().equals("REPAIR_SHOES")) {
            out.println("<option selected value=\"REPAIR_SHOES\">\n" + "REPAIR_SHOES" + "</option>\n");
        } else {
            out.println("<option value=\"REPAIR_SHOES\">\n" + "REPAIR_SHOES" + "</option>\n");
        }

        out.println("</select>\n" +"<br><br>"+ "<p>" +
                "<input class=\"btn1\" type=\"submit\" name=\"button1\" value=\"prev\" />\n" +
                "<input class=\"btn1\" type=\"submit\" name=\"button1\" value=\"next\" />\n" + "</p>" +
                "</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


}