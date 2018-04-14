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
@WebServlet(name = "ThirdServlet", urlPatterns = "/thirdStep")
public class ThirdServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
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
                if (name != null && surname != null && street != null && name.length() > 0 && surname.length() > 0 && street.length() > 0) {
                    Receipt receipt = (Receipt) session.getAttribute("receipt");
                    receipt.setName(name);
                    receipt.setSurname(surname);
                    receipt.setStreet(street);
                    System.out.println(session.getAttribute("receipt"));
                    request.getRequestDispatcher("/finishStep").forward(request, response);
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Third page</title>");
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
        out.println("Information about you");
        out.println("<form name=\"thirdform\"action=/thirdStep method=\"GET\">\n" +
                "Name<input type=\"text\" name=\"name\" value=" + (((Receipt) session.getAttribute("receipt")).getName() != null ? ((Receipt) session.getAttribute("receipt")).getName() : "-") + " />\n" +
                "Surname<input type=\"text\" name=\"surname\" value=" + (((Receipt) session.getAttribute("receipt")).getSurname() != null ? ((Receipt) session.getAttribute("receipt")).getSurname() : "-") + " />\n" +
                "Street<input type=\"text\" name=\"street\" value=" + (((Receipt) session.getAttribute("receipt")).getStreet() != null ? ((Receipt) session.getAttribute("receipt")).getStreet() : "-") + " />\n" +
                "<br><br>" +
                "<input type=\"submit\" name=\"button3\" value=\"prev\" />\n" +
                "<input type=\"submit\" name=\"button3\" value=\"next\" />\n" +
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

