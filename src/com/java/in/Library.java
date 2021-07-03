package com.java.in;

import com.java.in.data.UserDatabase;
import com.java.in.data.UserDatabaseImp;
import com.java.in.destination.AdminLogin;
import com.java.in.destination.LogIn;
import com.java.in.model.User;

import java.util.Scanner;

public class Library {

    private static UserDatabase userDatabase;

    public static void main(String[] args) {
//        Initialize database
        userDatabase = new UserDatabaseImp();

        while (true) {
            System.out.print("""
                    Welcome to AAANSR Library
                    For Log In press 1.\s
                    For Sign In press 2.\s
                    For Admin Access Press 0.\s
                    Press 3 for exit !!.
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 0 -> logInAsAdmin();
                case 1 -> logIn();
                case 2 -> signIn();
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
            LogIn.getLogInUser(logInUser,userDatabase);
        } catch (Exception e) {
            System.out.println("\n" + e.getLocalizedMessage() + "\n");
        }
    }

    /**
     * New user can log in to library
     */
    private static void signIn() {
        try {
            System.out.println("SIGN IN\n");
            System.out.print("Enter Your User Name :- ");
            var userName = new Scanner(System.in).next();
            System.out.print("Enter Your Password :- ");
            var password = new Scanner(System.in).next();
            var user = new User(userName, password);
            var isAdded = userDatabase.addUser(user);
            if (isAdded) System.out.println("User is Added !! \n");
        } catch (Exception e) {
            System.out.println("\n" + e.getLocalizedMessage() + "\n");
        }

    }
}
