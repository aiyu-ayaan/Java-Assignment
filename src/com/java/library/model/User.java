package com.java.library.model;

import java.util.ArrayList;
import java.util.List;

/**
 *Entity class for student
 */
public class User {
    private String userName;
    private String userPassword;
    private List<Book> borrowedBooks;

    /**
     * Entity class for student
     *
     * @param userName     username of given user
     * @param userPassword user password
     */
    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * This constructor is only called to initialize pre define list in UserDatabase
     *
     * @param userName
     * @param userPassword
     * @param borrowedBooks
     * @see com.java.library.utils.Constants Constants
     * @see com.java.library.data.UserDatabaseImp User Database
     */
    public User(String userName, String userPassword, List<Book> borrowedBooks) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.borrowedBooks = borrowedBooks;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
