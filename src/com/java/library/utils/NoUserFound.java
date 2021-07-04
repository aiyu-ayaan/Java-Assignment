package com.java.library.utils;

/**
 * Invoke when No user is found in database
 *
 * @see com.java.library.model.User
 */
public class NoUserFound extends Exception {
    /**
     * Invoke when No user is found in database
     *
     * @param message display message
     * @see com.java.library.model.User
     */
    public NoUserFound(String message) {
        super(message);
    }
}
