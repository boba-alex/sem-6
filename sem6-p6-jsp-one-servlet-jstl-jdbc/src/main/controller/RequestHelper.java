package main.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {

    private static RequestHelper instance = null;

    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper(){
        commands.put("login", new LoginCommand());
        commands.put("add-receipt-customer", new AddReceiptCustomerCommand());
        commands.put("add-receipt", new AddReceiptCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");

        Command command = commands.get(action);
        if(command == null){
            command = new NoCommand();
        }
        return command;
    }
    //Singleton
    public static RequestHelper getInstance() {
        if(instance == null){
            instance = new RequestHelper();
        }
        return instance;
    }
}
