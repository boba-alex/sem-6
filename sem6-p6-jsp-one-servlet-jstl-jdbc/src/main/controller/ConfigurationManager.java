package main.controller;

public class ConfigurationManager {
    private static ConfigurationManager instance;

    private static final String BUNDLE_NAME = "config";

    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";

    public static final String DATABASE_URL = "DATABASE_URL";

    public static final String ERROR_PAGE_PATH = "/jsp/error.jsp";
    public static final String MAIN_PAGE_PATH = "/jsp/main.jsp";
    public static final String LOGIN_PAGE_PATH = "/jsp/login.jsp";
    public static final String ADD_RECEIPT_CUSTOMER_PAGE_PATH = "/jsp/add-receipt-customer.jsp";
    public static final String ADD_RECEIPT_PAGE_PATH = "/jsp/add-receipt.jsp";

    public static ConfigurationManager getInstance() {
        if(instance == null){
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key){
        return key;
    }
}
