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
@WebServlet(name = "FinishServlet", urlPatterns = "/finishStep")
public class FinishServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Finish page</title>");
        out.println("<style type=\"text/css\">" +
                "body{\n" +
                "    text-color: brown;\n" +
                "    font-size: 30px;\n" + "  " +
                "  font-color: red;" + "    background-color: antiquewhite;\n" +
                "    text-align: center;" +
                "}\n" +
                "btn1{\n" +
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
        out.println("Your receipt");
        out.println("<br>Type of service: " + ((Receipt) session.getAttribute("receipt")).getService());
        out.println("<br>Quick? " + ((Receipt) session.getAttribute("receipt")).isQuick());
        out.println("<br>Name: " + ((Receipt) session.getAttribute("receipt")).getName());
        out.println("<br>Surname: " + ((Receipt) session.getAttribute("receipt")).getSurname());
        out.println("<br>Street: " + ((Receipt) session.getAttribute("receipt")).getStreet());
        out.println("<br>Street: " + session.getAttribute("lololo"));
        out.println("<form name=\"finishform\" action=/thirdStep method=\"GET\">\n" +
                "<br><br><input type=\"submit\" value=\"PREVIOUS\"/>\n" +
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
