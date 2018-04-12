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
@WebServlet(name = "StartServlet", urlPatterns = "/")
public class StartServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("START");

        String buttonStart = request.getParameter("buttonStart");
        if (buttonStart != null) {
            if (buttonStart.equals("START")) {
                session.setAttribute("receipt", new Receipt());
                request.getRequestDispatcher("/firstStep").forward(request, response);
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Start Page</title>");
        out.println("<style type=\"text/css\">" +
                "body{\n" +
                "    text-color: brown;\n" +
                "    font-size: 30px;\n" + "  " +
                "  font-color: red;" + "    background-color: antiquewhite;\n" +
                "    text-align: center;" +
                "}\n" +
                ".btn1{\n" +
                "    font-size: 50px;\n" +
                "} </style>");

        out.println("</head>");
        out.println("<body>");
        out.println("Click button START to build receipt");
        out.println("<form name=\"startform\" action=/ method=\"GET\">\n" +
                "<input  class=\"btn1\" type=\"submit\" name=\"buttonStart\" value=\"START\"/>\n" +
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