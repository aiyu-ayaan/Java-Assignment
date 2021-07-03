package com.java.in.data;

import com.java.in.model.Book;
import com.java.in.model.User;
import com.java.in.utils.DuplicateUserFound;
import com.java.in.utils.NoBookFound;
import com.java.in.utils.NoUserFound;

import java.util.List;

/**
 * All Function Related to io
 */
public interface UserDatabase {

    User getUserByUserName(String userName) throws NoUserFound;

    List<User> getAllUsers();

    User logIn(String userName, String password) throws NoUserFound;

    boolean addUser(User user) throws DuplicateUserFound;

    boolean deleteUser(User user);

    boolean changePassword(User user, String password) throws NoUserFound;

    boolean addBook(Book book, User user);

    boolean deleteBook(long bookId, User user) throws NoBookFound;
    boolean deleteAllBooks(User user);
}
