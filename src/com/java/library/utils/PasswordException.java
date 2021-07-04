package com.java.library.utils;

/**
 * When password doesn't satisfied required condition
 */
public class PasswordException extends Exception {
    /**
     * When password doesn't satisfied required condition
     *
     * @param message display message
     */
    public PasswordException(String message) {
        super(message);
    }
}
