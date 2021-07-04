package com.java.library;

import com.java.library.data.UserDatabase;
import com.java.library.data.UserDatabaseImp;
import com.java.library.destination.AdminLogin;
import com.java.library.destination.LogIn;
import com.java.library.model.User;
import com.java.library.utils.DuplicateUserFound;
import com.java.library.utils.NoUserFound;
import com.java.library.utils.PasswordException;
import com.java.library.utils.Validate;

import java.util.Scanner;

/**
 * Entry point of this project
 *
 * @see com.java.library.data.UserDatabase UserDatabase
 * @see com.java.library.destination.AdminLogin Admin Pannel
 * @see com.java.library.destination.LogIn LogIn Pannel
 * @see com.java.library.utils.Validate#validatePassword(String) user to valid password
 */
public class Library {

    private static UserDatabase userDatabase;

    public static void main(String[] args) {
//        Initialize database
        userDatabase = new UserDatabaseImp();

        while (true) {
            System.out.print("""
                    Welcome to Lockdown Library
                    For Log In press 1.\s
                    For Sign Up press 2.\s
                    For Admin Access Press 0.\s
                    Press 3 for exit !!.
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 0 -> logInAsAdmin();
                case 1 -> logIn();
                case 2 -> signUp();
                case 3 -> System.exit(0);
                default -> System.out.println("Check your input !!!");
            }
        }
    }

    /**
     * Admin Access
     */
    private static void logInAsAdmin() {
        AdminLogin.logInAsAdmin(userDatabase);
    }

    /**
     * User Can log in through userName and userPassword
     */
    private static void logIn() {
        try {
            System.out.println("LOG IN\n");
            System.out.print("Enter Your User Name :- ");
            var userName = new Scanner(System.in).next();
            System.out.print("Enter Your Password :- ");
            var password = new Scanner(System.in).next();
            var logInUser = userDatabase.logIn(userName, password);
            LogIn.getLogInUser(logInUser, userDatabase);
        } catch (NoUserFound e) {
            System.out.println("\n" + e.getLocalizedMessage() + "\n");
        }
    }

    /**
     * New user can log in to library
     */
    public static void signUp() {
        try {
            System.out.println("SIGN IN\n");
            System.out.print("Enter Your User Name :- ");
            var userName = new Scanner(System.in).next();
            System.out.print("Enter Your Password :- ");
            var password = new Scanner(System.in).next();
            //            Checking password is valid or not
            Validate.validatePassword(password);
            var user = new User(userName, password);
            var isAdded = userDatabase.addUser(user);
            if (isAdded) System.out.println("User is Added !! \n");
        } catch (DuplicateUserFound | PasswordException e) {
            System.out.println("\n" + e.getLocalizedMessage() + "\n");
        }

    }
}
