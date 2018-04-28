package main.controller.commands;

import main.controller.ConfigurationManager;
import main.entities.Receipt;
import main.services.MyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeReceiptsCommand implements Command {

    private static final String PARAM_NAME_CURRENT_PERIOD = "current-period";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        String current_period = request.getParameter(PARAM_NAME_CURRENT_PERIOD);

        MyService myService = new MyService();
        List<Receipt> chosenReceipts = new ArrayList<>();

        if (current_period.equals("day")) {
            chosenReceipts = myService.getReceiptsInCurrentDay();
            request.getSession().setAttribute("chosenReceipts", chosenReceipts);
        } else if (current_period.equals("month")) {
            chosenReceipts = myService.getReceiptsInCurrentMonth();
            request.getSession().setAttribute("chosenReceipts", chosenReceipts);
        } else if (current_period.equals("quarter")) {
            chosenReceipts = myService.getReceiptsInCurrentQuarter();
            request.getSession().setAttribute("chosenReceipts", chosenReceipts);
        } else {
            chosenReceipts = myService.getAllReceipts();
            request.getSession().setAttribute("chosenReceipts", chosenReceipts);
        }

        //request.getPa

        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ANALYZE_RECEIPTS_PAGE_PATH);
//        else{
//            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.ADD_RECEIPT_CUSTOMER_ERROR_MESSAGE));
//            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
//        }
        return page;
    }
}
