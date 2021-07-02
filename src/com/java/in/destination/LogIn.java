package com.java.in.destination;

import com.java.in.model.User;

import java.util.Scanner;

/**
 * Take Current login user and see all the details of that account
 */
public class LogIn {
    private User user;

    public LogIn(User user) {
        System.out.println("\n\nWelcome " + user.getUserName());
        this.user = user;
    }

    public void printMenu() {
        var isTrue = true;
        while (isTrue) {
            System.out.println("""
                    Press 1 to show all Borrowed Books.\s
                    Press 2 to change User Name.\s
                    Press 3 to change Password.\s
                    Press 4 to Log out..
                    """);
            var option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> printAllBorrowedBooks();
                case 4 -> isTrue = false;
            }
        }
    }

    private void updatePassword(String password) {

    }

    private void updateUserName(String userName) {

    }

    private void printAllBorrowedBooks() {
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
}
