//package main.controller.commands;
//
//import main.controller.ConfigurationManager;
//import main.entities.Receipt;
//import main.entities.ReceiptCustomer;
//import main.entities.ReceiptService;
//import main.services.MyService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDate;
//
//public class AddReceiptCommand implements Command {
//
//    private static final String PARAM_NAME_SERVICE_ID = "service-id";
//    private static final String PARAM_NAME_RECEIPT_CUSTOMER_ID = "receipt-customer-id";
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String page = null;
//
//        int service_id = Integer.parseInt(request.getParameter(PARAM_NAME_SERVICE_ID));
//        int receipt_customer_id = Integer.parseInt(request.getParameter(PARAM_NAME_RECEIPT_CUSTOMER_ID));
//
//        MyService myService = new MyService();
//        ReceiptService receiptService = myService.getReceiptServiceById(service_id);
//        ReceiptCustomer receiptCustomer = myService.getReceiptCustomerById(receipt_customer_id);
//
//        Receipt receipt = new Receipt();
//        receipt.setReceiptService(receiptService);
//        receipt.setReceiptCustomer(receiptCustomer);
//        receipt.setDate(LocalDate.now());
//        myService.addReceipt(receipt);
//
//        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_RECEIPT_PAGE_PATH);
//        return page;
//    }
//}
