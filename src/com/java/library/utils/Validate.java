package com.java.library.utils;

import java.util.regex.Pattern;

/**
 * Class that handles all validation process in this project
 */
public class Validate {
    /**
     * This function valid input password..
     *
     * @param password inputPassword
     * @return return boolean
     * @see java.util.regex.Pattern Pattern
     */
    public static boolean validatePassword(String password) throws PasswordException {
        var ex1 = "\\w{5,}\\d*";
        var ex3 = "\\w{5,}\\W+\\d*";
        var match = Pattern.matches(ex1, password)
                || Pattern.matches(ex3, password);
        if (match) {
            return true;
        }
        throw new PasswordException("Password at least contain 5 letters");
    }
}
