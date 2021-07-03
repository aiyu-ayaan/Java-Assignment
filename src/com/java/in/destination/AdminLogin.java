package com.java.in.destination;

import com.java.in.Library;
import com.java.in.data.UserDatabase;
import com.java.in.model.Book;
import com.java.in.model.User;
import com.java.in.utils.Exp;
import com.java.in.utils.NoBookFound;
import com.java.in.utils.NoUserFound;

import java.util.Locale;
import java.util.Scanner;

public class AdminLogin {
    public static void logInAsAdmin(UserDatabase userDatabase) {
        System.out.print("Enter Admin Username :- ");
        var adminUserName = new Scanner(System.in).next().toLowerCase(Locale.ROOT);
        if (!adminUserName.equals(Exp.ADMIN_ID)) {
            System.out.println("Invalid Admin User Id !!!\n");
            return;
        }
        System.out.print("Enter Admin Password :- ");
        var adminUserPassword = new Scanner(System.in).next();
        if (!adminUserPassword.equals(Exp.ADMIN_PASSWORD)) {
            System.out.println("Invalid Admin Password !!!\n\n");
            return;
        }
        System.out.println("\n Welcome " + adminUserName);
        var isTrue = true;
        while (isTrue) {
            System.out.println("""
                    Admin Panel :-
                                      
                    Press 1 to show all Student List\s
                    Press 2 to find any Student Name\s
                    Press 3 to add Students\s
                    Press 4 to Log Out
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> getAllUsers(userDatabase);
                case 2 -> getStudentFromName(userDatabase);
                case 3 -> Library.signIn();
                case 4 -> isTrue = false;
            }
        }
    }

    private static void getStudentFromName(UserDatabase userDatabase) {
        System.out.print("Enter username which you want to find :- ");
        var userName = new Scanner(System.in).next();
        try {
            var user = userDatabase.getUserByUserName(userName);
            editUser(user, userDatabase);
        } catch (NoUserFound e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void editUser(User user, UserDatabase userDatabase) {
        var isTrue = true;
        while (isTrue) {
            System.out.println("""
                    Press 1 to show all borrowed books\s
                    Press 2 to delete user\s
                    Press 3 to back !!
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> printAllBorrowedBooks(user, userDatabase);
                case 2 -> isTrue = !deleteUser(user, userDatabase);
                case 3 -> isTrue = false;
            }
        }
    }

    private static void printAllBorrowedBooks(User user, UserDatabase userDatabase) {
        var isTrue = true;
        while (isTrue) {
            LogIn.printBookList(user.getBorrowedBooks());
            System.out.println("""
                    Press 1 to Add Book\s
                    Press 2 to delete Book\s
                    Press 3 to delete All Books\s
                    Press 4 to back
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> addBook(user, userDatabase);
                case 2 -> deleteBook(user, userDatabase);
                case 3 -> deleteAllBooks(user, userDatabase);
                case 4 -> isTrue = false;
            }
        }
    }

    private static void deleteAllBooks(User user, UserDatabase userDatabase) {
        var isDeleted = userDatabase.deleteAllBooks(user);
        if (isDeleted) System.out.println("\n All book list is deleted to that user!!! \n");
    }

    private static void deleteBook(User user, UserDatabase userDatabase) {
        System.out.print("Enter BookId :- ");
        try {
            var bookId = new Scanner(System.in).nextLong();
            var isDeleted = userDatabase.deleteBook(bookId, user);
            if (isDeleted) System.out.println("\nDeleted Successfully \n");
        } catch (NoBookFound e) {
            System.out.println("\n" + e.getLocalizedMessage() + "\n");
        }

    }

    private static void addBook(User user, UserDatabase userDatabase) {
        System.out.print("Books Id :- ");
        var bookId = new Scanner(System.in).nextLong();
        System.out.print("Books Name :- ");
        var bookName = new Scanner(System.in).next();
        System.out.print("Borrow Date :- ");
        var borrowDate = new Scanner(System.in).next();
        System.out.print("Return Date :- ");
        var returnDate = new Scanner(System.in).next();
        var book = new Book(bookId, bookName, borrowDate, returnDate);
        var isAdded = userDatabase.addBook(book, user);
        if (isAdded) System.out.println("\nBook is Added !!! \n");
    }

    private static boolean deleteUser(User user, UserDatabase userDatabase) {
        return userDatabase.deleteUser(user);
    }

    private static void getAllUsers(UserDatabase userDatabase) {
        var users = userDatabase.getAllUsers();
        for (var user : users) {
            printDetail(user);
        }
        System.out.println("\n");
    }

    private static void printDetail(User user) {
        System.out.println("User Name :- " + user.getUserName() + " | Total Borrowed Book :- " + (user.getBorrowedBooks().size()));
    }
}
