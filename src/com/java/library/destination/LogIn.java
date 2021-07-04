package com.java.library.destination;

import com.java.library.data.UserDatabase;
import com.java.library.model.Book;
import com.java.library.model.User;
import com.java.library.utils.NoUserFound;
import com.java.library.utils.PasswordException;
import com.java.library.utils.Validate;

import java.util.List;
import java.util.Scanner;

/**
 * Take Current login user and see all the details of that account
 */
public class LogIn {

    /**
     * Takes current user and userDatabase
     *
     * @param user         current logIn user
     * @param userDatabase userDatabase
     * @see com.java.library.Library Library
     */
    public static void getLogInUser(User user, UserDatabase userDatabase) {
        System.out.println("\n\nWelcome " + user.getUserName());
        printMenu(user, userDatabase);
    }

    /**
     * Print all methods available for logIn user
     *
     * @param user         logInUser
     * @param userDatabase userDatabase
     * @see com.java.library.destination.LogIn#getLogInUser(User, UserDatabase)
     */
    private static void printMenu(User user, UserDatabase userDatabase) {
        var isTrue = true;
        while (isTrue) {
            System.out.println("""
                    Press 1 to show all Borrowed Books.\s
                    Press 2 to change Password.\s
                    Press 3 to Log out..
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> printAllBorrowedBooks(user);
                case 2 -> updatePassword(user, userDatabase);
                case 3 -> isTrue = false;
            }
        }
    }

    /**
     * Print all borrowed books of logInUser
     *
     * @param user login User
     * @see com.java.library.destination.LogIn#printMenu(User, UserDatabase)
     */
    private static void printAllBorrowedBooks(User user) {
        System.out.println("All Borrowed Books");
        printBookList(user.getBorrowedBooks());
    }

    /**
     * Takes book list and print them in formate
     *
     * @param books books list
     * @see com.java.library.destination.LogIn#printAllBorrowedBooks(User)
     */
    public static void printBookList(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No book is borrowed to this user name !!!\n");
            return;
        }
        for (var book : books) {
            System.out.println("Book Id :- " + book.getBookId() + " | Book Name :- " + book.getBookName() + " | Borrowed Data :- " + book.getBorrowedDate() + " | Return Date :- " + book.getReturnDate());
        }
        System.out.println("\n");
    }

    /**
     * Update password of login user
     *
     * @param user         login User
     * @param userDatabase userDatabase
     * @see com.java.library.destination.LogIn#printMenu(User, UserDatabase) 
     */
    private static void updatePassword(User user, UserDatabase userDatabase) {
        System.out.print("Enter your new password :- ");
        var password = new Scanner(System.in).next();
//        Validate new password
        boolean isChange = false;
        try {
            var isValidate = Validate.validatePassword(password);
            isChange = userDatabase.changePassword(user, password);
        } catch (NoUserFound | PasswordException e) {
            System.out.println("\n" + e.getLocalizedMessage() + "\n");
        }
        if (isChange) System.out.println("Your Password is successfully change !! \n");
    }
}
