package main.controller;

public class ConfigurationManager {
    private static ConfigurationManager instance;

    public static final String INDEX_PAGE_PATH = "/";
    public static final String ERROR_PAGE_PATH = "/jsp/error.jsp";
    public static final String ADD_RECEIPT_CUSTOMER_PAGE_PATH = "/jsp/add-receipt-customer.jsp";
    public static final String ADD_RECEIPT_PAGE_PATH = "/jsp/add-receipt.jsp";
    public static final String ANALYZE_RECEIPTS_PAGE_PATH = "/jsp/analyze-receipts.jsp";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return key;
    }
}
