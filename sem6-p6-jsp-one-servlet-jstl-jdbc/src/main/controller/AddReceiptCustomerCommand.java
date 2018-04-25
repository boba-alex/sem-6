package main.controller;

import main.entities.ReceiptCustomer;
import main.services.MyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddReceiptCustomerCommand implements Command{

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);

        if(CheckLogic.checkNameAndSurname(name, surname)){
            MyService myService = new MyService();
            myService.addReceiptCustomer(new ReceiptCustomer(name, surname));
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_RECEIPT_CUSTOMER_PAGE_PATH);
        }
        else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.ADD_RECEIPT_CUSTOMER_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}

