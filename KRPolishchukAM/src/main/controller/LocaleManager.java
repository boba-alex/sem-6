package main.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    private ResourceBundle resourceBundle;

    private static final String LANGUAGE_EN = "en";
    private static final String LANGUAGE_RU = "ru";
    private static final String US = "US";
    private static final String RUSSIA = "RU";
    private static final String fileName = "resources/text";
    private Locale locale = new Locale(LANGUAGE_RU, RUSSIA);

    public void updateLocale(String localeName) {
        switch (localeName) {
            case "en":
                locale = new Locale(LANGUAGE_EN, US);
                break;
            case "ru":
                locale = new Locale(LANGUAGE_RU, RUSSIA);
                break;
            default:
                locale = new Locale(LANGUAGE_EN, US);
                break;
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

//    public ResourceBundle getResourceBundle() {
//        return ResourceBundle.getBundle(fileName, locale);
//    }
    //jLabel4.setText(rb.getString("country"));
}





