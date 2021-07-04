package com.java.library.data;

import com.java.library.model.Book;
import com.java.library.model.User;
import com.java.library.utils.DuplicateUserFound;
import com.java.library.utils.NoBookFound;
import com.java.library.utils.NoUserFound;

import java.util.List;

/**
 * All Function Related to i/o
 */
public interface UserDatabase {
    /**
     * Takes username as a parameter and return match user if found .
     *
     * @param userName Valid username to search in database
     * @return match user
     * @throws NoUserFound When no user is found in database
     * @see com.java.library.model.User
     */
    User getUserByUserName(String userName) throws NoUserFound;

    /**
     * Return all user in database
     *
     * @return all users
     * @see com.java.library.model.User
     */
    List<User> getAllUsers();

    /**
     * Take userName and password and return match user
     *
     * @param userName Register userName
     * @param password Register password
     * @return user
     * @throws NoUserFound When no user is found in database
     * @see com.java.library.model.User User
     */
    User logIn(String userName, String password) throws NoUserFound;

    /**
     * Take user object as parameter and return boolean
     *
     * @param user Takes user object
     * @return boolean
     * @throws DuplicateUserFound If username is already present in database
     * @see com.java.library.model.User User
     */
    boolean addUser(User user) throws DuplicateUserFound;

    /**
     * Return boolean if user is deleted
     *
     * @param user Takes user object
     * @return boolean value
     * @see com.java.library.model.User User
     */
    boolean deleteUser(User user);

    /**
     * Takes currentLogIn user using getUserByUserName() &
     * and new password and return boolean
     *
     * @param user        Login user from getUserByUserName()
     * @param newPassword new Password
     * @return boolean value
     * @throws NoUserFound When no user is found in database
     * @see com.java.library.model.User User
     * @see com.java.library.data.UserDatabase#getUserByUserName(String)
     */
    boolean changePassword(User user, String newPassword) throws NoUserFound;

    /**
     * Take books object and current logIn user from getUSerByUserName()
     *
     * @param book book object
     * @param user current logIn user
     * @return boolean
     * @see com.java.library.model.User User
     * @see com.java.library.model.Book Book
     * @see com.java.library.data.UserDatabase#getUserByUserName(String)
     */
    boolean addBook(Book book, User user);

    /**
     * Take bookId and Current logIn user and return boolean
     *
     * @param bookId book id
     * @param user current logIn User
     * @return Boolean value
     * @throws NoBookFound When no book found
     * @see com.java.library.model.User User
     * @see com.java.library.model.Book Book
     */
    boolean deleteBook(long bookId, User user) throws NoBookFound;


    /**
     * Delete all books from user account and return boolean
     *
     * @param user current login user
     * @return boolean value
     * @see com.java.library.model.User User
     * @see com.java.library.model.Book Book
     */
    boolean deleteAllBooks(User user);
}
