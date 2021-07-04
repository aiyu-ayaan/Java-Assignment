package com.java.library.utils;

/**
 * Invoke when username is already present in database
 *
 * @see com.java.library.model.User User
 */
public class DuplicateUserFound extends Exception {
    /**
     * Invoke when username is already present in database
     *
     * @param message display message
     * @see com.java.library.model.User User
     */
    public DuplicateUserFound(String message) {
        super(message);
    }
}
