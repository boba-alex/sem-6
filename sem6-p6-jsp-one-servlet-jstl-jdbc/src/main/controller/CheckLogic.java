package main.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckLogic {

    public static boolean checkNameAndSurname(String name, String surname) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = p.matcher(name);
        if (!matcher.matches()) {
            return false;
        }
        matcher = p.matcher(surname);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
}
