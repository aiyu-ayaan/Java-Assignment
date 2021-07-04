package com.java.library.destination;

import com.java.library.Library;
import com.java.library.data.UserDatabase;
import com.java.library.model.Book;
import com.java.library.model.User;
import com.java.library.utils.Constants;
import com.java.library.utils.NoBookFound;
import com.java.library.utils.NoUserFound;

import java.util.Locale;
import java.util.Scanner;

public class AdminLogin {
    /**
     * This Class has all methods used by admin
     *
     * @param userDatabase userDatabase
     * @see com.java.library.data.UserDatabase UserDatabase
     */
    public static void logInAsAdmin(UserDatabase userDatabase) {
        System.out.print("Enter Admin Username :- ");
        var adminUserName = new Scanner(System.in).next().toLowerCase(Locale.ROOT);
        if (!adminUserName.equals(Constants.ADMIN_ID)) {
            System.out.println("Invalid Admin User Id !!!\n");
            return;
        }
        System.out.print("Enter Admin Password :- ");
        var adminUserPassword = new Scanner(System.in).next();
        if (!adminUserPassword.equals(Constants.ADMIN_PASSWORD)) {
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
                case 3 -> Library.signUp();
                case 4 -> isTrue = false;
            }
        }
    }

    /**
     * Print all user from database
     *
     * @param userDatabase userDatabase Object
     * @see com.java.library.data.UserDatabaseImp UserDatabase
     * @see com.java.library.destination.AdminLogin#logInAsAdmin(UserDatabase)
     */
    private static void getAllUsers(UserDatabase userDatabase) {
        var users = userDatabase.getAllUsers();
        for (var user : users) {
            printDetail(user);
        }
        System.out.println("\n");
    }

    /**
     * Print all properties of user
     *
     * @param user
     * @see com.java.library.model.User User
     * @see com.java.library.destination.AdminLogin#getAllUsers(UserDatabase)
     */
    private static void printDetail(User user) {
        System.out.println("User Name :- " + user.getUserName() + " | Total Borrowed Book :- " + (user.getBorrowedBooks().size()));
    }

    /**
     * Fetch user using its userName
     *
     * @param userDatabase userDatabase
     * @see com.java.library.data.UserDatabaseImp UserDatabaseImp
     * @see com.java.library.model.User User
     * @see com.java.library.destination.AdminLogin#logInAsAdmin(UserDatabase)
     */
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

    /**
     * Edit data user selected user
     *
     * @param user         selected user
     * @param userDatabase userDatabase
     * @see com.java.library.data.UserDatabaseImp UserDatabaseImp
     * @see com.java.library.destination.AdminLogin#getStudentFromName(UserDatabase)
     */
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
                case 1 -> bookDetail(user, userDatabase);
                case 2 -> isTrue = !deleteUser(user, userDatabase);
                case 3 -> isTrue = false;
            }
        }
    }

    /**
     * Print all book list of selected user and also allow to manipulate them
     *
     * @param user         selected user
     * @param userDatabase User Database
     * @see com.java.library.data.UserDatabaseImp UserDatabaseImp
     * @see com.java.library.model.User User
     * @see com.java.library.destination.AdminLogin#editUser(User, UserDatabase)
     */
    private static void bookDetail(User user, UserDatabase userDatabase) {
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

    /**
     * Add book to selected user's account
     *
     * @param user         selected user
     * @param userDatabase userDatabase
     * @see com.java.library.model.User User
     * @see com.java.library.data.UserDatabaseImp UserDatabaseImp
     * @see com.java.library.destination.AdminLogin#bookDetail(User, UserDatabase)
     */
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

    /**
     * Delete selected book from user's account
     *
     * @param user         selected user
     * @param userDatabase userDatabase
     * @see com.java.library.model.User User
     * @see com.java.library.data.UserDatabaseImp UserDatabaseImp
     * @see com.java.library.destination.AdminLogin#bookDetail(User, UserDatabase)
     */
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

    /**
     * Delete all book list from selected user's account
     *
     * @param user         selected user
     * @param userDatabase userDatabase
     * @see com.java.library.model.User User
     * @see com.java.library.data.UserDatabaseImp UserDatabaseImp
     * @see com.java.library.destination.AdminLogin#bookDetail(User, UserDatabase)
     */
    private static void deleteAllBooks(User user, UserDatabase userDatabase) {
        var isDeleted = userDatabase.deleteAllBooks(user);
        if (isDeleted) System.out.println("\n All book list is deleted to that user!!! \n");
    }

    /**
     * Delete selected user from userDatabase
     *
     * @param user         selected user
     * @param userDatabase userDatabase
     * @return boolean value
     * @see com.java.library.model.User User
     * @see com.java.library.data.UserDatabaseImp UserDatabaseImp
     * @see com.java.library.destination.AdminLogin#editUser(User, UserDatabase)
     */
    private static boolean deleteUser(User user, UserDatabase userDatabase) {
        return userDatabase.deleteUser(user);
    }


}
