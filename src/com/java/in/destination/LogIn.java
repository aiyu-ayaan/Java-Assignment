package com.java.in.destination;

import com.java.in.data.UserDatabase;
import com.java.in.model.User;
import com.java.in.utils.NoUserFound;

import java.util.Scanner;

/**
 * Take Current login user and see all the details of that account
 *
 */
public class LogIn {


    public static void getLogInUser(User user, UserDatabase userDatabase) {
        System.out.println("\n\nWelcome " + user.getUserName());
        printMenu(user, userDatabase);
    }

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

    public static void printAllBorrowedBooks(User user) {
        System.out.println("All Borrowed Books");
        var books = user.getBorrowedBooks();
        if (books.isEmpty()) {
            System.out.println("No book is borrowed to this user name !!!\n");
            return;
        }
        for (var book : books) {
            System.out.println("Book Id :- " + book.getBookId() + " | Book Name :- " + book.getBookName() + " | Borrowed Data :- " + book.getBorrowedDate() + " | Return Date :- " + book.getReturnDate());
        }
        System.out.println("\n");
    }

    private static void updatePassword(User user, UserDatabase userDatabase) {
        System.out.print("Enter your new password :- ");
        var password = new Scanner(System.in).next();
        boolean isChange = false;
        try {
            isChange = userDatabase.changePassword(user, password);
        } catch (NoUserFound e) {
            System.out.println("\n" + e.getLocalizedMessage() + "\n");
        }
        if (isChange) System.out.println("Your Password is successfully change !! \n");
    }
}
