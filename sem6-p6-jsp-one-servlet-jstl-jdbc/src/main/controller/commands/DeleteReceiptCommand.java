package main.controller.commands;

import main.controller.ConfigurationManager;
import main.controller.commands.Command;
import main.entities.Receipt;
import main.services.MyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteReceiptCommand implements Command {

        private static final String PARAM_NAME_RECEIPT_ID = "receipt-id";

        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String page = null;

            int receipt_id = Integer.parseInt(request.getParameter(PARAM_NAME_RECEIPT_ID));

            MyService myService = new MyService();
            Receipt receipt = myService.getReceiptById(receipt_id);
            myService.deleteReceipt(receipt);

            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_RECEIPT_PAGE_PATH);
            return page;
    }
}
