package main.controller.commands;

import main.controller.ConfigurationManager;
import main.controller.LocaleManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {

    private static final String PARAM_NAME_LOCALE_NAME = "locale-name";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        String localeName = request.getParameter(PARAM_NAME_LOCALE_NAME);

        LocaleManager localeManager = (LocaleManager) request.getSession().getAttribute("localeManager");

        localeManager.updateLocale(localeName);
        request.getSession().setAttribute("locale", localeManager.getLocale());

        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        return page;
    }
}
