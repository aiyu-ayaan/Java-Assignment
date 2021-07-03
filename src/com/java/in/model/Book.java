package com.java.in.model;

public class Book {
    private long bookId;
    private String bookName;
    private String returnDate;
    private String borrowedDate;

    public Book(long bookId, String bookName, String borrowedDate, String returnDate) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.returnDate = returnDate;
        this.borrowedDate = borrowedDate;
    }

    public long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }
}
