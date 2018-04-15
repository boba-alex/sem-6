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
@WebServlet(name = "SecondServlet", urlPatterns = "/secondStep")
public class SecondServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("SECOND");

        String button2 = request.getParameter("button2");
        if (button2 != null) {
            if (button2.equals("prev")) {
                request.getRequestDispatcher("/firstStep").forward(request, response);
            } else {
                String quick = request.getParameter("quick");
                if (quick != null && quick.length() > 0) {
                    Receipt receipt = (Receipt) session.getAttribute("receipt");
                    receipt.setQuick(quick.equals("yes"));
                    System.out.println(session.getAttribute("receipt"));
                    request.getRequestDispatcher("/thirdStep").forward(request, response);
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Second page</title>");
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
        out.println("Do u need quick service?");
        out.println("<form name=\"secondform\" action=/secondStep method=\"GET\">\n");
        if (((Receipt) (session.getAttribute("receipt"))).isQuick()) {
            out.println("YES<input type=\"radio\" name=\"quick\" value=\"yes\" checked>\n");
        } else {
            out.println("YES<input type=\"radio\" name=\"quick\" value=\"yes\">\n");
        }
        if (!((Receipt) (session.getAttribute("receipt"))).isQuick()) {
            out.println("NO<input type=\"radio\" name=\"quick\" value=\"no\" checked>\n");
        } else {
            out.println("NO<input type=\"radio\" name=\"quick\" value=\"no\">\n");
        }
        out.println("<br><br><p>" + "<input class=\"btn1\" type=\"submit\" name=\"button2\" value=\"prev\" />\n" +
                "<input class=\"btn1\" type=\"submit\" name=\"button2\" value=\"next\" />\n</p>" +
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
