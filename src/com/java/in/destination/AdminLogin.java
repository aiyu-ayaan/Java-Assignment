package com.java.in.destination;

import com.java.in.data.UserDatabase;
import com.java.in.model.User;
import com.java.in.utils.Constants;
import com.java.in.utils.NoUserFound;

import java.util.Locale;
import java.util.Scanner;

public class AdminLogin {
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
                    Press 1 to show all Student List\s
                    Press 2 to find any Student Name\s
                    Press 3 to Log Out
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> getAllUsers(userDatabase);
                case 2 -> getStudentFromName(userDatabase);
                case 3 -> isTrue = false;
            }
        }
    }

    private static void getStudentFromName(UserDatabase userDatabase) {
        System.out.print("Enter username which you want to find :- ");
        var userName = new Scanner(System.in).next();
        try {
            var user = userDatabase.getUserByUserName(userName);
            editUser(user);
        } catch (NoUserFound e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void editUser(User user) {
        var isTrue = true;
        while (isTrue) {
            System.out.println("""
                    Press 1 to show all borrowed books\s
                    Press 2 to delete user\s
                    Press 3 to back !!
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> LogIn.printAllBorrowedBooks(user);
                case 3 -> isTrue = false;
            }
        }
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
