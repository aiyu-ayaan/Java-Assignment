package com.java.in.data;

import com.java.in.model.Book;
import com.java.in.model.User;
import com.java.in.utils.DuplicateUserFound;
import com.java.in.utils.NoUserFound;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseImp implements UserDatabase {
    private List<User> allUser;

    public UserDatabaseImp() {
        allUser = new ArrayList<>();
        var user = new User("Aiyu", "aiyu.exe");
        List<Book> books = new ArrayList<>();
        books.add(new Book(156, "Programming in C", "12/5/2021", "06/06/2021"));
        books.add(new Book(256, "Programming in Java", "01/6/2021", "12/06/2021"));
        user.setBorrowedBooks(books);
        allUser.add(user);

    }

    private User getUser(User user) {
        for (var u : allUser) {
            if (u.getUserName().equals(user.getUserName()) &&
                    u.getUserPassword().equals(user.getUserPassword())
            ) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User logIn(String userName, String password) throws NoUserFound {
        var user = getUser(new User(userName, password));
        if (user == null)
            throw new NoUserFound("No User found with this user name or either your password is incorrect");
        return user;
    }

    @Override
    public boolean addUser(User user) throws DuplicateUserFound {
        for (var u : allUser) {
            if (u.getUserName().equals(user.getUserName())) {
                throw new DuplicateUserFound("User is already register !!!");
            }
        }
        allUser.add(user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        return true;
    }
}
