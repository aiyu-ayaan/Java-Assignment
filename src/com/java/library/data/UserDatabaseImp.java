package com.java.library.data;

import com.java.library.model.Book;
import com.java.library.model.User;
import com.java.library.utils.Constants;
import com.java.library.utils.DuplicateUserFound;
import com.java.library.utils.NoBookFound;
import com.java.library.utils.NoUserFound;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * It's contain all function related to i/o by implementing
 * UserDatabase interface
 *
 * @see com.java.library.data.UserDatabase userDatabase
 */
public class UserDatabaseImp implements UserDatabase {

    private List<User> allUser;

    /**
     * It's contain all function related to i/o by implementing
     * UserDatabase interface
     *
     * @see com.java.library.data.UserDatabase userDatabase
     */
    public UserDatabaseImp() {
//        Setting predefined user from Constants::class
        allUser = Constants.list;
    }


    private User getUser(User user) throws NoUserFound {
        for (var u : allUser) {
            if (u.getUserName().equals(user.getUserName()) &&
                    u.getUserPassword().equals(user.getUserPassword())
            ) {
                return u;
            }
        }
        throw new NoUserFound("No User found with this user name or either your password is incorrect \n");
    }

    @Override
    public User getUserByUserName(String userName) throws NoUserFound {
        for (var user : allUser) {
            if (userName.equals(user.getUserName()))
                return user;
        }
        throw new NoUserFound("No user found with this userName !!!\n");
    }

    @Override
    public List<User> getAllUsers() {
        return allUser;
    }

    @Override
    public User logIn(String userName, String password) throws NoUserFound {
        return getUser(new User(userName, password));
    }

    @Override
    public boolean addUser(User user) throws DuplicateUserFound {
        for (var u : allUser) {
            if (u.getUserName().equals(user.getUserName())) {
                throw new DuplicateUserFound("User is already register !!!\n");
            }
        }
        allUser.add(user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        allUser = allUser.stream().filter(u -> !(u.getUserName().equals(user.getUserName()))).collect(Collectors.toList());
        return true;
    }

    @Override
    public boolean changePassword(User user, String newPassword) throws NoUserFound {
        var logInUser = getUser(user);
        logInUser.setUserPassword(newPassword);
        return true;
    }

    @Override
    public boolean addBook(Book book, User user) {
        var books = user.getBorrowedBooks();
        books.add(book);
        user.setBorrowedBooks(books);
        return true;
    }

    @Override
    public boolean deleteBook(long bookId, User user) throws NoBookFound {
        var book = getBookById(bookId, user);
        if (book == null) throw new NoBookFound("No Book is found by this Id");
        user.setBorrowedBooks(user.getBorrowedBooks()
                .stream()
                .filter(b -> (bookId != b.getBookId()))
                .collect(Collectors.toList()));
        return true;
    }

    @Override
    public boolean deleteAllBooks(User user) {
        user.setBorrowedBooks(new ArrayList<>());
        return true;
    }

    private Book getBookById(long bookId, User user) {
        var books = user.getBorrowedBooks();
        for (var book : books) {
            if (bookId == book.getBookId()) return book;
        }
        return null;
    }
}
