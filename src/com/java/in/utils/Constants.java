package com.java.in.utils;

import com.java.in.model.Book;
import com.java.in.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Constants {
    public static String ADMIN_ID = "system";
    public static String ADMIN_PASSWORD = "system";
    public static List<User> list = new ArrayList<User>() {{
        add(new User("aiyu", "aiyu.exe", new ArrayList<Book>() {{
            add(new Book(156, "Programming in C", "12/5/2021", "6/6/2021"));
            add(new Book(256, "Programming in Java", "1/6/2021", "12/6/2021"));
        }}));

        add(new User("roushan", "roushan.exe", new ArrayList<Book>() {{
            add(new Book(567, "Programming in HTML", "4/5/2021", "6/5/2021"));
        }}));

        add(new User("aasif", "aasif.exe"));

        add(new User("nilchor", "nilchor", new ArrayList<Book>() {{
            add(new Book(876, "How To Earn More Xp In Valorant", "31/7/2021", "5/9/2021"));
            add(new Book(864, "Cracking the Xp Hunting Competition", "31/6/2021", "12/7/2021"));
        }}));


        add(new User("littu", "littu", new ArrayList<Book>() {{
            add(new Book(689, "How to Make Anime", "16/7/2021", "06/9/2021"));
        }}));


        add(new User("aaditya", "aaditya", new ArrayList<Book>() {{
            add(new Book(224, "10 Best Street Food", "12/6/2021", "06/9/2021"));
        }}));
    }};

}
