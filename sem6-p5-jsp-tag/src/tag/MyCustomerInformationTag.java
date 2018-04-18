package tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class MyCustomerInformationTag extends SimpleTagSupport {

    private String name;

    private String surname;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (name != null && surname != null) {
            out.println("<br>Name: " + name);
            out.println("<br>Surname: " + surname);
        } else {
            throw new JspTagException("Not correct data in attribute!");
        }

        getJspBody().invoke(sw);
        out.println("<br>Street: " + sw.toString());
    }
}