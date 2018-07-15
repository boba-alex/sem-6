package main.controller;

import main.controller.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {

    private static RequestHelper instance = null;

    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        commands.put("change-language", new ChangeLanguageCommand());
//        commands.put("add-receipt", new AddReceiptCommand());
//        commands.put("delete-receipt", new DeleteReceiptCommand());
//        commands.put("analyze-receipts", new AnalyzeReceiptsCommand());
//    }
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");

        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    //Singleton
    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
