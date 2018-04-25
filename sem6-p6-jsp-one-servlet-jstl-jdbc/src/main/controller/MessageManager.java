package main.controller;

import java.util.ResourceBundle;

public class MessageManager {

    private static MessageManager instance;
    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "messages";

    public static final String LOGIN_ERROR_MESSAGE = "Incorrect login or password";

    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "ServletException: Servlet encounters difficulty";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "IOException: Input or output error while handling the request";

    public static final String ADD_RECEIPT_CUSTOMER_ERROR_MESSAGE = "Exception: Input correct name or surname";

    public static final String ADD_RECEIPT_ERROR_MESSAGE = "Exception: Input correct receipt";


    public static MessageManager getInstance() {
        if(instance == null){
            instance = new MessageManager();
        }
        return instance;
    }

    public String getProperty(String key){
        return key;
    }
}
