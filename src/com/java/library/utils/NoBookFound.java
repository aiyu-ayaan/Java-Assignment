package com.java.library.utils;

/**
 * Invoke when their is no book found with given book id
 *
 * @see com.java.library.model.Book
 */
public class NoBookFound extends Exception {
    /**
     * Invoke when their is no book found with given book id
     *
     * @param message display message
     * @see com.java.library.model.Book
     */
    public NoBookFound(String message) {
        super(message);
    }
}
