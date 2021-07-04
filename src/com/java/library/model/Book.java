package com.java.library.model;

/**
 * Entity class for books
 */
public class Book {
    private long bookId;
    private String bookName;
    private String returnDate;
    private String borrowedDate;

    /**
     * Entity class for books
     *
     * @param bookId       valid book id
     * @param bookName     book name
     * @param borrowedDate date
     * @param returnDate   date
     * @see com.java.library.model.User User
     */
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
